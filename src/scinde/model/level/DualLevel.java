package scinde.model.level;

import java.util.List;

import scinde.model.entity.EntityHolder;
import scinde.model.entity.PlayerHolder;
import scinde.model.world.World;

public class DualLevel extends AbstractLevel{
	
	private World left;
	private World right;
	private List<EntityHolder> sharedEntities;
	
	protected DualLevel(String name, PlayerHolder player, List<EntityHolder> shared, World left, World right)
	{
		super(name, player, true);
		this.left = left;
		this.right=right;
		this.sharedEntities = shared;
		this.sharedEntities.add(player);
	}
	
	public void triggerInterraction()
	{
		left.triggerActivables(player);
		right.triggerActivables(player);
	}
	
	public synchronized void update()
	{
		for(EntityHolder entity : left.getEntities())
		{
			entity.update(List.of(left));
		}
		for(EntityHolder entity : right.getEntities())
		{
			entity.update(List.of(right));
		}
		for(EntityHolder entity : sharedEntities)
		{
			entity.update(List.of(left, right));
		}
		left.makeEntitiesUpdate();
		right.makeEntitiesUpdate();
		player.updateWeapons(left, right);
	}
	
	public World getLeft() {
		return left;
	}
	
	public World getRight() {
		return right;
	}
}
