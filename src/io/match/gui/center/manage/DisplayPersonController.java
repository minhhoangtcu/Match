package io.match.gui.center.manage;

import io.match.Model;
import io.match.datastructure.Person;
import io.match.datastructure.attributes.Attribute;
import io.match.gui.MainController;
import io.match.reader.PeopleStringReader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.ColumnConstraintsBuilder;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class DisplayPersonController {
	
	/*
	 * Required
	 */
	private Model model;
	private MainController mController;
	private Person person;
	
	/*
	 * GUI's
	 */
	@FXML
	private Button btnAddNew;
	
	@FXML
	private Button btnModify;
	
	@FXML
	private Button btnDelete;
	
	@FXML
	private ScrollPane scrollPane;
	
	@FXML
	private GridPane gridPane;
	
	/*
	 * String Constant 
	 */
	private static final String NAME = "Name";
	private static final String MATCHED = "Matched";
	private static final String MATCHES_AVAILABLE = "Matches Available";
	
	/*
	 * Helper Variables
	 */
	private boolean isColored;
	
	public void setModel(Model model) {
		this.model = model;
	}

	public void setMainController(MainController controller) {
		mController = controller;
		initialize();
	}
	
	private void initialize() {
		gridPane.getRowConstraints().removeAll();
		gridPane.setVgap(0);
		isColored = false;
	}

	public void setPerson(Person person) {
		this.person = person;
		popupPerson(person);
	}

	private void popupPerson(Person person) {
		
		/*
		 * THIS IS HOW TO ADD ELEMENTS DYNAMICALLY TO THE DISPLAY
		 */
		int row = 0;
		boolean isDisable = true;

		PersonComponentPopup.popupString(NAME, person.getName(), gridPane, row++, isDisable, isColored);
		isColored = !isColored;
		PersonComponentPopup.popupString(MATCHED, person.getNumMatched()+"", gridPane, row++, isDisable, isColored);
		isColored = !isColored;
		PersonComponentPopup.popupString(MATCHES_AVAILABLE, person.getNumMatchesAvaiable()+"", gridPane, row++, isDisable, isColored);
		isColored = !isColored;
		
		for (Attribute attribute: person.getAttributes()) {
			
			switch (attribute.getAttributeType()) {
			case GENERAL:
				PersonComponentPopup.popupString(attribute.getAttributeName(), PeopleStringReader.getDataGeneral(attribute), gridPane, row++, isDisable, isColored);
				isColored = !isColored;
				break;
			case WEIGHTED_ONE_TO_MULTIPLE:
				PersonComponentPopup.popupWeightedOneToMultipleAttribute(attribute, gridPane, row++, isDisable);
				break;
			case WEIGHTED_SCALE:
				PersonComponentPopup.popupWithedScaleAttribute(attribute, gridPane, row++, isDisable);
				break;
			}
		}
	}
	
	@FXML
	private void add() {
		System.out.println("From add: DisplayPersonController");
	}
	
	@FXML
	private void modify() {
		System.out.println("From modify: DisplayPersonController");	
	}
	
	@FXML
	private void delete() {
		System.out.println("From delete: DisplayPersonController");
	}
	
}
