package scinde.model.level;

import java.util.List;

import scinde.model.entity.EntityHolder;
import scinde.model.entity.PlayerHolder;
import scinde.model.world.World;

public class SingleLevel extends AbstractLevel{

	private World world;
	
	public SingleLevel(String name, World world, PlayerHolder player)
	{
		super(name, player, false);
		this.world=world;
		this.world.spawnEntity(player);
		this.world.makeEntitiesUpdate();
	}
	
	@Override
	public void triggerInterraction() {
		world.triggerActivables(player);
	}
	
	public World getWorld()
	{
		return world;
	}

	@Override
	public void update() {
		for(EntityHolder entity : world.getEntities())
		{
			entity.update(List.of(world));
		}
		world.makeEntitiesUpdate();
		player.updateWeapons(world, world);
	}

}
