package scinde.model.entity.enemies;

import java.util.Random;

import scinde.model.utils.hitbox.CircleHitbox;
import scinde.model.world.World;

public class Moula extends Enemy{

	public Moula() {
		super(1, new Random().nextFloat()*0.6f+0.2f, new CircleHitbox(10));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onDeath(World world) {
		//do nothing
	}
}
