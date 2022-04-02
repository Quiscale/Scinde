package scinde.view.group;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import scinde.Main;
import scinde.view.IHM;
import scinde.view.node.PlayButton;

public class MenuPane extends Pane {

	// ////////////////////////////////////////////////////////////////////////
	// Attributes
	// ////////////////////////////////////////////////////////////////////////

	boolean hey = true;

	// ////////////////////////////////////////////////////////////////////////
	// Constructors
	// ////////////////////////////////////////////////////////////////////////

	public MenuPane() {
		super();
		MenuPane menu = this;
		
		this.setTranslateX(640);
		
		PlayButton button = new PlayButton();
		button.setTranslateY(130);
		this.getChildren().add(button);
		
		this.setOnMouseClicked((event) -> {
			
			new Transition() {

				{
					this.setCycleDuration(Duration.seconds(2));
					this.setInterpolator(Interpolator.LINEAR);
					this.setOnFinished((action) -> {
						IHM.PANE.getChildren().remove(menu);
					});
				}
				
				@Override
				protected void interpolate(double frac) {
					
					menu.setOpacity(1 -frac);
					
				}
				
			}.play();
			
			Main.GAME.nextAction();
		});
	}
	
	// ////////////////////////////////////////////////////////////////////////
	// Methods
	// ////////////////////////////////////////////////////////////////////////

	// ////////////////////////////////////////////////////////////////////////
	// Overrides
	// ////////////////////////////////////////////////////////////////////////
}
