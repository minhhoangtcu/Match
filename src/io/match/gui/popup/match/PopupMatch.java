package io.match.gui.popup.match;

import java.io.IOException;

import io.match.Match;
import io.match.algorithm.Similarity;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopupMatch {

	private MatchDetailController controller;
	private BorderPane layout;
	private Stage primaryStage;

	public PopupMatch(Stage primaryStage) {
		this.primaryStage = primaryStage;

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Match.class.getResource("gui/popup/match/MatchDetail.fxml"));

		try {
			layout = (BorderPane) loader.load();
		} catch (IOException e) {
			System.err.println("Cannot load");
			e.printStackTrace();
		}

		controller = loader.getController();
	}

	public void showPopup(String firstName, String secondName, Similarity[] allSimilarities) {
		// Set up the pop up
		Stage popupStage = new Stage();
		popupStage.setTitle("Matching Details");
		popupStage.initModality(Modality.WINDOW_MODAL);
		popupStage.initOwner(primaryStage);
		Scene scene = new Scene(layout);
		popupStage.setScene(scene);

		// Provide information for the popUp to display
		controller.setPopupStage(popupStage);
		controller.setFirstName(firstName);
		controller.setSecondName(secondName);
		controller.setAllSimilarities(allSimilarities);

		// Show the dialog and wait until the user closes it
		popupStage.show();

	}
}
