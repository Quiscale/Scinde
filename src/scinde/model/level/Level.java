package scinde.model.level;

import java.util.List;

import scinde.model.entity.EntityHolder;
import scinde.model.entity.PlayerHolder;
import scinde.model.world.World;

public class Level {
	
	private String name;
	private World left;
	private World right;
	private PlayerHolder player;
	private List<EntityHolder> sharedEntities;
	
	private Object lock = new Object();
	
	protected Level(String name, PlayerHolder player, List<EntityHolder> shared, World left, World right)
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
	
	public PlayerHolder getPlayer()
	{
		return player;
	}
	
	public String getName()
	{
		return name;
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
