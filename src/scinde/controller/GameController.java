package scinde.controller;

import scinde.model.level.Level;
import scinde.model.level.LevelMaker;
import scinde.view.IHM;
import scinde.view.group.MainPane;

public class GameController {

	// ////////////////////////////////////////////////////////////////////////
	// Attributes
	// ////////////////////////////////////////////////////////////////////////

	public static Level LEVEL;
	public GameAction[] actions;
	private int action_i;
	
	// ////////////////////////////////////////////////////////////////////////
	// Constructors
	// ////////////////////////////////////////////////////////////////////////

	public GameController() {
		this.action_i = 0;
		this.actions = new GameAction[] {
				
			// First game action : play sound
			() -> {
				// Start menu sound
				SoundController.playMenu();
			},
			
			// Remove Menu, zoom on ship
			() -> {
				IHM.PANE.SHIP.zoomCockpit()
					.setOnEnd(() -> {
						this.nextAction();
					});
				IHM.PANE.SHIP.showHitboxes();
			},
			
			// Make the character speak
			() -> {
				IHM.PANE.DIALOG.printText("Tout se joue au clavier (appuyez sur espace pour continuer)")
					.setOnEnd(() -> {
						this.nextAction();
					});
			},
			
			// Il démarre le réacteur
			() -> {
				
			},
			
			// Temps d'attente
			() -> {
				
			},
			
			// Tremblement
			() -> {
				
			},
			
			// Arrêt du réacteur
			() -> {
				
			},
			
			// Le personnage parle
			() -> {
				
			}
		};
		
	}
	
	// ////////////////////////////////////////////////////////////////////////
	// Methods
	// ////////////////////////////////////////////////////////////////////////

	public static void startGame(MainPane pane) {
		
		KeyboardController keyboard = new KeyboardController();
		pane.setOnKeyPressed(keyboard);
		pane.setOnKeyReleased(keyboard);
		pane.requestFocus();
		
		LevelMaker maker = new LevelMaker("level1");
		
		try {
			LEVEL = maker.make();
			
			new UpdateTimer();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void start() {

		LevelMaker maker = new LevelMaker("level1");
		try {
			LEVEL = maker.make();
			
			new UpdateTimer();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		this.action_i = 0;
		this.actions[this.action_i].handle();
	}

	public void nextAction() {
		
		this.action_i += 1;
		this.actions[this.action_i].handle();
		
	}
	
	// ////////////////////////////////////////////////////////////////////////
	// Overrides
	// ////////////////////////////////////////////////////////////////////////
}
