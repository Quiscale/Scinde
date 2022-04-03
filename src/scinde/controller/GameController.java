package scinde.controller;

import scinde.model.level.Level;
import scinde.model.level.LevelMaker;
import scinde.model.utils.Position;
import scinde.model.weapon.Weapons;
import scinde.view.IHM;
import scinde.view.group.MainPane;

public class GameController {

	// ////////////////////////////////////////////////////////////////////////
	// Attributes
	// ////////////////////////////////////////////////////////////////////////

	public GameAction[] actions;
	private int action_i;
	
	public static KeyboardController KEYBOARD;
	
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

				
				try {
					LevelMaker.instance.load("level1");
					
					new UpdateTimer();
					
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				/*IHM.PANE.SHIP.zoomCockpit()
					.setOnEnd(() -> {
						this.nextAction();
					});*/
				startGame(IHM.PANE);
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
		
		KEYBOARD = new KeyboardController();
		pane.setOnKeyPressed(KEYBOARD);
		pane.setOnKeyReleased(KEYBOARD);
		pane.requestFocus();
		LevelMaker.instance.getCurrentLevel().getPlayer().unlockLeft(Weapons.RAYGUN);
		LevelMaker.instance.getCurrentLevel().getPlayer().equipNextLeft();
		LevelMaker.instance.getCurrentLevel().update();
		pane.SHIP.showHitboxes();
		pane.SHIP.setOnMouseClicked(event -> {
			LevelMaker.instance.getCurrentLevel().getPlayer().useLeft(
					LevelMaker.instance.getCurrentLevel().getLeft(), new Position(event.getX(), event.getY()));
		});
	}
	
	public void start() {

		try {
			//LevelMaker.instance.make("level1");
			
			//new UpdateTimer();
			
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
