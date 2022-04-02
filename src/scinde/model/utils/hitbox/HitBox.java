package scinde.model.utils.hitbox;

import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import scinde.model.utils.Position;

public abstract class HitBox {
	private Shape shape;
	private boolean enabled;
	private int id;
	private static int currentId = 0;
	private Text idText;
	
	public void init()
	{
		this.shape = createShape();
		enabled = true;
		this.id = currentId++;
		idText = new Text(id+"");
	}
	
	public int getId()
	{
		return id;
	}
	
	public Text getIdDisplay()
	{
		return idText;
	}
	
	public Shape getShape()
	{
		return this.shape;
	}
	
	public void setEnabled(boolean value)
	{
		this.enabled = value;
	}
	
	public boolean isEnabled()
	{
		return enabled;
	}
	
	public void rotate(float angle)
	{
		this.shape.setRotate(angle);
	}
	
	protected abstract Shape createShape();
	
	public boolean overlap(HitBox box)
	{
		Shape other = box.getShape();
		return !((Path)Shape.intersect(this.shape, other)).getElements().isEmpty();
	}
	
	public void moveTo(Position pos)
	{
		idText.setTranslateX(pos.getX()-idText.getBoundsInLocal().getWidth()/2);
		idText.setTranslateY(pos.getY()+idText.getBoundsInLocal().getHeight()/2);
		shape.setTranslateX(pos.getX());
		shape.setTranslateY(pos.getY());
	}
	
	public String toString()
	{
		return "[("+this.shape.getTranslateX()+":"+this.shape.getTranslateY()+"), "+this.shape.getRotate()+"] / "+this.shape;
	}
}
