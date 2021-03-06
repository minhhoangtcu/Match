package io.match.gui.center.manage;

import java.util.HashMap;

import io.match.Model;
import io.match.datastructure.Person;
import io.match.datastructure.attributes.Attribute;
import io.match.gui.MainController;
import io.match.reader.PeopleStringReader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

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

	@FXML
	private HBox bottomHBox;

	/*
	 * String Constant
	 */
	private static final String NAME = "Name";
	private static final String MATCHED = "Matched";
	private static final String MATCHES_AVAILABLE = "Matches Available";

	/*
	 * Helper Variables
	 */
	private static HashMap<String, Object> tableContent;

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
	}

	public void setPerson(Person person) {
		this.person = person;
		popupPerson(person, true);
	}

	private void popupPerson(Person person, boolean isDisable) {

		// empty previous access
		PersonComponentPopup.emptyTableContent();
		tableContent = PersonComponentPopup.getTableContent();

		/*
		 * THIS IS HOW TO ADD ELEMENTS DYNAMICALLY TO THE DISPLAY
		 */
		Integer row = 0;

		PersonComponentPopup.popupString(
				NAME, person.getName(), gridPane, row++, isDisable);
		PersonComponentPopup.popupString(
				MATCHED, person.getNumMatched() + "", gridPane, row++, isDisable);
		PersonComponentPopup.popupString(
				MATCHES_AVAILABLE, person.getNumMatchesAvaiable() + "", gridPane, row++, isDisable);

		for (Attribute attribute : person.getAttributes()) {

			switch (attribute.getAttributeType()) {
			case GENERAL:
				PersonComponentPopup.popupString(
						attribute.getAttributeName(),PeopleStringReader.getDataGeneral(attribute),
						gridPane, row++, isDisable);
				break;
			case WEIGHTED_ONE_TO_MULTIPLE:
				PersonComponentPopup.popupWeightedOneToMultipleAttribute(
						attribute, gridPane, row, isDisable);
				row = row + 3;
				break;
			case WEIGHTED_SCALE:
				PersonComponentPopup.popupScaleAttribute(attribute, gridPane, row, isDisable);
				row = row + 3;
				break;
			}
		}
	}

	@FXML
	private void add() {
		//all depends on this new Person
		Person person = new Person("");
		
		// re-display the person with text fields
		gridPane.getChildren().clear();
		gridPane.getRowConstraints().clear();
		popupPerson(person, false);

		// replace buttons
		bottomHBox.getChildren().clear();

		Button btnCancel = new Button("Cancel");
		Button btnOK = new Button("OK");

		bottomHBox.getChildren().add(btnOK);
		btnOK.setMaxWidth(Double.MAX_VALUE);
		bottomHBox.setHgrow(btnOK, Priority.ALWAYS);

		btnOK.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				// TODO: modify
				String name = ReadPersonComponentPopup
						.readTextField(tableContent, NAME);
				String numMatched = ReadPersonComponentPopup
						.readTextField(tableContent, MATCHED);
				String numMatchesAvaiable = ReadPersonComponentPopup
						.readTextField(tableContent, MATCHES_AVAILABLE);

				for (Attribute attribute : person.getAttributes()) {
					switch (attribute.getAttributeType()) {
					case GENERAL:
						String newValue = ReadPersonComponentPopup
											.readTextField(tableContent, attribute.getAttributeName());
						// TODO: modify
						break;
					case WEIGHTED_ONE_TO_MULTIPLE:
						String newChoice = ReadPersonComponentPopup
											.readMultipleChoices(tableContent, attribute.getAttributeName());
						System.out.println(newChoice);
						// TODO: read new expecting choices
						
						// TODO: read new level of importance
						System.out.println(PersonComponentPopup.IMPORTANCE_LABEL + attribute.getAttributeName());
						String newLevelOfImportance = ReadPersonComponentPopup
											.readMultipleChoices(
													tableContent,
													PersonComponentPopup.IMPORTANCE_LABEL + attribute.getAttributeName());
						System.out.println(newLevelOfImportance);
						// TODO: modify
						break;
					case WEIGHTED_SCALE:
						Slider slider = (Slider) tableContent.get(attribute.getAttributeName());
						double newInput = slider.getValue();
						// TODO: modify
						break;
					}
				}

				// display original buttons
				bottomHBox.getChildren().clear();
				bottomHBox.getChildren().addAll(btnAddNew, btnModify, btnDelete);
				
				// TODO: add this new person
				
				// re-display the person with updated info
				gridPane.getChildren().clear();
				gridPane.getRowConstraints().clear();
				popupPerson(person, true);
			}
		});

		bottomHBox.getChildren().add(btnCancel);
		btnCancel.setMaxWidth(Double.MAX_VALUE);
		bottomHBox.setHgrow(btnCancel, Priority.ALWAYS);

		btnCancel.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// display original buttons
				bottomHBox.getChildren().clear();
				bottomHBox.getChildren().addAll(btnAddNew, btnModify, btnDelete);
			}
		});
	}

	@FXML
	private void modify() {
		// re-display the person with text fields
		gridPane.getChildren().clear();
		gridPane.getRowConstraints().clear();
		popupPerson(person, false);

		// replace buttons
		bottomHBox.getChildren().clear();

		Button btnCancel = new Button("Cancel");
		Button btnOK = new Button("OK");

		bottomHBox.getChildren().add(btnOK);
		btnOK.setMaxWidth(Double.MAX_VALUE);
		bottomHBox.setHgrow(btnOK, Priority.ALWAYS);

		btnOK.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				// TODO: modify
				String name = ReadPersonComponentPopup.readTextField(tableContent, NAME);
				String numMatched = ReadPersonComponentPopup.readTextField(tableContent, MATCHED);
				String numMatchesAvaiable = ReadPersonComponentPopup.readTextField(tableContent, MATCHES_AVAILABLE);

				for (Attribute attribute : person.getAttributes()) {
					switch (attribute.getAttributeType()) {
					case GENERAL:
						String newValue = 
							ReadPersonComponentPopup.readTextField(
								tableContent, attribute.getAttributeName());
						// TODO: modify
						break;
					case WEIGHTED_ONE_TO_MULTIPLE:
						String newChoice = 
							ReadPersonComponentPopup.readMultipleChoices(
								tableContent, attribute.getAttributeName());
						// TODO: fix this bug
//						String[] newExpectingChoices = 
//							ReadPersonComponentPopup.readMultipleOptions(
//								tableContent, PersonComponentPopup.EXPECTING_CHOICE_LABEL + attribute.getAttributeName());
						
						String newLevelOfImportance = 
							ReadPersonComponentPopup.readMultipleChoices(
								tableContent,PersonComponentPopup.IMPORTANCE_LABEL + attribute.getAttributeName());
						// TODO: modify
						break;
					case WEIGHTED_SCALE:
						double newInput = 
							ReadPersonComponentPopup.readSlider(
								tableContent, attribute.getAttributeName());
						double newLevelOfInterst = 
							ReadPersonComponentPopup.readSlider(
								tableContent, 
								PersonComponentPopup.LEVEL_INTEREST + attribute.getAttributeName());
						String newLevelofImportance = 
							ReadPersonComponentPopup.readMultipleChoices(
								tableContent,
								PersonComponentPopup.IMPORTANCE_LABEL + attribute.getAttributeName());
						// TODO: check if these gettings work
						// TODO: modify
						break;
					}
				}

				// display original buttons
				bottomHBox.getChildren().clear();
				bottomHBox.getChildren().addAll(btnAddNew, btnModify, btnDelete);

				// re-display the person with updated info
				gridPane.getChildren().clear();
				gridPane.getRowConstraints().clear();
				popupPerson(person, true);
			}
		});

		bottomHBox.getChildren().add(btnCancel);
		btnCancel.setMaxWidth(Double.MAX_VALUE);
		bottomHBox.setHgrow(btnCancel, Priority.ALWAYS);

		btnCancel.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// display original buttons
				bottomHBox.getChildren().clear();
				bottomHBox.getChildren().addAll(btnAddNew, btnModify, btnDelete);
			}
		});
	}

	@FXML
	private void delete() {
		System.out.println("From delete: DisplayPersonController");
	}

}
