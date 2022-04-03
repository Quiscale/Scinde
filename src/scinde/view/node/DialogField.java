package scinde.view.node;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import scinde.controller.ActionEnd;
import scinde.view.IHM;

public class DialogField extends Group {

	// ////////////////////////////////////////////////////////////////////////
	// Attributes
	// ////////////////////////////////////////////////////////////////////////

	// ////////////////////////////////////////////////////////////////////////
	// Constructors
	// ////////////////////////////////////////////////////////////////////////

	public DialogField() {
		super();
	}

	public ActionEnd printText(String text) {

		ActionEnd action = new ActionEnd();
		Label label = new Label(text);
		
		label.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		label.setPrefSize(600, 100);
		label.setTranslateX(-300);
		label.setPadding(new Insets(20));
		label.setWrapText(true);
		label.setAlignment(Pos.CENTER);
		label.setFont(new Font(20));
		
		label.setOnMouseClicked((e) -> {
			this.getChildren().remove(label);
			IHM.PANE.requestFocus();
			action.trigger();
		});
		
		this.getChildren().add(label);
		
		return action;
	}
	
	// ////////////////////////////////////////////////////////////////////////
	// Methods
	// ////////////////////////////////////////////////////////////////////////

	// ////////////////////////////////////////////////////////////////////////
	// Overrides
	// ////////////////////////////////////////////////////////////////////////
}
