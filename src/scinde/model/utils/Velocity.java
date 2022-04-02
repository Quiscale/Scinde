package scinde.model.utils;

public class Velocity {
	private float x;
	private float y;
	
	public Velocity(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	
	public Velocity inverse()
	{
		return new Velocity(-this.x, -this.y);
	}
	
	
}
