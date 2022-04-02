package scinde.view.group;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import scinde.controller.GameController;
import scinde.model.entity.Entity;
import scinde.model.entity.enemies.Enemy;
import scinde.model.utils.Position;
import scinde.model.utils.hitbox.HitBox;
import scinde.view.node.ShipView;

public class ShipGroup extends Group {

	// ////////////////////////////////////////////////////////////////////////
	// Attributes
	// ////////////////////////////////////////////////////////////////////////

	Scale scale;
	Translate translate;
	Group hitGroup;
	
	// ////////////////////////////////////////////////////////////////////////
	// Constructors
	// ////////////////////////////////////////////////////////////////////////

	public ShipGroup() {

		this.scale = new Scale(1, 1);
		this.scale.setPivotX(479 +240/2);
		this.scale.setPivotY(400);
		this.translate = new Translate(-240, 0);
		this.getTransforms().addAll(scale, translate);

		ShipView ship = new ShipView();
		ship.setTranslateX(640);
		ship.setTranslateY(360);
		this.getChildren().add(ship);
		
		this.hitGroup = new Group();
		this.hitGroup.setTranslateX(640);
		this.hitGroup.setTranslateY(360);
		this.getChildren().add(this.hitGroup);
		
	}
	
	// ////////////////////////////////////////////////////////////////////////
	// Methods
	// ////////////////////////////////////////////////////////////////////////

	public void showHitboxes() {
		
		System.out.println("HEY");
		
		for(HitBox box : GameController.LEVEL.getLeft().getHitboxes()) {
			box.getShape().setStroke(Color.GREEN);
			box.getShape().setFill(null);
			this.hitGroup.getChildren().add(box.getShape());
		}
		for(Entity entity : GameController.LEVEL.getLeft().getEntities())
		{
			if(entity instanceof Enemy enemy)
			{
				if(!enemy.getPattern().isEmpty())
				{
					Position old = null;
					for(Position pos : enemy.getPattern())
					{
						Circle dot = new Circle(2);
						dot.setFill(Color.YELLOW);
						dot.setTranslateX(pos.getX());
						dot.setTranslateY(pos.getY());
						this.hitGroup.getChildren().add(dot);
						if(old != null)
						{
							Line line = new Line(pos.getX(), pos.getY(), old.getX(), old.getY());
							line.setStroke(Color.YELLOW);
							this.hitGroup.getChildren().add(line);
						}
						old = pos;
					}
					if(enemy.getPattern().size()>2)
					{
						Position first = enemy.getPattern().get(0);
						Position last = enemy.getPattern().get(enemy.getPattern().size()-1);
						Line line = new Line(first.getX(), first.getY(), last.getX(), last.getY());
						line.setStroke(Color.YELLOW);
						this.hitGroup.getChildren().add(line);
					}
				}
			}
		}
	}

	public void zoomCockpit() {
		
		new Transition() {

			{
				this.setCycleDuration(Duration.seconds(5));
				this.setInterpolator(Interpolator.LINEAR);
			}
			
			@Override
			protected void interpolate(double frac) {
				
				scale.setX(1 +frac);
				scale.setY(1 +frac);
			}
			
		}.play();
		
	}
	
	// ////////////////////////////////////////////////////////////////////////
	// Overrides
	// ////////////////////////////////////////////////////////////////////////
}
