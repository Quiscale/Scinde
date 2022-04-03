package scinde.controller;

import scinde.view.IHM;

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
				new UpdateTimer();
			},
			
			// Remove Menu, zoom on ship
			() -> {

				IHM.PANE.SHIP.zoomCockpit()
					.setOnEnd(() -> {
						this.nextAction();
					});
				
			},
			
			// Make the character speak
			() -> {
				IHM.PANE.DIALOG.printText("Pour passer le texte, cliquez avec la souris")
					.setOnEnd(() -> {
						this.nextAction();
					});
			},
			() -> {
				IHM.PANE.DIALOG.printText("\"Bon, il serait temps d'aller sur la planète Sc1D. J'ai pas envie d'être en retard pour le tournois !\"")
					.setOnEnd(() -> {
						this.nextAction();
					});
			},
			() -> {
				IHM.PANE.DIALOG.printText("\"Activons le moteur Supraluminique. C'est pas tout, mais il y a 16 années lumières à parcourir...\"")
					.setOnEnd(() -> {
						this.nextAction();
					});
			},
			
			// Il démarre le réacteur
			() -> {
				IHM.PANE.SHIP.followCharacter();
				
				KEYBOARD = new KeyboardController();
				IHM.PANE.setOnKeyPressed(KEYBOARD);
				IHM.PANE.setOnKeyReleased(KEYBOARD);
				IHM.PANE.requestFocus();
			},
			
			// Temps d'attente
			
			// Tremblement
			() -> {
				
			},
			
			// Arrêt du réacteur
			() -> {
				
			},
			
			// Le personnage parle
			() -> {
				
			},
			
		};
		
	}
	
	// ////////////////////////////////////////////////////////////////////////
	// Methods
	// ////////////////////////////////////////////////////////////////////////

	public static void startGame() {
		
		/*LevelManager.level().getPlayer().unlockLeft(Weapons.RAYGUN);
		LevelManager.level().getPlayer().equipNextLeft();
		LevelManager.level().update();
		pane.SHIP.setOnMouseClicked(event -> {
			LevelManager.level().getPlayer().useLeft(
					LevelManager.level().getLeft(), new Position(event.getX(), event.getY()));
		});*/
	}
	
	public void start() {

		this.action_i = 0;
		this.actions[this.action_i].handle();
		
	}

	public void nextAction() {
		
		this.action_i += 1;
		this.actions[this.action_i].handle();
		
	}

	public void startSuperluminal() {
		if(action_i == 2)
			nextAction();
	}
	
	// ////////////////////////////////////////////////////////////////////////
	// Overrides
	// ////////////////////////////////////////////////////////////////////////
}
