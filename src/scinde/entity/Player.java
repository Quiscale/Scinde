package scinde.entity;

import scinde.utils.hitbox.CircleHitbox;
import scinde.world.World;

public class Player extends Entity{
	
	private static final float FEET_OFFSET_X = 5;
	private static final float FEET_OFFSET_Y = 10;

	protected Player() {
		super(new CircleHitbox(20).setOffsetX(FEET_OFFSET_X).setOffsetY(FEET_OFFSET_Y));
	}

	@Override
	public void onHit(Entity other) {
		//do nothing
	}
}
