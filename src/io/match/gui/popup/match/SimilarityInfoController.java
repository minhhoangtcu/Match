package io.match.gui.popup.match;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SimilarityInfoController {
	
	@FXML
	Label choice;
	
	@FXML
	Label expectingChoice;
	
	@FXML
	Label importance;

	@FXML
	Label attribute;
	
	public void setChoice(String text) {
		choice.setText(text);
	}
	
	public void setExpectingChoice(String text) {
		expectingChoice.setText(text);
	}
	
	public void setImportance(String text) {
		importance.setText(text);
	}
	
	public void setAttribute(String text) {
		attribute.setText(text);
	}

}
