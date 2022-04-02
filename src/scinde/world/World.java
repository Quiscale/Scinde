package scinde.world;

import java.util.ArrayList;
import java.util.List;

import scinde.entity.Entity;
import scinde.utils.Velocity;
import scinde.utils.hitbox.HitBox;

public class World {
	
	private List<Entity> entities;
	private List<HitBox> blocks;
	
	public World()
	{
		entities = new ArrayList<>();
		blocks = new ArrayList<>();
	}
	
	public List<HitBox> getBlocks()
	{
		return blocks;
	}
	
	public List<Entity> getEntities()
	{
		return entities;
	}
	
	public void spawnEntity(Entity e)
	{
		this.entities.add(e);
	}
	
	public void addHitbox(HitBox h)
	{
		this.blocks.add(h);
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
	
	public boolean entityCanMove(Entity entity) {
		boolean canMove = true;
		for(Entity other : entities)
		{
			if(other != entity && other.getHitbox().contains(entity.getHitbox()))
			{
				other.onHit(entity);
				canMove = false;
			}
		}
		for(HitBox other : blocks)
		{
			if(other.contains(entity.getHitbox()))
			{
				return false;
			}
		}
		return canMove;
	}
	
}
