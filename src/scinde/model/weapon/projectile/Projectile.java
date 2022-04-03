package scinde.model.weapon.projectile;

import java.util.List;

import scinde.model.entity.Entity;
import scinde.model.entity.EntityHolder;
import scinde.model.entity.enemies.Enemy;
import scinde.model.registry.Registry;
import scinde.model.utils.Position;
import scinde.model.utils.hitbox.CircleHitbox;
import scinde.model.world.World;

public abstract class Projectile extends Entity{
	
	private int bouncesOffWallsAllowed;

	protected Projectile(int bouncesOffWallsAllowed, float bouncyNess, double velocityUnit) {
		super(0.1f, true, bouncyNess, velocityUnit, 10);
		this.bouncesOffWallsAllowed = bouncesOffWallsAllowed;
	}


	@Override
	protected boolean canDamage(Entity entity) {
		return entity instanceof Enemy;
	}
	
	public void onHitWall(World world, EntityHolder self) {
		bouncesOffWallsAllowed--;
		if(bouncesOffWallsAllowed <= 0)
		{
			self.kill(world);
		}
	}
	public String toString()
	{
		return ""+Registry.PROJECTILE.getId(this);
	}

}
