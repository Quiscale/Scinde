package scinde.triggerable;

import scinde.utils.hitbox.HitBox;
import scinde.world.World;

public class Door extends Triggerable{

	protected Door(HitBox box) {
		super(box);
	}

	@Override
	public void onTrigger(World word) {
		//TODO : open door
	}
	
}
