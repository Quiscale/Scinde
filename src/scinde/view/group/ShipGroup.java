package scinde.view.group;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import scinde.controller.ActionEnd;
import scinde.controller.GameController;
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
		this.scale.setPivotX(680-1820/4);
		this.scale.setPivotY(300);
		this.translate = new Translate(680-1820/4, 580);
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

	public void showHitboxes() {
		
		System.out.println("HEY");
		
		for(HitBox box : GameController.LEVEL.getLeft().getHitboxes()) {
			box.getShape().setFill(Color.RED);
			this.hitGroup.getChildren().add(box.getShape());
			System.out.println(box.getShape());
			
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
