package scinde.utils.hitbox;

import java.util.List;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class CustomHitbox extends HitBox{

	private Shape finalShape;
	public CustomHitbox(List<HitBox> composites)
	{
		if(!composites.isEmpty())
		{
			Shape shape = composites.remove(0).getShape();
			while(!composites.isEmpty())
			{
				Shape next = composites.remove(0).getShape();
				shape = Shape.union(shape, next);
			}
			finalShape = shape;
		}
		else
		{
			finalShape = new Circle(0);
		}
	}
	@Override
	protected Shape createShape() {
		return finalShape;
	}

}
