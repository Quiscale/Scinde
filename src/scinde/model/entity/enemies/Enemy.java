package scinde.model.entity.enemies;

import java.util.List;

import scinde.model.entity.Entity;
import scinde.model.entity.player.Player;
import scinde.model.utils.Velocity;
import scinde.model.utils.hitbox.HitBox;
import scinde.model.world.World;

public abstract class Enemy extends Entity {

	private boolean canMove;
	private float damage;
	private PatternFollower pattern;
	private float velocityUnit;

	protected Enemy(float damage, float velocityUnit, HitBox box) {
		super(box);
		canMove = true;
		this.velocityUnit = velocityUnit;
		this.damage = damage;
	}

	@Override
	public boolean update(List<World> worlds) {
		super.update(worlds);
		if (pattern != null) {
			pattern.follow();
		}
		return true;
	}

	@Override
	public void onHitWall() {
		if (pattern != null) {
			pattern.inverse();
			pattern.waitNext();
		}
	}

	@Override
	public void setVelocity(Velocity velocity) {
		if (canMove)
			super.setVelocity(velocity);
	}

	public PatternFollower getPattern() {
		return pattern;
	}

	public void setPattern(PatternFollower pattern) {
		if (canMove) {
			this.setPattern(pattern, false);
		}
	}

	public void setPattern(PatternFollower pattern, boolean startReverse) {
		if (canMove) {
			this.pattern = pattern;
			if(startReverse)
			{
				pattern.inverse();
			}
			this.pattern.setVelocityUnit(velocityUnit);
		}
	}

	@Override
	public void onHit(World world, Entity other) {
		if (other instanceof Player player) {
			player.hit(damage);
		} else {
			if (pattern != null) {
				pattern.inverse();
			}
		}
	}
}
