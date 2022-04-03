package scinde.controller;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import scinde.model.entity.EntityHolder;
import scinde.model.level.LevelMaker;
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

			LevelMaker.instance.getCurrentLevel().update();
		}
		
	}
	
}
