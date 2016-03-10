package io.match.gui.left;

import java.io.IOException;
import io.match.Match;
import io.match.Model;
import io.match.datastructure.Person;
import io.match.datastructure.attributes.Attribute;
import io.match.gui.MainController;
import io.match.gui.center.manage.AttributesViewController;
import io.match.gui.center.manage.DisplayPersonController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class LeftController {

	/*
	 * GUI's
	 */
	@FXML
	private AnchorPane leftLayout;

	@FXML
	private TableView displayTable;

	/*
	 * Required
	 */
	private BorderPane rootLayout;
	private Model model;
	private MainController mController;

	/*
	 * Helper variables
	 */
	private DisplayType type;

	enum DisplayType {
		STUDENT, FACULTY, ATTRIBUTE
	}

	public void setModel(Model model) {
		this.model = model;
	}

	/**
	 * Setting the main controller to left controller. Doing this will also
	 * initialize all required data for the whole class
	 * 
	 */
	public void setMainController(MainController controller) {
		mController = controller;
		mController.setLeftTableView(displayTable);
		mController.setLeftLayout(leftLayout);
		rootLayout = mController.getRootLayout();
		type = DisplayType.STUDENT; // Default to show students first
		displayTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> loadCenter(newValue));
	}

	@FXML
	public void loadStudents() {
		type = DisplayType.STUDENT;
		TablePopulator.populateStudent(displayTable, model.getStudents());
		loadCenter(null); // default layout
	}

	@FXML
	public void loadFaculties() {
		type = DisplayType.FACULTY;
		TablePopulator.populateFaculties(displayTable, model.getFaculties());
		loadCenter(null); // default layout
	}

	@FXML
	public void loadFields() {
		type = DisplayType.ATTRIBUTE;
		TablePopulator.populateAttributes(displayTable, model.getAttributes());
		loadCenter(null); // default layout
	}

	private void loadCenter(Object row) {
		if (row != null) {
			String name = ((Row) row).getName();
			switch (type) {
			case STUDENT:
				loadCenterLayoutWithPerson(model.getStudent(name));
				break;
			case FACULTY:
				loadCenterLayoutWithPerson(model.getFaculty(name));
				break;
			case ATTRIBUTE:
				loadCenterLayoutWithAttribute(model.getAttribute(name));
				break;
			}
		}
		else {
			// Set default layout
		}
	}

	private void loadCenterLayoutWithAttribute(Attribute attribute) {

		BorderPane center = mController.getCenterLayout();

		try {
			FXMLLoader loader = new FXMLLoader(Match.class.getResource("gui/center/manage/AttributesView.fxml"));
			Parent layout = loader.load(); // fail to load here

			AttributesViewController controller = loader.getController();
			controller.setModel(model);
			controller.setRootLayout(rootLayout);

			// Handle passed Attribute

			center.setCenter(layout);

		} catch (IOException e) {
			System.out.println("Fail to load FXML file in loadAttributesLayout: BottomController");
		}
	}

	private void loadCenterLayoutWithPerson(Person person) {

		BorderPane center = mController.getCenterLayout();

		try {
			FXMLLoader loader = new FXMLLoader(Match.class.getResource("gui/center/manage/PersonTemplate.fxml"));
			Parent layout = loader.load();

			DisplayPersonController controller = loader.getController();
			controller.setModel(model);
			controller.setMainController(mController);
			controller.setPerson(person);
			
			center.setCenter(layout);
		} catch (IOException e) {
			System.out.println("Fail to load FXML file in loadCenterLayout: LeftController");
		}
	}
}
