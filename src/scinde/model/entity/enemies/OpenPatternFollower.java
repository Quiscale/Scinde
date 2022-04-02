package scinde.model.entity.enemies;

import java.util.List;

import scinde.model.utils.Position;
import scinde.model.utils.Velocity;

public class OpenPatternFollower extends PatternFollower{

	public OpenPatternFollower(Enemy owner, List<Position> path) {
		super(owner, path);
	}
	
	@Override
	public void follow()
	{
		if(followReverse && tracker > 0 || !followReverse && tracker < pattern.size())
			super.follow();
		else
			enemy.setVelocity(new Velocity(0, 0));
	}
	
	@Override
	public void inverse() {
		this.waitNext();
	}

}
