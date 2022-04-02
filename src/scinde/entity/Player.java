package scinde.entity;

import scinde.utils.hitbox.CircleHitbox;

public class Player extends Entity{

	protected Player() {
		super(new CircleHitbox(20));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onHit(Entity other) {
		//do nothing
	}

}
