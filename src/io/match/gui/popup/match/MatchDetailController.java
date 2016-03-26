package io.match.gui.popup.match;

import java.io.IOException;

import io.match.Match;
import io.match.algorithm.Similarity;
import io.match.datastructure.attributes.Attribute;
import io.match.reader.PeopleStringReader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
	private int row;
	
	@FXML
	private void initialize() {
		row = 1;
	}

	private void displaySimilarities() {
		for (Similarity similarity: allSimilarities) {
			infoPane.add(getAttributeInfoGrid(similarity.getFirstAttr()), 0, row);
			String point = String.format("%.1f", 100.0 * (similarity.getTotalPointGained() / 200.0)) + "%";
			infoPane.add(new Label(point), 1, row);
			infoPane.add(getAttributeInfoGrid(similarity.getSecondAttr()), 2, row);
			row++;
		}
	}
	
	private GridPane getAttributeInfoGrid(Attribute attr) {
		
		// Load the GridPane for the attribute
		FXMLLoader loader = new FXMLLoader(Match.class.getResource("gui/popup/match/SimilarityInfo.fxml"));
		
		GridPane infoGrid = null;
		
		try {
			infoGrid = (GridPane) loader.load();
		} catch (IOException e) {
			System.err.println("Cannot load similarity info");
			e.printStackTrace();
		}
		
		// Load the data into declared GridPane 
		SimilarityInfoController controller = loader.getController();
		switch (attr.getAttributeType()) {
		case WEIGHTED_ONE_TO_MULTIPLE:
			controller.setChoice(PeopleStringReader.getDataOneToMultiple(attr));
			
			StringBuilder sb = new StringBuilder();
			for (String expecting: PeopleStringReader.getExpectingOneToMultiple(attr)) {
				sb.append(expecting);
				sb.append('\n');
			}
			controller.setExpectingChoice(sb.toString());
			
			controller.setImportance(PeopleStringReader.getImportanceOneToMultiple(attr));
			break;
		case WEIGHTED_SCALE:
			controller.setChoice(PeopleStringReader.getDataScale(attr)+"");
			controller.setExpectingChoice(PeopleStringReader.getExpectingScale(attr)+"");
			controller.setImportance(PeopleStringReader.getImportanceScale(attr));
			break;
		default:
			throw new IllegalArgumentException("Cannot pass any attribute other than Weigted");
		}
		controller.setAttribute(attr.getAttributeName());
		
		// Return
		return infoGrid;
	}
	
	public void setFirstName(String name) {
		firstName.setText(name);
	}
	
	public void setSecondName(String name) {
		secondName.setText(name);
	}
	
	public void setAllSimilarities(Similarity[] allSimilarities) {
		this.allSimilarities = allSimilarities;
		displaySimilarities();
	}

	public void setPopupStage(Stage popupStage) {
		this.popupStage = popupStage;
	}
}
