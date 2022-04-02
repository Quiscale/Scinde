package scinde.model.utils;

public class Position {
	private double x;
	private double y;
	
	public Position()
	{
		this.x = 0;
		this.y = 0;
	}

	public Position(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public void translate(Position p)
	{
		this.x += p.getX();
	}
	
	public String toString()
	{
		return "("+this.x+","+this.y+")";
	}

	public double diff(Position position) {
		return Math.sqrt(Math.pow(x-position.getX(), 2)+Math.pow(y-position.getY(), 2));
	}
}
