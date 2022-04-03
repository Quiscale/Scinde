package scinde.view.group;

import java.util.Random;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import scinde.controller.ActionEnd;
import scinde.model.entity.EntityHolder;
import scinde.model.entity.enemies.OpenPatternFollower;
import scinde.model.entity.enemies.PatternFollower;
import scinde.model.level.LevelManager;
import scinde.model.utils.Position;
import scinde.model.utils.hitbox.HitBox;
import scinde.view.node.CharacterView;
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

		this.scale = new Scale(0.5, 0.5);
		this.scale.setPivotX(640);
		this.scale.setPivotY(820);
		this.translate = new Translate(640-1820/2, 40); //680-1820/16, 580
		this.getTransforms().addAll(scale, translate);

		// Ship background
		ShipView ship = new ShipView();
		//ship.setTranslateX(640);
		//ship.setTranslateY(360);
		this.getChildren().add(ship);
		
		// Player
		CharacterView character = new CharacterView();
		this.getChildren().add(character);
		
		// Hitbox debug
		this.hitGroup = new Group();
		//this.hitGroup.setTranslateX(640);
		//this.hitGroup.setTranslateY(360);
		this.getChildren().add(this.hitGroup);
		
	}
	
	// ////////////////////////////////////////////////////////////////////////
	// Methods
	// ////////////////////////////////////////////////////////////////////////
	
	public void showHitbox(EntityHolder entity)
	{
		Random rand = new Random();
		Color color = new Color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble(), 1);
		entity.getHitbox().getShape().setStroke(color);
		entity.getHitbox().getShape().setFill(null);
		this.hitGroup.getChildren().add(entity.getHitbox().getShape());
		if(entity.getPattern() != null)
		{
			PatternFollower pattern = entity.getPattern();
			if(pattern != null && !pattern.getPositions().isEmpty())
			{
				Position old = null;
				for(Position pos : pattern.getPositions())
				{
					Circle dot = new Circle(2);
					dot.setFill(color);
					dot.setTranslateX(pos.getX());
					dot.setTranslateY(pos.getY());
					this.hitGroup.getChildren().add(dot);
					if(old != null)
					{
						Line line = new Line(pos.getX(), pos.getY(), old.getX(), old.getY());
						line.setStroke(color);
						this.hitGroup.getChildren().add(line);
					}
					old = pos;
				}
				if(pattern.getPositions().size()>2 && !(pattern instanceof OpenPatternFollower))
				{
					Position first = pattern.getPositions().get(0);
					Position last = pattern.getPositions().get(pattern.getPositions().size()-1);
					Line line = new Line(first.getX(), first.getY(), last.getX(), last.getY());
					line.setStroke(color);
					line.setFill(color);
					this.hitGroup.getChildren().add(line);
				}
			}
		}
	}

	public void showHitboxes() {
		
		for(HitBox box : LevelManager.level().getLeft().getBlocks()) {
			box.getShape().setStroke(Color.GREEN);
			box.getShape().setFill(null);
			this.hitGroup.getChildren().add(box.getShape());
		}

		Shape player = LevelManager.level().getPlayer().getHitbox().getShape();
		player.setStroke(Color.RED);
		player.setFill(null);
		this.hitGroup.getChildren().add(player);
		
		for(EntityHolder entity : LevelManager.level().getLeft().getEntities())
		{
			System.out.println(entity.getEntity());
			showHitbox(entity);
		}
	}

	public ActionEnd zoomCockpit() {
		
		ActionEnd action = new ActionEnd();
		
		new Transition() {

			{
				this.setCycleDuration(Duration.seconds(5));
				this.setInterpolator(Interpolator.LINEAR);
				this.setOnFinished((e) -> action.trigger());
			}
			
			@Override
			protected void interpolate(double frac) {
				
				scale.setX(0.5 +frac/2);
				scale.setY(0.5 +frac/2);
			}
			
		}.play();
		
		return action;
	}
	
	// ////////////////////////////////////////////////////////////////////////
	// Overrides
	// ////////////////////////////////////////////////////////////////////////
}
