package io.match.gui.popup.match;

import io.match.algorithm.Similarity;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MatchDetailController {
	
	@FXML
	private Label firstName;
	
	@FXML
	private Label secondName;
	
	@FXML
	private GridPane infoPane;
	
	private Stage popupStage;
	private Similarity[] allSimilarities;
	
	@FXML
	private void initialize() {	}

	public void setFirstName(String name) {
		firstName.setText(name);
	}
	
	public void setSecondName(String name) {
		secondName.setText(name);
	}
	
	public void setAllSimilarities(Similarity[] allSimilarities) {
		this.allSimilarities = allSimilarities;
	}

	public void setPopupStage(Stage popupStage) {
		this.popupStage = popupStage;
	}
}
