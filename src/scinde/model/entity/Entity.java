package scinde.model.entity;

import java.util.List;

import scinde.model.level.Level;
import scinde.model.utils.IUpdatable;
import scinde.model.utils.Position;
import scinde.model.utils.Velocity;
import scinde.model.utils.hitbox.HitBox;
import scinde.model.world.World;

public abstract class Entity implements IUpdatable{
	private HitBox hitbox;
	private Position pos;
	private Velocity velocity;
	private float lifePoints;
	
	protected Entity(HitBox box)
	{
		this(new Position(), box);
	}
	
	public void hit(float damage)
	{
		this.lifePoints -= damage;
	}
	
	public float getLifePoints()
	{
		return lifePoints;
	}
	
	protected Entity(Position pos, HitBox box)
	{
		hitbox = box;
		this.hitbox.init();
		this.pos = pos;
		hitbox.moveTo(pos);
		velocity = new Velocity(0, 0);
	}
	
	public void setVelocity(Velocity velocity)
	{
		this.velocity = velocity;
	}
	
	public void setPosition(Position pos)
	{
		this.pos = pos;
		this.hitbox.moveTo(pos);		
	}
	
	protected void move()
	{
		Position pos = new Position(this.pos.getX()+velocity.getX(), this.pos.getY()+velocity.getY());
		this.pos = pos;
		this.hitbox.moveTo(pos);
	}

	public HitBox getHitbox() {
		return hitbox;
	}

	public Position getPos() {
		return pos;
	}
	
	public Velocity getVelocity()
	{
		return velocity;
	}
	
	public void update(List<World> worlds)
	{
		this.move();
		for(World world : worlds)
		{
			if(!world.entityCanMove(this))
			{
				setVelocity(getVelocity().inverse());
				move(); 
				setVelocity(getVelocity().inverse());
				return;
			}
		}
	}
	
	public abstract void onHit(World world, Entity other);
	
	public abstract void onDeath(World world);
}
