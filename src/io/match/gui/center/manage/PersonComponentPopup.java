package io.match.gui.center.manage;

import io.match.datastructure.attributes.Attribute;
import io.match.reader.PeopleStringReader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class PersonComponentPopup {
	public static void popupGeneralAttribute(Attribute attribute, GridPane gridPane, int row, boolean editable) {
		gridPane.add(new Label(attribute.getAttributeName() + ":"), 0, row);
		if (editable) {
			gridPane.add(new TextField(PeopleStringReader.getDataGeneral(attribute)), 1, row);
		} else {
			gridPane.add(new Label(PeopleStringReader.getDataGeneral(attribute)), 1, row);
		}
	}
	
	public static void popupName(String name, GridPane gridPane, int row) {
		gridPane.add(new Label("Name: "), 0, row);
		gridPane.add(new Label(name), 1, row);
	}
	
	public static void popupMatched(int matched, GridPane gridPane, int row) {
		gridPane.add(new Label("Matched: "), 0, row);
		gridPane.add(new Label("" + matched), 1, row);
	}
	
	public static void popupAvailableMatches(int available, GridPane gridPane, int row) {
		gridPane.add(new Label("Matches Available: "), 0, row);
		gridPane.add(new Label("" +  available), 1, row);
	}
}
