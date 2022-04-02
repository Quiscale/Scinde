package scinde.model.triggerable;

import scinde.model.world.World;
import scinde.utils.hitbox.HitBox;

public class Door extends Triggerable{

	protected Door(HitBox box) {
		super(box);
	}

	@Override
	public void onTrigger(World word) {
		//TODO : open door
	}
	
}
