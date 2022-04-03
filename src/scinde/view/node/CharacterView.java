package scinde.view.node;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Translate;
import scinde.Main;
import scinde.model.level.LevelManager;

public class CharacterView extends ImageView {

	// ////////////////////////////////////////////////////////////////////////
	// Attributes
	// ////////////////////////////////////////////////////////////////////////

	private double originalX;
	private double originalY;
	private Translate originalDiff;
	
	// ////////////////////////////////////////////////////////////////////////
	// Constructors
	// ////////////////////////////////////////////////////////////////////////

	public CharacterView() {
		super();
		
		this.originalX = 0;
		this.originalY = 0;
		this.originalDiff = new Translate(0, 0);
		
		Image img = Main.LOADER.get("character").asImage();
		this.setImage(img);
		
		this.setPreserveRatio(true);
		this.setFitWidth(90);
	}
	
	// ////////////////////////////////////////////////////////////////////////
	// Methods
	// ////////////////////////////////////////////////////////////////////////

	public void update() {
		
		double x = LevelManager.level().getPlayer().getPos().getX() -45;
		double y = LevelManager.level().getPlayer().getPos().getY() -45;

		this.setTranslateX(x);
		this.setTranslateY(y);
		this.originalDiff.setX(-(x -this.originalX));
		this.originalDiff.setY(-(y -this.originalY));
		
	}
	
	public void saveOriginal() {
		this.originalX = this.getTranslateX();
		this.originalY = this.getTranslateY();
		this.originalDiff.setX(0);
		this.originalDiff.setY(0);
	}
	
	public Translate getTranslateDiff() {
		return this.originalDiff;
	}

	// ////////////////////////////////////////////////////////////////////////
	// Overrides
	// ////////////////////////////////////////////////////////////////////////
}
