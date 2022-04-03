package scinde.model.utils.hitbox;

import java.util.List;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import scinde.model.utils.Position;

public class PolygonHitbox extends HitBox{

	private List<Position> points;
	
	public PolygonHitbox(List<Position> points)
	{
		this.points = points;
	}
	
	@Override
	protected Shape createShape() {
		Polygon shape = new Polygon();
		for(Position pos : points)
		{
			shape.getPoints().add(pos.getX());
			shape.getPoints().add(pos.getY());
		}
		return shape;
	}
	
}
