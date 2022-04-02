package scinde.level;

import scinde.entity.Player;
import scinde.world.World;

public class Level {
	
	private String name;
	private World left;
	private World right;
	private Player player;
	
	protected Level(String name, Player player, World left, World right)
	{
		this.left = left;
		this.right=right;
		this.player=player;
		this.name=name;
	}
	
	public void triggerInterraction()
	{
		//TODO : do interraction triggering here
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	public String getName()
	{
		return name;
	}
	
	public World getLeft() {
		return left;
	}
	
	public World getRight() {
		return right;
	}
}
