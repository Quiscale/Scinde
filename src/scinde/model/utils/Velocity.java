package scinde.model.utils;

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
	
	public Velocity rotate(double angle)
	{
		return new Velocity(x*Math.cos(angle) - y*Math.sin(angle),
				x * Math.sin(angle) + y * Math.cos(angle));
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

	public double angle(Velocity other) {
		if(this.getMagnitude() == 0 || other.getMagnitude() == 0)
		{
			return 0;
		}
		return Math.acos(dotProduct(this, other)/(getMagnitude()*other.getMagnitude()));
	}
	
	public String toString()
	{
		return "["+x+", "+y+"]";
	}

	public Velocity mult(double f) {
		return new Velocity(this.x * f, this.y * f);
	}

	public Velocity div(float f) {

		return new Velocity(this.x / f, this.y / f);
	}

	public Velocity normal() {
		return new Velocity(-y, -x);
	}

	public static Velocity add(Velocity a, Velocity b) {
		return new Velocity(a.getX()+b.getX(), a.getY()+b.getY());
	}

	public double absoluteAngle() {
		Velocity reference = new Velocity(1, 0);
		return reference.angle(this);
	}
	
	public static Velocity fromAbsoluteAngle(double angle)
	{
		double x = Math.cos(angle);
		double y = Math.sin(angle);
		return new Velocity(x, y);
	}
}
