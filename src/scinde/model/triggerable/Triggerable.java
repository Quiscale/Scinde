package scinde.model.triggerable;

import scinde.model.utils.hitbox.HitBox;
import scinde.model.world.World;

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
