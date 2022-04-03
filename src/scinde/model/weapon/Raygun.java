package scinde.model.weapon;

import scinde.model.entity.EntityHolder;
import scinde.model.weapon.projectile.Projectiles;

public class Raygun extends Weapon {

	protected Raygun() {
		super(5000, 500, 5);
		// TODO Auto-generated constructor stub
	}

	@Override
	public EntityHolder getProjectile() {
		// TODO Auto-generated method stub
		return new EntityHolder(Projectiles.RAIL);
	}
	
}
