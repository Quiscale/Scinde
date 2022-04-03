package scinde.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import scinde.Main;
import scinde.view.group.MainPane;

public class IHM extends Application {

	// ////////////////////////////////////////////////////////////////////////
	// Attributes
	// ////////////////////////////////////////////////////////////////////////

	public static MainPane PANE;
	
	// ////////////////////////////////////////////////////////////////////////
	// Constructors
	// ////////////////////////////////////////////////////////////////////////

	// ////////////////////////////////////////////////////////////////////////
	// Methods
	// ////////////////////////////////////////////////////////////////////////

	@Override
	public void start(Stage stage) throws Exception {
		
		new MainPane();
		// IHM.PANE is set in MainPane
		
		stage.setScene(new Scene(IHM.PANE, 1280, 720));
		stage.show();
		
		Main.GAME.start();
	}

	// ////////////////////////////////////////////////////////////////////////
	// Overrides
	// ////////////////////////////////////////////////////////////////////////
}
