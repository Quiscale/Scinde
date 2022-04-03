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
		
		Velocity velocityVec = LevelManager.level().getPlayer().getVelocity();
		
		if(event.getEventType() == KeyEvent.KEY_PRESSED) {
			KeyCode code = event.getCode();
			
			if(code == KeyCode.UP || code == KeyCode.Z) {
				velocityVec.setY(-velocity);
			}
			if(code == KeyCode.DOWN || code == KeyCode.S) {
				velocityVec.setY(+velocity);
			}
			if(code == KeyCode.LEFT || code == KeyCode.Q) {
				velocityVec.setX(-velocity);
			}
			if(code == KeyCode.RIGHT || code == KeyCode.D) {
				velocityVec.setX(+velocity);
			}
		}
		
		
	}
	
}
