package scinde.utils.hitbox;

import org.w3c.dom.css.Rect;

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
