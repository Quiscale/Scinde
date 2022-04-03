package scinde.controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import scinde.model.level.LevelMaker;
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
		
		Velocity velocityVec = LevelMaker.instance.getCurrentLevel().getPlayer().getVelocity();
		
		if(event.getEventType() == KeyEvent.KEY_PRESSED) {
			
			if(event.getCode() == KeyCode.UP) {
				velocityVec.setY(-velocity);
			}
			if(event.getCode() == KeyCode.DOWN) {
				velocityVec.setY(+velocity);
			}
			if(event.getCode() == KeyCode.LEFT) {
				velocityVec.setX(-velocity);
			}
			if(event.getCode() == KeyCode.RIGHT) {
				velocityVec.setX(+velocity);
			}
		}
		
		
	}
	
}
