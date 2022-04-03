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

	private static float velocity = 4;
	private boolean upPressed = false;
	private boolean downPressed = false;
	private boolean leftPressed = false;
	private boolean rightPressed = false;
	
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
			
			if(event.getCode() == KeyCode.UP && !upPressed) {
				upPressed = true;
				velocityVec.setX(velocityVec.getX() + velocity);
			}
			if(event.getCode() == KeyCode.DOWN && !downPressed) {
				downPressed = true;
				velocityVec.setX(velocityVec.getX() - velocity);
			}
			if(event.getCode() == KeyCode.LEFT && !leftPressed) {
				leftPressed = true;
				velocityVec.setY(velocityVec.getY() + velocity);
			}
			if(event.getCode() == KeyCode.RIGHT && !rightPressed) {
				rightPressed = true;
				velocityVec.setY(velocityVec.getY() - velocity);
			}
		}
		if(event.getEventType() == KeyEvent.KEY_RELEASED) {
			
			if(event.getCode() == KeyCode.UP) {
				upPressed = false;
				velocityVec.setX(velocityVec.getX() - velocity);
			}
			if(event.getCode() == KeyCode.DOWN) {
				downPressed = false;
				velocityVec.setX(velocityVec.getX() + velocity);
			}
			if(event.getCode() == KeyCode.LEFT) {
				leftPressed = false;
				velocityVec.setY(velocityVec.getY() - velocity);
			}
			if(event.getCode() == KeyCode.RIGHT) {
				rightPressed = false;
				velocityVec.setY(velocityVec.getY() + velocity);
			}
		}		
		
	}
	
}
