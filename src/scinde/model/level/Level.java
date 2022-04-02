package scinde.model.level;

import java.util.List;

import scinde.model.entity.Entity;
import scinde.model.entity.EntityHolder;
import scinde.model.entity.player.Player;
import scinde.model.world.World;

public class Level {
	
	private String name;
	private World left;
	private World right;
	private EntityHolder player;
	private List<EntityHolder> sharedEntities;
	
	protected Level(String name, EntityHolder player, List<EntityHolder> shared, World left, World right)
	{
		this.left = left;
		this.right=right;
		this.player=player;
		this.name=name;
		this.sharedEntities = shared;
		this.sharedEntities.add(this.player);
	}
	
	public void triggerInterraction()
	{
		left.triggerActivables(player);
		right.triggerActivables(player);
	}
	
	public EntityHolder getPlayer()
	{
		return player;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void update()
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
	}
	
	public World getLeft() {
		return left;
	}
	
	public World getRight() {
		return right;
	}
}
