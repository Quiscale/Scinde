package scinde.entity;

import scinde.utils.IUpdatable;
import scinde.utils.Position;
import scinde.utils.Velocity;
import scinde.utils.hitbox.HitBox;
import scinde.world.World;

public abstract class Entity implements IUpdatable{
	HitBox hitbox;
	Position pos;
	Velocity velocity;
	
	protected Entity(HitBox box)
	{
		this(new Position(), box);
	}
	
	protected Entity(Position pos, HitBox box)
	{
		hitbox = box;
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
	
	private void move()
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
	
	public void update(World world)
	{
		this.move();
		if(!world.entityCanMove(this))
		{
			setVelocity(getVelocity().inverse());
			move(); 
			setVelocity(getVelocity().inverse());
		}
	}
	
	public abstract void onHit(Entity other);
}
