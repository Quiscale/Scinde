package scinde.model.utils.hitbox;

import java.util.List;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class CustomHitbox extends HitBox{

	private Shape finalShape;
	public CustomHitbox(List<HitBox> composites)
	{
		if(!composites.isEmpty())
		{
			Shape shape = composites.get(0).getShape();
			for(int i = 1; i<composites.size(); i++)
			{
				Shape next = composites.get(i).getShape();
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
