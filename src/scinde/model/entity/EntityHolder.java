package scinde.model.entity;

import java.util.List;

import scinde.controller.UpdateTimer;
import scinde.model.entity.enemies.PatternFollower;
import scinde.model.utils.IUpdatable;
import scinde.model.utils.Position;
import scinde.model.utils.Velocity;
import scinde.model.utils.hitbox.CircleHitbox;
import scinde.model.utils.hitbox.HitBox;
import scinde.model.world.World;

public class EntityHolder implements IUpdatable {

	private static final float SLOW_COEFFICIENT = 0.2f;
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
		this.hitbox = new CircleHitbox((float) entity.getHitboxSize());
		this.hitbox.init();
		this.velocity = new Velocity(0, 0);
		this.pos = new Position();
		this.lifePoints = entity.getMaxLifePoints();
		invincibilityTime = 0;
	}

	public Entity getEntity() {
		return entity;
	}

	public void hit(World world, EntityHolder from, Position contactPoint) {
		System.out.println(entity + " vs " + from.entity);
		if (entity.canDamage(from.entity) && from.invincibilityTime <= 0) {
			from.lifePoints -= entity.getDamage();
			entity.onHit(world, pos, this, from);
		}

		double medianMag = (velocity.getMagnitude() + from.getVelocity().getMagnitude()) / 2;
		velocity = Velocity.createVector(contactPoint, pos).inverse().toUnit().mult(medianMag)
				.mult(1 + entity.bouncyness).mult(ENERGY_CONSERVATION);

		if (pattern != null) {
			pattern.follow();
		}
	}

	public void kill(World world) {

		entity.onDeath(world, pos, this);
		world.removeEntity(this);
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

	public void hitWall(World world, Position contactPoint) {
		if (pattern != null) {
			pattern.inverse();
			pattern.waitNext();
		}
		Velocity mark = Velocity.createVector(contactPoint, pos).toUnit();
		double angle = velocity.inverse().angle(mark);
		velocity = velocity.rotate(angle * 2f).mult(1 + entity.bouncyness).mult(ENERGY_CONSERVATION).mult(0.60);

		entity.onHitWall(world, this);
	}

	public Position getPos() {
		return pos;
	}

	public Velocity getVelocity() {
		return velocity;
	}

	public void update(List<World> worlds) {
		if (lifePoints <= 0) {
			for (World world : worlds) {
				kill(world);
			}
			return;
		}
		this.move();
		for (World world : worlds) {
			world.detectCollision(this);
			entity.onUpdate(worlds, this);
		}
		if (pattern != null) {
			pattern.follow();
		}
		if (invincibilityTime > 0) {
			invincibilityTime -= UpdateTimer.ELAPSED_TIME;
		}
		
		if(velocity.getMagnitude() > 0.1)
			this.velocity = velocity.div(1 + SLOW_COEFFICIENT);
	}

	public void setInvincibleFor(int i) {
		invincibilityTime = i;
	}
}
