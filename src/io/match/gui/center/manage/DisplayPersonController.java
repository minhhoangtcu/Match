package io.match.gui.center.manage;

import io.match.Model;
import io.match.datastructure.Person;
import io.match.datastructure.attributes.Attribute;
import io.match.gui.MainController;
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
	
	
	public void setModel(Model model) {
		this.model = model;
	}

	public void setMainController(MainController controller) {
		mController = controller;
		initialize();
	}
	
	private void initialize() {
		gridPane.getRowConstraints().removeAll();
		gridPane.setVgap(10);
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
		boolean disable = true;

		PersonComponentPopup.popupName(person.getName(), gridPane, row++, disable);
		PersonComponentPopup.popupMatched(person.getNumMatched(), gridPane, row++, disable);
		PersonComponentPopup.popupAvailableMatches(person.getNumMatchesAvaiable(), gridPane, row++, disable);
		
		for (Attribute attribute: person.getAttributes()) {
			
			switch (attribute.getAttributeType()) {
			case GENERAL:
				PersonComponentPopup.popupGeneralAttribute(attribute, gridPane, row++, disable);
				break;
			case WEIGHTED_ONE_TO_MULTIPLE:
				PersonComponentPopup.popupWeightedOneToMultipleAttribute(attribute, gridPane, row++, disable);
				break;
			case WEIGHTED_SCALE:
				PersonComponentPopup.popupWithedScaleAttribute(attribute, gridPane, row++, disable);
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
