package scinde.model.entity;

import java.util.List;
import java.util.concurrent.TimeUnit;

import scinde.controller.UpdateTimer;
import scinde.model.entity.enemies.Enemy;
import scinde.model.entity.enemies.PatternFollower;
import scinde.model.entity.player.Player;
import scinde.model.utils.IUpdatable;
import scinde.model.utils.Position;
import scinde.model.utils.Velocity;
import scinde.model.utils.hitbox.HitBox;
import scinde.model.world.World;

public class EntityHolder implements IUpdatable {

	private static final float SLOW_COEFFICIENT = 0.01f;
	private static final float ENERGY_CONSERVATION = 0.9f;

	private Entity entity;
	private Position pos;
	private Velocity velocity;
	private float lifePoints;
	private HitBox hitbox;
	private PatternFollower pattern;
	private int invincibilityTime;

	public EntityHolder(Entity entity) {
		this.entity = entity;
		this.hitbox = entity.provideHitbox();
		this.hitbox.init();
		this.velocity = new Velocity(0, 0);
		this.pos = new Position();
		this.lifePoints = entity.getMaxLifePoints();
		invincibilityTime = 0;
	}

	public Entity getEntity() {
		return entity;
	}

	public void hit(World world, EntityHolder from) {
		if (from.entity.canDamage(entity) && invincibilityTime <= 0) {
			this.lifePoints -= from.entity.getDamage();
			entity.onHit(world, pos, from.getEntity());
			if (lifePoints <= 0) {
				entity.onDeath(world, pos);
			}
		}

		Velocity additive = Velocity.createVector(from.getPos(), pos).toUnit().mult(ENERGY_CONSERVATION);
		this.setVelocityRaw(additive.mult((float)from.getVelocity().getMagnitude()).mult(1+entity.bouncyness));
		from.setVelocityRaw(additive.inverse().mult((float)velocity.getMagnitude()).mult(1+from.getEntity().bouncyness));
		if (pattern != null) {
			pattern.follow();
		}
	}

	public float getLifePoints() {
		return lifePoints;
	}

	public void setVelocityRaw(Velocity v) {
		this.velocity = v;
	}

	public void setVelocity(Velocity v) {
		this.velocity = new Velocity((velocity.getX() + v.getX()) / 2, (v.getY() + velocity.getY()) / 2);
	}

	public void setPosition(Position pos) {
		this.pos = pos;
		this.hitbox.moveTo(pos);
	}

	protected void move() {
		Position pos = new Position(this.pos.getX() + velocity.getX(), this.pos.getY() + velocity.getY());
		this.pos = pos;
		this.hitbox.moveTo(pos);
	}

	public PatternFollower getPattern() {
		return pattern;
	}

	public void setPattern(PatternFollower pattern) {
		if (entity.canMove) {
			this.setPattern(pattern, false);
		}
	}

	public void setPattern(PatternFollower pattern, boolean startReverse) {
		if (entity.canMove) {
			this.pattern = pattern;
			if (startReverse) {
				pattern.inverse();
			}
			this.pattern.setVelocityUnit(entity.velocityUnit);
		}
	}

	public HitBox getHitbox() {
		return hitbox;
	}

	public void hitWall() {
		if (pattern != null) {
			pattern.inverse();
			pattern.waitNext();
		}
		entity.onHitWall();
	}

	public Position getPos() {
		return pos;
	}

	public Velocity getVelocity() {
		return velocity;
	}

	public void update(List<World> worlds) {
		this.move();
		for (World world : worlds) {
			if (world.detectCollision(this)) {
				setVelocityRaw(getVelocity().inverse());
				move();
				setVelocityRaw(getVelocity().inverse());
			}
			entity.onUpdate(worlds);
		}
		if (pattern != null) {
			pattern.follow();
		}
		if(invincibilityTime > 0)
		{
			invincibilityTime -= UpdateTimer.ELAPSED_TIME;
		}
		this.velocity = velocity.div(1 + SLOW_COEFFICIENT);
	}

	public void setInvincibleFor(int i) {
		invincibilityTime = i;
	}
}
