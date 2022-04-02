package scinde.model.utils.hitbox;

import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import javafx.scene.shape.Shape;
import scinde.model.utils.Position;

public abstract class HitBox {
	private Shape shape;
	private boolean enabled;
	
	public void init()
	{
		this.shape = createShape();
		enabled = true;
	}
	
	public Shape getShape()
	{
		return this.shape;
	}
	
	public void setEnabled(boolean value)
	{
		this.enabled = value;
	}
	
	public boolean isEnabled()
	{
		return enabled;
	}
	
	public void rotate(float angle)
	{
		this.shape.setRotate(angle);
	}
	
	protected abstract Shape createShape();
	
	public boolean overlap(HitBox box)
	{
		Shape other = box.getShape();
		return !((Path)Shape.intersect(this.shape, other)).getElements().isEmpty();
	}
	
	public void moveTo(Position pos)
	{
		shape.setTranslateX(pos.getX());
		shape.setTranslateY(pos.getY());
	}
	
	public String toString()
	{
		return "[("+this.shape.getTranslateX()+":"+this.shape.getTranslateY()+"), "+this.shape.getRotate()+"] / "+this.shape;
	}
}
