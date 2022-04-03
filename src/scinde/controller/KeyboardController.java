package scinde.controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import scinde.model.level.LevelManager;
import scinde.model.utils.Velocity;

public class KeyboardController implements EventHandler<KeyEvent> {

	// ////////////////////////////////////////////////////////////////////////
	// Attributes
	// ////////////////////////////////////////////////////////////////////////

	private static float velocity = 2;
	public static boolean UP = false;
	public static boolean DOWN = false;
	public static boolean LEFT = false;
	public static boolean RIGHT = false;
	
	// ////////////////////////////////////////////////////////////////////////
	// Constructors
	// ////////////////////////////////////////////////////////////////////////

	// ////////////////////////////////////////////////////////////////////////
	// Methods
	// ////////////////////////////////////////////////////////////////////////

	// ////////////////////////////////////////////////////////////////////////
	// Overrides
	// ////////////////////////////////////////////////////////////////////////

	@Override
	public void handle(KeyEvent event) {
		
		KeyCode code = event.getCode();
		
		if(code == KeyCode.UP || code == KeyCode.Z) {
			KeyboardController.UP = event.getEventType() == KeyEvent.KEY_PRESSED;
		}
		if(code == KeyCode.DOWN || code == KeyCode.S) {
			KeyboardController.DOWN = event.getEventType() == KeyEvent.KEY_PRESSED;
		}
		if(code == KeyCode.LEFT || code == KeyCode.Q) {
			KeyboardController.LEFT = event.getEventType() == KeyEvent.KEY_PRESSED;
		}
		if(code == KeyCode.RIGHT || code == KeyCode.D) {
			KeyboardController.RIGHT = event.getEventType() == KeyEvent.KEY_PRESSED;
		}
		
	}
	
}
