package scinde.view.group;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import scinde.controller.GameTransition;
import scinde.view.node.ShipView;
import scinde.view.node.SpaceBackground;

public class MainPane extends Pane {

	// ////////////////////////////////////////////////////////////////////////
	// Attributes
	// ////////////////////////////////////////////////////////////////////////
	
	// ////////////////////////////////////////////////////////////////////////
	// Constructors
	// ////////////////////////////////////////////////////////////////////////

	public MainPane() {
		super();
		
		this.getChildren().add(new SpaceBackground());
		
		ShipView ship = new ShipView();
		ship.setTranslateX(640);
		ship.setTranslateY(400);
		
		MenuPane menu = new MenuPane();
		menu.setOnMouseClicked((event) -> {
			
			new Transition() {

				{
					this.setCycleDuration(Duration.seconds(2));
					this.setInterpolator(Interpolator.LINEAR);
					this.setOnFinished((action) -> {
						getChildren().remove(menu);
					});
				}
				
				@Override
				protected void interpolate(double frac) {
					
					menu.setOpacity(1 -frac);
					
				}
				
			}.play();
			
			GameTransition.startGame(ship);
			
		});
		
		this.getChildren().addAll(ship, menu);

	}
	
	// ////////////////////////////////////////////////////////////////////////
	// Methods
	// ////////////////////////////////////////////////////////////////////////
	
	// ////////////////////////////////////////////////////////////////////////
	// Overrides
	// ////////////////////////////////////////////////////////////////////////
}
