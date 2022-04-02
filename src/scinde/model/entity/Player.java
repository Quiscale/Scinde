package scinde.model.entity;

import scinde.model.world.World;
import scinde.utils.hitbox.CircleHitbox;

public class Player extends Entity{
	
	private static final float FEET_OFFSET_X = 5;
	private static final float FEET_OFFSET_Y = 10;

	protected Player() {
		super(new CircleHitbox(20).setOffsetX(FEET_OFFSET_X).setOffsetY(FEET_OFFSET_Y));
	}

	@Override
	public void onHit(World world, Entity other) {
		//do nothing
	}

	@Override
	public void onDeath(World world) {
		//what do we do ?
	}
}
