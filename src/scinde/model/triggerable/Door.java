package scinde.model.triggerable;

import scinde.model.utils.hitbox.HitBox;
import scinde.model.world.World;

public class Door extends Triggerable{

	protected Door(HitBox box) {
		super(box);
	}

	@Override
	public void onTrigger(World word) {
		//TODO : open door
	}
	
}
