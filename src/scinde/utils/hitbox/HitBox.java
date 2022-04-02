package scinde.utils.hitbox;

import javafx.scene.shape.Shape;
import scinde.utils.Position;

public abstract class HitBox {
	private Shape shape;
	
	protected HitBox()
	{
		this.shape = createShape();
	}
	
	public Shape getShape()
	{
		return this.shape;
	}
	
	protected abstract Shape createShape();
	
	public boolean overlap(HitBox box)
	{
		Shape other = box.getShape();
		return shape.intersects(other.getBoundsInLocal());
	}
	
	public void moveTo(Position pos)
	{
		shape.setTranslateX(pos.getX());
		shape.setTranslateY(pos.getY());
	}
	
	public boolean contains(HitBox box)
	{
		Shape other = box.getShape();
		return shape.getBoundsInLocal().contains(other.getBoundsInLocal());
	}
}
