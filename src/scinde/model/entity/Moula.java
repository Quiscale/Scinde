package scinde.model.entity;

import scinde.model.utils.hitbox.CircleHitbox;
import scinde.model.utils.hitbox.HitBox;
import scinde.model.world.World;

public class Moula extends Entity{

	protected Moula() {
		super(new CircleHitbox(10));
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
