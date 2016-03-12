package io.match.gui.center.manage;

import io.match.datastructure.attributes.Attribute;
import io.match.reader.PeopleStringReader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

/*
 * TODO: 
 * - Center the content
 * - Make a the whole thing nicer
 * 
 */

public class PersonComponentPopup {

	private static final int rowHeight = 24;
	private static final String COLORED_STYLE = "-fx-background-color: #e6e6e6;";

	private static Pane getStyledPane(String text, boolean isDisable, boolean isColored) {
		Pane back = new Pane();

		if (isDisable) {
			Label lbl = new Label(text);
			back.getChildren().add(lbl);
			// TODO: Set to the middle of the cell
			lbl.setAlignment(Pos.CENTER_RIGHT); // NOT WORKING
			
		} else
			back.getChildren().add(new TextField(text));

		if (isColored)
			back.setStyle(COLORED_STYLE);
		return back;
	}

	@Deprecated
	public static void popupAvailableMatches(int available, GridPane gridPane, int row, boolean disable) {
		gridPane.getRowConstraints().add(new RowConstraints(rowHeight));
		gridPane.add(new Label("Matches Available"), 0, row);
		if (disable) {
			gridPane.add(new Label(": " + available), 1, row);
		} else {
			gridPane.add(new TextField("" + available), 1, row);
		}
	}

	@Deprecated
	public static void popupGeneralAttribute(Attribute attribute, GridPane gridPane, int row, boolean disable) {
		// add row constraints
		gridPane.getRowConstraints().add(new RowConstraints(rowHeight));

		// add left label
		Pane back = new Pane();
		Label name = new Label();
		back.getChildren().add(name);
		back.setStyle("-fx-background-color: #C0C0C0;");
		gridPane.add(back, 0, row);

		// add right component
		if (disable) {
			gridPane.add(new Label(": " + PeopleStringReader.getDataGeneral(attribute)), 1, row);
		} else {
			gridPane.add(new TextField(PeopleStringReader.getDataGeneral(attribute)), 1, row);
		}
	}

	@Deprecated
	public static void popupMatched(int matched, GridPane gridPane, int row, boolean disable) {
		gridPane.getRowConstraints().add(new RowConstraints(rowHeight));
		gridPane.add(new Label("Matched"), 0, row);
		if (disable) {
			gridPane.add(new Label(": " + matched), 1, row);
		} else {
			gridPane.add(new TextField("" + matched), 1, row);
		}
	}

	@Deprecated
	public static void popupName(String name, GridPane gridPane, int row, boolean disable) {
		gridPane.getRowConstraints().add(new RowConstraints(rowHeight));
		gridPane.add(new Label("Name"), 0, row);
		if (disable) {
			gridPane.add(new Label(": " + name), 1, row);
		} else {
			gridPane.add(new TextField(name), 1, row);
		}
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
	public static void popupString(String left, String right, GridPane gridPane, int row, boolean isDisable,
			boolean isColored) {
		
		gridPane.getRowConstraints().add(new RowConstraints(rowHeight));
		gridPane.add(getStyledPane(left, isDisable, isColored), 0, row);

		if (isDisable) {
			gridPane.add(getStyledPane(": " + right, isDisable, isColored), 1, row);
		} else {
			gridPane.add(getStyledPane(right, isDisable, isColored), 1, row);
		}
	}

	public static void popupWeightedOneToMultipleAttribute(Attribute attribute, GridPane gridPane, int row,
			boolean isDisable, boolean isColored) {

		// add left label
		gridPane.add(getStyledPane(attribute.getAttributeName(), isDisable, isColored), 0, row);

		// add right component
		VBox vBox = new VBox();
		if (isColored)
			vBox.setStyle(COLORED_STYLE);
		
		ToggleGroup group = new ToggleGroup();

		String choice = PeopleStringReader.getDataOneToMultiple(attribute);
		for (String possibleChoice : PeopleStringReader.getPossibleOneToMultiple(attribute)) {
			RadioButton button = new RadioButton(possibleChoice);
			button.setToggleGroup(group);
			
			if (choice.equals(possibleChoice)) {
				button.setSelected(true);
			}
			
			button.setDisable(isDisable);
			button.setStyle("-fx-opacity: 1;"); // force the disabled radio button to be the same as normal one.

			// edit vBox
			vBox.getChildren().add(button);
			vBox.setMargin(button, new Insets(5, 0, 5, 0));
		}
		gridPane.add(vBox, 1, row);

	}

	public static void popupWithedScaleAttribute(Attribute attribute, GridPane gridPane, int row, boolean isDisable, boolean isColored) {
		
		// add left label
		gridPane.add(getStyledPane(attribute.getAttributeName(), isDisable, isColored), 0, row);

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
	}
}
