package scinde.model.utils;

import java.math.MathContext;

public class Velocity {
	private double x;
	private double y;
	
	public Velocity(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	
	public Velocity inverse()
	{
		return new Velocity(-this.x, -this.y);
	}

	public double getMagnitude()
	{
		double magnitude = Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));
		return magnitude;
	}
	
	public Velocity toUnit()
	{
		double magnitude = getMagnitude();
		if(magnitude == 0)
		{
			return new Velocity(0, 0);
		}
		return new Velocity(x/magnitude, y/magnitude);
	}
	
	public Velocity toUnit(double velocityUnit) {
		Velocity unit = toUnit();
		unit.setX(unit.getX()*velocityUnit);
		unit.setY(unit.getY()*velocityUnit);
		return unit;
	}

	public static Velocity createVector(Position pos, Position tracker) {
		// TODO Auto-generated method stub
		return new Velocity(tracker.getX()-pos.getX(), tracker.getY() - pos.getY());
	}
	
	public static double dotProduct(Velocity v1, Velocity v2)
	{
		return v1.getX()*v2.getX()+v1.getY()*v2.getY();
	}

	public float angle(Velocity other) {
		if(this.getMagnitude() == 0 || other.getMagnitude() == 0)
		{
			return 0;
		}
		return Math.round(Math.acos(dotProduct(this, other)/(getMagnitude()*other.getMagnitude())));
	}
	
	public String toString()
	{
		return "["+x+", "+y+"]";
	}
}
