package scinde.view.node;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import scinde.Main;
import scinde.model.entity.EntityHolder;

public class CharacterView extends ImageView {

	// ////////////////////////////////////////////////////////////////////////
	// Attributes
	// ////////////////////////////////////////////////////////////////////////

	// ////////////////////////////////////////////////////////////////////////
	// Constructors
	// ////////////////////////////////////////////////////////////////////////

	public CharacterView() {
		super();
		
		Image img = Main.LOADER.get("character").asImage();
		this.setImage(img);
		
		/*EntityHolder player = Main.GAME.LEVEL.getPlayer();

		this.xProperty().bind(player.getPos().xProperty());
		this.yProperty().bind(player.getPos().yProperty());*/
	}
	
	// ////////////////////////////////////////////////////////////////////////
	// Methods
	// ////////////////////////////////////////////////////////////////////////

	// ////////////////////////////////////////////////////////////////////////
	// Overrides
	// ////////////////////////////////////////////////////////////////////////
}
