package io.match.gui.center.manage;

import io.match.datastructure.attributes.Attribute;
import io.match.reader.PeopleStringReader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

public class PersonComponentPopup {
	
	private static final int rowHeight = 20;
	
	public static void popupGeneralAttribute(
			Attribute attribute, GridPane gridPane, int row, boolean disable) {
		// add row constraints
		gridPane.getRowConstraints().add(new RowConstraints(rowHeight));
		
		// add left label
		gridPane.add(new Label(attribute.getAttributeName()), 0, row);
		
		// add right component
		if (disable) {
			gridPane.add(new Label(": " + PeopleStringReader.getDataGeneral(attribute)), 1, row);
		} else {
			gridPane.add(new TextField(PeopleStringReader.getDataGeneral(attribute)), 1, row);
		}
	}
	
	public static void popupWeightedOneToMultipleAttribute(
			Attribute attribute, GridPane gridPane, int row, boolean disable) {
		
		// add left label
		gridPane.add(new Label(attribute.getAttributeName()), 0, row);
		
		// add right component
		VBox vBox = new VBox();
	    ToggleGroup group = new ToggleGroup();
	    
	    String choice = PeopleStringReader.getDataOneToMultiple(attribute);
	    for (String possibleChoice: PeopleStringReader.getPossibleOneToMultiple(attribute)) {
	    	RadioButton button = new RadioButton(possibleChoice);
	    	button.setToggleGroup(group);
	    	if (choice.equals(possibleChoice)) {
	    		button.setSelected(true);
	    	}
	    	button.setDisable(disable);
	    	
	    	 // edit vBox
	    	vBox.getChildren().add(button);
	    	vBox.setMargin(button, new Insets(5, 0, 5, 0));
	    }
	    gridPane.add(vBox, 1, row);
	    
	}
	
	public static void popupWithedScaleAttribute(
			Attribute attribute, GridPane gridPane, int row, boolean disable) {
		// add left label
		gridPane.add(new Label(attribute.getAttributeName()), 0, row);
		
		// add right component
		VBox vBox = new VBox();
		
		double min = PeopleStringReader.getMinScale(attribute);
		double max = PeopleStringReader.getMaxScale(attribute);
		double choice = PeopleStringReader.getDataScale(attribute);
		Slider slider = new Slider(min, max, choice);
		slider.setShowTickMarks(true);
		slider.setShowTickLabels(true);
		slider.setSnapToTicks(true);
		slider.setMajorTickUnit(1);
		slider.setMinorTickCount(0);
		slider.setDisable(disable);
		
		vBox.getChildren().add(slider);
		
		gridPane.add(vBox, 1, row);
	}
	
	public static void popupName(String name, GridPane gridPane, int row, boolean disable) {
		gridPane.getRowConstraints().add(new RowConstraints(rowHeight));
		gridPane.add(new Label("Name"), 0, row);
		if (disable) {
			gridPane.add(new Label(": " + name), 1, row);
		} else {
			gridPane.add(new TextField(name), 1, row);
		}
	}
	
	public static void popupMatched(int matched, GridPane gridPane, int row, boolean disable) {
		gridPane.getRowConstraints().add(new RowConstraints(rowHeight));
		gridPane.add(new Label("Matched"), 0, row);
		if (disable) {
			gridPane.add(new Label(": " + matched), 1, row);
		} else {
			gridPane.add(new TextField("" + matched), 1, row);
		}
	}
	
	public static void popupAvailableMatches(int available, GridPane gridPane, int row, boolean disable) {
		gridPane.getRowConstraints().add(new RowConstraints(rowHeight));
		gridPane.add(new Label("Matches Available"), 0, row);
		if (disable) {
			gridPane.add(new Label(": " + available), 1, row);
		} else {
			gridPane.add(new TextField("" + available), 1, row);
		}
	}
}
