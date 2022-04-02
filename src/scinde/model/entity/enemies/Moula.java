package scinde.model.entity.enemies;

import scinde.model.entity.Entity;
import scinde.model.entity.player.Player;
import scinde.model.utils.hitbox.CircleHitbox;
import scinde.model.world.World;

public class Moula extends Enemy{

	public Moula() {
		super(0.8f, new CircleHitbox(10));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onHit(World world, Entity other) {
		if(other instanceof Player player)
		{
			player.hit(1);
		}
	}

	@Override
	public void onDeath(World world) {
		//do nothing
	}

}
