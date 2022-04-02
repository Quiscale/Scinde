package scinde.triggerable;

import scinde.utils.hitbox.HitBox;
import scinde.world.World;

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
