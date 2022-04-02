package scinde.model.entity.enemies;

import java.util.List;

import scinde.controller.UpdateTimer;
import scinde.model.entity.EntityHolder;
import scinde.model.utils.Position;
import scinde.model.utils.Velocity;

public class PatternFollower {
	protected EntityHolder enemy;
	protected List<Position> pattern;
	protected int tracker;
	private double velocityUnit;
	private int waitingCounter;
	private boolean forceNextStep;
	protected boolean followReverse;
	
	public PatternFollower(EntityHolder owner, List<Position> path)
	{
		pattern = path;
		enemy = owner;
	}
	
	public List<Position> getPositions()
	{
		return pattern;
	}
	
	public void setVelocityUnit(double value)
	{
		this.velocityUnit = value;
	}
	
	public void follow()
	{
		double diff = enemy.getPos().diff(pattern.get(tracker));
		double newVelocityUnit = velocityUnit - (velocityUnit-0.1)/(0.1*diff+1);
		if(newVelocityUnit <= 0) newVelocityUnit = 0.01;
		Velocity newVelocity = Velocity.createVector(enemy.getPos(), pattern.get(tracker)).toUnit(newVelocityUnit);

		if (waitingCounter < getWaitingForContinuePattern()) {
			waitingCounter += UpdateTimer.ELAPSED_TIME;
			newVelocity = new Velocity(0, 0);
		} else {
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
				newVelocity = Velocity.createVector(enemy.getPos(), pattern.get(tracker)).toUnit(newVelocityUnit);
			}
		}
		enemy.setVelocity(newVelocity);
	}


	private int getWaitingForContinuePattern() {
		return 500;
	}

	public void inverse() {
		followReverse = !followReverse;
		forceNextStep = true;
	}

	public void waitNext() {
		waitingCounter = 0;
	}
}
