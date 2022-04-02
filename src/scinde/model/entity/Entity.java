package scinde.model.entity;

import java.util.List;

import scinde.model.utils.hitbox.HitBox;
import scinde.model.world.World;

public abstract class Entity{
	
	protected float maxLifePoints;
	protected boolean canMove;
	protected double velocityUnit;
	protected float bouncyness;
	
	protected Entity(float maxLifePoints, boolean canMove, float bouncyNess, double velocityUnit)
	{
		this.maxLifePoints = maxLifePoints;
		this.canMove = canMove;
		this.velocityUnit = velocityUnit;
		this.bouncyness = bouncyNess;
	}
	
	public float getMaxLifePoints()
	{
		return maxLifePoints;
	}
	
	public void onHitWall() {}
	
	public abstract float getDamage();
	
	public abstract void onHit(World world, Entity other);
	
	public abstract void onDeath(World world);
	
	public abstract void onUpdate(List<World> worlds);

	public abstract HitBox provideHitbox();

	protected abstract boolean canDamage(Entity entity);
}
