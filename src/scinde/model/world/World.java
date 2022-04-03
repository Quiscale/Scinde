package scinde.model.world;

import java.util.ArrayList;
import java.util.List;

import scinde.model.entity.EntityHolder;
import scinde.model.entity.PlayerHolder;
import scinde.model.triggerable.Triggerable;
import scinde.model.utils.Position;
import scinde.model.utils.hitbox.HitBox;

public class World {
	
	private List<EntityHolder> entities;
	private List<HitBox> blocks;
	private List<Triggerable> triggerables; 
	private List<EntityHolder> toSpawn;
	private List<EntityHolder> toRemove;
	
	public World()
	{
		entities = new ArrayList<>();
		blocks = new ArrayList<>();
		triggerables = new ArrayList<>();
		toSpawn = new ArrayList<>();
		toRemove = new ArrayList<>();
	}
	
	public List<HitBox> getBlocks()
	{
		return blocks;
	}
	
	public List<EntityHolder> getEntities()
	{
		return entities;
	}
	
	public List<Triggerable> getTriggerables()
	{
		return triggerables;
	}
	
	public void makeEntitiesUpdate()
	{
		this.entities.addAll(toSpawn);
		toSpawn.clear();
		this.entities.removeAll(toRemove);
		toRemove.clear();
	}
	
	public void spawnEntity(EntityHolder e)
	{
		this.toSpawn.add(e);
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
		for(EntityHolder other : entities)
		{
			boxes.add(other.getHitbox());
		}
		for(HitBox other : blocks)
		{
			boxes.add(other);
		}
		return boxes;
	}
	
	public void triggerActivables(PlayerHolder fromPrespective)
	{
		for(Triggerable trigger : triggerables)
		{
			if(trigger.getTrigger().isEnabled() && trigger.getTrigger().overlap(fromPrespective.getHitbox()))
			{
				trigger.onTrigger(this, fromPrespective);
			}
		}
	}
	
	public boolean detectCollision(EntityHolder entity) {
		boolean collides = false;
		for(EntityHolder other : entities)
		{
			HitBox otherBox = other.getHitbox();
			HitBox thisBox = entity.getHitbox();
			if(other != entity && otherBox != null && otherBox.isEnabled() && thisBox != null && thisBox.isEnabled() && otherBox.overlap(thisBox))
			{
				entity.hit(this, other, HitBox.contactPoint(otherBox, thisBox));
				other.hit(this, entity, HitBox.contactPoint(otherBox, thisBox));
				collides = true;
			}
		}
		for(HitBox other : blocks)
		{
			HitBox thisBox = entity.getHitbox();
			if(other != null && other.isEnabled() && thisBox != null && thisBox.isEnabled() && other.overlap(thisBox))
			{
				entity.hitWall(this, HitBox.contactPoint(other, thisBox));
				collides = true;
			}
		}
		return collides;
	}

	public EntityHolder getEntityAt(Position pos) {
		for(EntityHolder entity : entities)
		{
			if(entity.getPos() == pos)
			{
				return entity;
			}
		}
		return null;
	}

	public void removeEntity(EntityHolder self) {
		self.getHitbox().getShape().setVisible(false);
		toRemove.add(self);
	}
	
}
