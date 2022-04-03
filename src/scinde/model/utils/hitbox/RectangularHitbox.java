package scinde.model.utils.hitbox;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class RectangularHitbox extends HitBox{
	
	private float width;
	private float height;
	
	public RectangularHitbox(float width, float height)
	{
		this.width = width;
		this.height = height;
	}

	@Override
	protected Shape createShape() {
		return new Rectangle(width, height);
	}
}
