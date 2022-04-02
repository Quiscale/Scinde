package scinde.utils.hitbox;

import java.util.List;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class CircleHitbox extends HitBox{
	
	private float radius;
	
	public CircleHitbox(float radius)
	{
		super();
		this.radius = radius;
	}
	
	@Override
	protected Shape createShape()
	{
		return new Circle(radius);
	}
}
