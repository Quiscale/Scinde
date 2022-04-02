package scinde.model.triggerable;

import scinde.model.world.World;
import scinde.utils.hitbox.HitBox;

public abstract class Triggerable {
	private HitBox trigger;
	
	protected Triggerable(HitBox box)
	{
		this.trigger = box;
	}
	
	public HitBox getTrigger()
	{
		return trigger;
	}
	
	public abstract void onTrigger(World word);
}
