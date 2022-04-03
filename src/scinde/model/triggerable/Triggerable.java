package scinde.model.triggerable;

import scinde.model.entity.EntityHolder;
import scinde.model.utils.hitbox.HitBox;
import scinde.model.world.World;

public abstract class Triggerable {
	protected HitBox trigger;
	protected EntityHolder linked;
	
	protected Triggerable(HitBox box)
	{
		this.trigger = box;
	}
	
	public void setLinked(EntityHolder data)
	{
		this.linked = data;
	}
	
	public HitBox getTrigger()
	{
		return trigger;
	}
	
	public abstract void onTrigger(World word, EntityHolder entity);
}
