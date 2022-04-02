package scinde.model.entity.enemies;

import java.util.List;
import java.util.Random;

import scinde.model.entity.Entity;
import scinde.model.utils.Position;
import scinde.model.world.World;

public class Moula extends Enemy{

	public Moula() {
		super(2, 1, new Random().nextFloat()*0.6f+0.2f, 0.1f, 10);
	}

	@Override
	public void onDeath(World world, Position pos) {
		//do nothing
	}

	@Override
	public void onHit(World world, Position pos, Entity other) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpdate(List<World> worlds) {
		// TODO Auto-generated method stub
		
	}
}
