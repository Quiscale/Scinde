package scinde.model.entity;

import java.util.List;

import scinde.model.registry.Registry;
import scinde.model.utils.Position;
import scinde.model.utils.hitbox.CircleHitbox;
import scinde.model.utils.hitbox.HitBox;
import scinde.model.world.World;

public abstract class Entity{
	
	protected float maxLifePoints;
	protected boolean canMove;
	protected double velocityUnit;
	protected float bouncyness;
	double hitboxSize;
	
	protected Entity(float maxLifePoints, boolean canMove, float bouncyNess, double velocityUnit, double hitboxSize)
	{
		this.maxLifePoints = maxLifePoints;
		this.canMove = canMove;
		this.velocityUnit = velocityUnit;
		this.bouncyness = bouncyNess;
		this.hitboxSize = hitboxSize;
	}
	
	public float getMaxLifePoints()
	{
		return maxLifePoints;
	}
	
	public double getHitboxSize()
	{
		return hitboxSize;
	}
	
	public void onHitWall(World world, EntityHolder self) {}
	
	public abstract float getDamage();
	
	public abstract void onHitBy(World world, Position pos, EntityHolder self, EntityHolder other);
	
	public abstract void onHit(World world, Position pos, EntityHolder self, EntityHolder other);
	
	public abstract void onDeath(World world, Position pos, EntityHolder self);
	
	public abstract void onUpdate(List<World> worlds, EntityHolder self);

	protected abstract boolean canDamage(Entity entity);
	
	public String toString()
	{
		return ""+Registry.ENTITY.getId(this);
	}
}
