package scinde.controller;

import javafx.animation.AnimationTimer;
import scinde.model.level.LevelManager;
import scinde.model.utils.Velocity;
import scinde.view.IHM;

public class UpdateTimer extends AnimationTimer {
	
	public static int ELAPSED_TIME = 10;

	// ////////////////////////////////////////////////////////////////////////
	// Attributes
	// ////////////////////////////////////////////////////////////////////////

	private long lastNow;
	
	// ////////////////////////////////////////////////////////////////////////
	// Constructors
	// ////////////////////////////////////////////////////////////////////////
	
	public UpdateTimer() {
		super();
		
		this.lastNow = 0;
		
		this.start();
		
	}

	// ////////////////////////////////////////////////////////////////////////
	// Methods
	// ////////////////////////////////////////////////////////////////////////
	
	// ////////////////////////////////////////////////////////////////////////
	// Overrides
	// ////////////////////////////////////////////////////////////////////////

	@Override
	public void handle(long now) {
		
		if(now > this.lastNow +ELAPSED_TIME*1000000) { // 100ms
			this.lastNow = now;

			// Update player velocity
			Velocity velocityVec = LevelManager.level().getPlayer().getVelocity();
			double y = (KeyboardController.UP ? -2 : +2) + (KeyboardController.DOWN ? +2 : -2);
			double x = (KeyboardController.LEFT ? -2 : +2) + (KeyboardController.RIGHT ? +2 : -2);
			velocityVec.setX(x);
			velocityVec.setY(y);
			
			// Update Game & Graphics
			LevelManager.level().update();
			IHM.PANE.SHIP.update();
		}
		
	}
	
}
