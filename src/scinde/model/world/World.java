package scinde.model.world;

import java.util.ArrayList;
import java.util.List;

import scinde.model.entity.Entity;
import scinde.model.triggerable.Triggerable;
import scinde.model.utils.Position;
import scinde.model.utils.Velocity;
import scinde.model.utils.hitbox.HitBox;

public class World {
	
	private List<Entity> entities;
	private List<HitBox> blocks;
	private List<Triggerable> triggerables; 
	
	public World()
	{
		entities = new ArrayList<>();
		blocks = new ArrayList<>();
		triggerables = new ArrayList<>();
	}
	
	public List<HitBox> getBlocks()
	{
		return blocks;
	}
	
	public List<Entity> getEntities()
	{
		return entities;
	}
	
	public List<Triggerable> getTriggerables()
	{
		return triggerables;
	}
	
	public void spawnEntity(Entity e)
	{
		this.entities.add(e);
	}
	
	public void addHitbox(HitBox h)
	{
		this.blocks.add(h);
	}
	
	public void addTriggerable(Triggerable t)
	{
		this.triggerables.add(t);
	}

	public List<HitBox> getHitboxes()
	{
		List<HitBox> boxes = new ArrayList<>();
		for(Entity other : entities)
		{
			boxes.add(other.getHitbox());
		}
		for(HitBox other : blocks)
		{
			boxes.add(other);
		}
		return boxes;
	}
	
	public void triggerActivables(Entity fromPrespective)
	{
		for(Triggerable trigger : triggerables)
		{
			if(trigger.getTrigger().overlap(fromPrespective.getHitbox()))
			{
				trigger.onTrigger(this);
			}
		}
	}
	
	public boolean detectCollision(Entity entity) {
		for(Entity other : entities)
		{
			HitBox otherBox = other.getHitbox();
			HitBox thisBox = entity.getHitbox();
			if(other != entity && otherBox != null && otherBox.isEnabled() && thisBox != null && thisBox.isEnabled() && otherBox.overlap(thisBox))
			{
				entity.onHit(this, other);
				if(other.getLifePoints() <= 0)
				{
					entity.onDeath(this);
				}
				return false;
			}
		}
		for(HitBox other : blocks)
		{
			HitBox thisBox = entity.getHitbox();
			if(other != null && other.isEnabled() && thisBox != null && thisBox.isEnabled() && other.overlap(thisBox))
			{
				entity.onHitWall();
				return false;
			}
		}
		return true;
	}
	
}
