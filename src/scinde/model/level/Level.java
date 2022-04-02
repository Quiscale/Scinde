package scinde.model.level;

import java.util.List;

import scinde.model.entity.Entity;
import scinde.model.entity.player.Player;
import scinde.model.world.World;

public class Level {
	
	private String name;
	private World left;
	private World right;
	private Player player;
	private List<Entity> sharedEntities;
	
	protected Level(String name, Player player, List<Entity> shared, World left, World right)
	{
		this.left = left;
		this.right=right;
		this.player=player;
		this.name=name;
		this.sharedEntities = shared;
		this.sharedEntities.add(player);
	}
	
	public void triggerInterraction()
	{
		left.triggerActivables(player);
		right.triggerActivables(player);
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void update()
	{
		for(Entity entity : left.getEntities())
		{
			entity.update(List.of(left));
		}
		for(Entity entity : right.getEntities())
		{
			entity.update(List.of(right));
		}
		for(Entity entity : sharedEntities)
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
