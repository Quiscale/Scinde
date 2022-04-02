package scinde.view.node;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import scinde.Main;

public class SpaceBackground extends Group {

	// ////////////////////////////////////////////////////////////////////////
	// Attributes
	// ////////////////////////////////////////////////////////////////////////
	
	private static Transition transition;
	private static double height;
	private static Group group;
	private static Node effect;
	
	// ////////////////////////////////////////////////////////////////////////
	// Constructors
	// ////////////////////////////////////////////////////////////////////////

	public SpaceBackground() {
		super();
		
		Group subGroup = new Group();
		
		Image backgroundImage = Main.LOADER.get("fond-espace").asImage();
		ImageView view1 = new ImageView(backgroundImage);
		ImageView view2 = new ImageView(backgroundImage);
		view2.setTranslateY(-backgroundImage.getHeight());
		
		Image speedImage = Main.LOADER.get("fond-supraluminique").asImage();
		ImageView view3 = new ImageView(speedImage);
		view3.getTransforms().addAll(new Scale(1.1, 1.1), new Translate(-64, -36));
		view3.setOpacity(0.0);
		
		subGroup.getChildren().addAll(view1, view2);
		this.getChildren().addAll(subGroup, view3);

		SpaceBackground.group = subGroup;
		SpaceBackground.height = backgroundImage.getHeight();
		SpaceBackground.effect = view3;
		
		this.setNormal();
		
	}
	
	// ////////////////////////////////////////////////////////////////////////
	// Methods
	// ////////////////////////////////////////////////////////////////////////
	
	public void setNormal() {
		
		Transition transition = new Transition() {

			{
				this.setCycleCount(Transition.INDEFINITE);
				this.setCycleDuration(Duration.seconds(30));
				this.setInterpolator(Interpolator.LINEAR);
			}
			
			@Override
			protected void interpolate(double frac) {

				SpaceBackground.group.setTranslateY(frac*SpaceBackground.height);
				SpaceBackground.effect.setTranslateX(Math.cos(frac*2239));
				SpaceBackground.effect.setTranslateY(Math.cos(frac*2671));
			}
		};
		transition.play();
		SpaceBackground.transition = transition;
		
	}
	
	public static void goToSupraluminic() {
		System.out.println("SUPRA");
		Transition transition = new Transition() {

			double originalHeight;
			
			{
				originalHeight = SpaceBackground.height;
				this.setCycleDuration(Duration.seconds(10));
				this.setInterpolator(Interpolator.LINEAR);
			}
			
			@Override
			protected void interpolate(double frac) {
				SpaceBackground.transition.setRate(1 +frac*99);
				SpaceBackground.effect.setOpacity(frac);
				SpaceBackground.group.setScaleY(1 +frac*5);
				SpaceBackground.height = originalHeight*(1 +frac*5);
			}
		};
		transition.play();
		
	}
	
	public static void goToNormal() {
		System.out.println("NORMAL");
		Transition transition = new Transition() {

			double originalHeight;

			{
				originalHeight = SpaceBackground.height /6;
				this.setCycleDuration(Duration.seconds(3));
				this.setInterpolator(Interpolator.LINEAR);
			}
			
			@Override
			protected void interpolate(double frac) {
				SpaceBackground.transition.setRate(100 -frac*99);
				SpaceBackground.effect.setOpacity(1 -frac);
				SpaceBackground.group.setScaleY(6 -frac*5);
				SpaceBackground.height = originalHeight*(6 -frac*5);
			}
		};
		transition.play();
		
	}
	
	// ////////////////////////////////////////////////////////////////////////
	// Overrides
	// ////////////////////////////////////////////////////////////////////////
	
}
