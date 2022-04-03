package scinde.model.entity;

import java.util.List;

import scinde.model.utils.Position;
import scinde.model.world.World;

public class Door extends Entity{

	private boolean isHorizontal;
	
	protected Door(boolean isHorizontal) {
		super(0, false, 0, 0, 0);
		this.isHorizontal = isHorizontal;
	}
	
	public boolean isHororizontal()
	{
		return isHorizontal;
	}

	@Override
	public float getDamage() {
		return 0;
	}

	@Override
	public void onHitBy(World world, Position pos, EntityHolder self, EntityHolder other) {
		
	}

	@Override
	public void onHit(World world, Position pos, EntityHolder self, EntityHolder other) {
		
	}

	@Override
	public void onDeath(World world, Position pos, EntityHolder self) {
		
	}

	@Override
	public void onUpdate(List<World> worlds, EntityHolder self) {
		
	}

	@Override
	protected boolean canDamage(Entity entity) {
		return false;
	}

}
