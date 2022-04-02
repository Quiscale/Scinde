package scinde.model.entity.enemies;

import java.util.ArrayList;
import java.util.List;

import scinde.controller.UpdateTimer;
import scinde.model.entity.Entity;
import scinde.model.entity.player.Player;
import scinde.model.utils.Position;
import scinde.model.utils.Velocity;
import scinde.model.utils.hitbox.HitBox;
import scinde.model.world.World;

public abstract class Enemy extends Entity {

	private List<Position> pattern;
	private float velocityUnit;
	private boolean canMove;
	private boolean followPattern;
	private int tracker;
	private int waitingCounter;
	private boolean followReverse;
	private float damage;
	private boolean forceNextStep;

	protected Enemy(float damage, float velocityUnit, HitBox box) {
		super(box);
		canMove = true;
		pattern = new ArrayList<>();
		this.velocityUnit = velocityUnit;
		followPattern = false;
		waitingCounter = getWaitingForContinuePattern();
		followReverse = false;
		this.damage = damage;
	}

	@Override
	public boolean update(List<World> worlds) {
		super.update(worlds);
		if (followPattern) {
			Velocity newVelocity = Velocity.createVector(this.getPos(), pattern.get(tracker)).toUnit(velocityUnit);

			if (waitingCounter < getWaitingForContinuePattern()) {
				waitingCounter += UpdateTimer.ELAPSED_TIME;
				newVelocity = new Velocity(0, 0);
			} else {
				double diff = this.getPos().diff(pattern.get(tracker));
				if (diff <= (1 * velocityUnit) || forceNextStep) {
					if(!forceNextStep)
						waitingCounter = 0;
					else
					{
						forceNextStep = false;
					}
					tracker += followReverse ? -1 : 1;
					if (!followReverse && tracker >= pattern.size())
						tracker = 0;
					else if (followReverse && tracker < 0)
						tracker = pattern.size() - 1;
					newVelocity = Velocity.createVector(this.getPos(), pattern.get(tracker)).toUnit(velocityUnit);
				}
			}
			this.setVelocity(newVelocity);
		}
		return true;
	}

	@Override
	public void onHitWall() {
		if (followPattern) {
			waitingCounter = 0;
			followReverse = !followReverse;
			forceNextStep = true;
		}
	}

	private int getWaitingForContinuePattern() {
		return 500;
	}

	@Override
	public void setVelocity(Velocity velocity) {
		if (canMove)
			super.setVelocity(velocity.toUnit(velocityUnit));
	}

	public List<Position> getPattern() {
		return pattern;
	}

	public void setPattern(List<Position> pattern) {
		if (canMove) {
			System.out.println("set pattern " + pattern);
			this.pattern = pattern;
			this.followPattern = true;
			this.followReverse = false;
		}
	}

	public void setPattern(List<Position> pattern, boolean startReverse) {
		if (canMove) {
			System.out.println("set pattern " + pattern);
			this.pattern = pattern;
			this.followPattern = true;
			this.followReverse = startReverse;
		}
	}

	@Override
	public void onHit(World world, Entity other) {
		if (other instanceof Player player) {
			player.hit(damage);
		} else {
			if (followPattern) {
				followReverse = !followReverse;
				forceNextStep = true;
			}
		}
	}
}
