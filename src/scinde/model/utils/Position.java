package scinde.model.utils;

public class Position {
	private float x;
	private float y;
	
	public Position()
	{
		this.x = 0;
		this.y = 0;
	}

	public Position(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
	public void translate(Position p)
	{
		this.x += p.getX();
	}
}
