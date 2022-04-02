package scinde.model.utils;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Position {
	private DoubleProperty x;
	private DoubleProperty y;
	
	public Position()
	{
		this.x = new SimpleDoubleProperty(0);
		this.y = new SimpleDoubleProperty(0);
	}

	public Position(double x, double y) {

		this.x = new SimpleDoubleProperty(x);
		this.y = new SimpleDoubleProperty(y);
	}

	public double getX() {
		return x.get();
	}

	public double getY() {
		return y.get();
	}
	
	public String toString()
	{
		return "("+this.x.get()+","+this.y.get()+")";
	}
	
	public DoubleProperty xProperty()
	{
		return x;
	}
	public DoubleProperty yProperty()
	{
		return y;
	}

	public double diff(Position position) {
		return Math.sqrt(Math.pow(x.get()-position.getX(), 2)+Math.pow(y.get()-position.getY(), 2));
	}
}
