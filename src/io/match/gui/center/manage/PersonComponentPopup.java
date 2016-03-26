package io.match.gui.center.manage;

import java.util.ArrayList;
import java.util.HashMap;

import io.match.datastructure.attributes.Attribute;
import io.match.datastructure.attributes.AttributeUtil;
import io.match.reader.PeopleStringReader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

public class PersonComponentPopup {

	private static HashMap<String, Object> tableContent = new HashMap<String, Object>();

	private static final int rowHeight = 30;
	private static final String COLORED_STYLE = "-fx-background-color: #e6e6e6;";
	private static final String EXPECTING_CHOICE_LABEL = "Expecting Choices: ";
	private static final String IMPORTANCE_LABEL = "Expecting Choices: ";

	private static HBox getStyledPane(String text, boolean isColored) {
		HBox back = new HBox();

		Label lbl = new Label(text);
		back.getChildren().add(lbl);
		back.setAlignment(Pos.CENTER_LEFT);

		if (isColored)
			back.setStyle(COLORED_STYLE);

		return back;
	}

	private static HBox getEditableStyledPane(String attributeName, String text, boolean isColored) {
		HBox back = new HBox();

		TextField tf = new TextField(text);
		back.setHgrow(tf, Priority.ALWAYS);
		back.setMargin(tf, new Insets(0, 10, 0, 0));

		back.getChildren().add(tf);
		back.setAlignment(Pos.CENTER_LEFT);

		if (isColored)
			back.setStyle(COLORED_STYLE);

		tableContent.put(attributeName, tf);

		return back;
	}
	
	/**
	 * Fill in the specified row of the grid pane with two input Strings. The
	 * method may make the whole row colored or the second collumn editable
	 * depends on the input.
	 * 
	 * @param left
	 * @param right
	 * @param gridPane
	 * @param row
	 * @param isDisable
	 * @param isColored
	 */
	public static void popupString(
			String left, String right, GridPane gridPane, 
			int row, boolean isDisable) {
		popupString(left, right, gridPane, row, isDisable, row % 2 == 0);
	}
	
	private static void popupString(
			String left, String right, GridPane gridPane, int row, 
			boolean isDisable, boolean isColored) {

		gridPane.getRowConstraints().add(new RowConstraints(rowHeight));
		gridPane.add(getStyledPane(left, isColored), 0, row);

		if (isDisable) {
			gridPane.add(getStyledPane(": " + right, isColored), 1, row);
		} else {
			gridPane.add(getEditableStyledPane(left, right, isColored), 1, row);
		}
	}

	public static void popupWeightedOneToMultipleAttribute(
			Attribute attribute, GridPane gridPane, int row, boolean isDisable) {
		
		popupMultipleChoices(
				attribute.getAttributeName(), 
				"",
				PeopleStringReader.getDataOneToMultiple(attribute), 
				PeopleStringReader.getPossibleOneToMultiple(attribute), 
				gridPane, row, isDisable, row % 2 == 0);
		
		row = row + 1;
		popupMultipleOptions(
				EXPECTING_CHOICE_LABEL, 
				attribute.getAttributeName(),
				PeopleStringReader.getExpectingOneToMultiple(attribute),
				PeopleStringReader.getPossibleOneToMultiple(attribute),
				gridPane, row, isDisable, row % 2 == 0);
		
		row = row + 1;
		popupMultipleChoices(
				IMPORTANCE_LABEL, 
				attribute.getAttributeName(),
				PeopleStringReader.getImportanceOneToMultiple(attribute),
				AttributeUtil.getAllString(),
				gridPane, row, isDisable, row % 2 == 0);
	}

	private static void popupMultipleChoices(
			String left, String additional, String choice, String[] choices, 
			GridPane gridPane, int row, boolean isDisable, boolean isColored) {
		// add left label
		gridPane.add(getStyledPane(left, isColored), 0, row);

		// add right component
		VBox vBox = new VBox();
		if (isColored)
			vBox.setStyle(COLORED_STYLE);

		ToggleGroup group = new ToggleGroup();

		for (String possibleChoice : choices) {
			RadioButton button = new RadioButton(possibleChoice);
			button.setToggleGroup(group);

			if (choice.equals(possibleChoice)) {
				button.setSelected(true);
			}

			button.setDisable(isDisable);
			button.setStyle("-fx-opacity: 1;"); // force the disabled radio
												// button to be the same as
												// normal one.

			// edit vBox
			vBox.getChildren().add(button);
			vBox.setMargin(button, new Insets(5, 0, 5, 0));
		}
		gridPane.add(vBox, 1, row);

		// add to Table content
		tableContent.put(left + additional, group);
	}
	
	private static void popupMultipleOptions(
			String left, String additional, String[] checkedChoice, String[] allChoices, 
			GridPane gridPane, int row, boolean isDisable, boolean isColored) {
		// add left label
		gridPane.add(getStyledPane(left, isColored), 0, row);

		// add right component
		VBox vBox = new VBox();
		if (isColored)
			vBox.setStyle(COLORED_STYLE);

		ArrayList<CheckBox> group = new ArrayList<CheckBox>();

		for (String possibleChoice : allChoices) {
			CheckBox box = new CheckBox(possibleChoice);
			group.add(box);
			
			for (String checked: checkedChoice) {
				if (checked.equals(possibleChoice)) {
					box.setSelected(true);
				}
			}
			

			box.setDisable(isDisable);
			box.setStyle("-fx-opacity: 1;"); // force the disabled radio
												// button to be the same as
												// normal one.

			// edit vBox
			vBox.getChildren().add(box);
			vBox.setMargin(box, new Insets(5, 0, 5, 0));
		}
		gridPane.add(vBox, 1, row);

		// add to Table content
		tableContent.put(left + additional, group);
	}

	public static void popupScaleAttribute(
			Attribute attribute, GridPane gridPane, int row, boolean isDisable) {
		// popup choice
		popupScale(attribute, gridPane, row, isDisable, row % 2 == 0);
		
		// popup level of interest
		row = row + 1;
		
	}
	
	private static void popupScale(
			Attribute attribute, GridPane gridPane, int row, boolean isDisable,
			boolean isColored) {

		// add left label
		gridPane.add(getStyledPane(attribute.getAttributeName(), isColored), 0, row);

		// add right component
		VBox vBox = new VBox();
		if (isColored)
			vBox.setStyle(COLORED_STYLE);

		double min = PeopleStringReader.getMinScale(attribute);
		double max = PeopleStringReader.getMaxScale(attribute);
		double choice = PeopleStringReader.getDataScale(attribute);
		Slider slider = new Slider(min, max, choice);
		slider.setShowTickMarks(true);
		slider.setShowTickLabels(true);
		slider.setSnapToTicks(true);
		slider.setMajorTickUnit(1);
		slider.setMinorTickCount(0);
		slider.setDisable(isDisable);
		slider.setStyle("-fx-opacity: 1;");

		vBox.getChildren().add(slider);

		gridPane.add(vBox, 1, row);

		// add to Table content
		tableContent.put(attribute.getAttributeName(), slider);
	}

	public static HashMap<String, Object> getTableContent() {
		return tableContent;
	}

	public static void emptyTableContent() {
		tableContent = new HashMap<String, Object>();
	}
}
