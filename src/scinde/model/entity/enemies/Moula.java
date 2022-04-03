package scinde.model.entity.enemies;

import java.util.List;
import java.util.Random;

import scinde.model.entity.Entity;
import scinde.model.entity.EntityHolder;
import scinde.model.utils.Position;
import scinde.model.world.World;

public class Moula extends Enemy{

	public Moula() {
		super(2, 1, 1f, 0.4f, 25);
	}

	@Override
	public void onDeath(World world, Position pos, EntityHolder self) {
		//do nothing
	}

	@Override
	public void onHitBy(World world, Position pos, EntityHolder self, EntityHolder other) {
		System.out.println("lp : "+self.getLifePoints()+"/"+self.getEntity().getMaxLifePoints());
	}

	@Override
	public void onUpdate(List<World> worlds, EntityHolder self) {
		
	}

	@Override
	public void onHit(World world, Position pos, EntityHolder self, EntityHolder other) {
		
	}
}
