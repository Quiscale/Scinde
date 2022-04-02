package scinde.view.node;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import scinde.Main;

public class ShipView extends ImageView {

	// ////////////////////////////////////////////////////////////////////////
	// Attributes
	// ////////////////////////////////////////////////////////////////////////

	Translate translate;
	Scale scale;
	
	// ////////////////////////////////////////////////////////////////////////
	// Constructors
	// ////////////////////////////////////////////////////////////////////////

	public ShipView() {
		super();
		
		Image img = Main.LOADER.get("vaisseau").asImage();
		this.setImage(img);
		
		this.translate = new Translate(-img.getWidth()/2, 0);
		this.scale = new Scale(1, 1);
		
		this.getTransforms().addAll(scale, translate);
		
	}
	
	// ////////////////////////////////////////////////////////////////////////
	// Methods
	// ////////////////////////////////////////////////////////////////////////

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
				
				translate.setY(-frac *50);
			}
			
		}.play();
		
	}
	
	// ////////////////////////////////////////////////////////////////////////
	// Overrides
	// ////////////////////////////////////////////////////////////////////////
}
