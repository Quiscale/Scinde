package scinde.view.node;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import scinde.controller.ActionEnd;
import scinde.view.IHM;

public class DialogField extends Label {

	// ////////////////////////////////////////////////////////////////////////
	// Attributes
	// ////////////////////////////////////////////////////////////////////////

	// ////////////////////////////////////////////////////////////////////////
	// Constructors
	// ////////////////////////////////////////////////////////////////////////

	public DialogField() {
		
		this.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		this.setPrefSize(600, 100);
		this.setTranslateX(-300);
		this.setPadding(new Insets(20));
		this.setWrapText(true);
		this.setTextAlignment(TextAlignment.CENTER);
		this.setOpacity(0.0);
		
		this.setFont(new Font(20));
		
	}

	public ActionEnd printText(String text) {
		
		ActionEnd action = new ActionEnd();
		
		this.setText(text);
		this.setOpacity(1.0);
		this.requestFocus();
		
		this.setOnKeyReleased((e) -> {
			this.setOpacity(0.0);
			IHM.PANE.requestFocus();
			action.trigger();
		});
		
		return action;
	}
	
	// ////////////////////////////////////////////////////////////////////////
	// Methods
	// ////////////////////////////////////////////////////////////////////////

	// ////////////////////////////////////////////////////////////////////////
	// Overrides
	// ////////////////////////////////////////////////////////////////////////
}
