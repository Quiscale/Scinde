package scinde.weapon;

import scinde.world.World;

public class Raygun extends Weapon {

	protected Raygun() {
		super(true, new WeaponStats(1, 0.4f, 10));
	}

	@Override
	public void use(World world) {
		
	}
	
}
