package scinde.model.level;

import scinde.model.entity.PlayerHolder;

public abstract class AbstractLevel {

	protected String name;
	protected PlayerHolder player;
	private boolean isDual;
	
	protected AbstractLevel(String name, PlayerHolder player, boolean dual)
	{
		this.name=name;
		this.player = player;
		this.isDual = dual;
	}
	
	public abstract void triggerInterraction();
	
	public abstract void update();

	
	public PlayerHolder getPlayer()
	{
		return player;
	}
	
	public boolean isDual()
	{
		return isDual;
	}
	
	public String getName()
	{
		return name;
	}
	
	public DualLevel asDual()
	{
		return (DualLevel)this;
	}
	
	public SingleLevel asSingle()
	{
		return (SingleLevel)this;
	}
}
