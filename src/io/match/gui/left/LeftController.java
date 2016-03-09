package io.match.gui.left;

import java.io.IOException;
import io.match.Match;
import io.match.Model;
import io.match.gui.MainController;
import io.match.gui.center.manage.AttributesViewController;
import io.match.gui.center.manage.DisplayPersonController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class LeftController {

	// GUI's
	@FXML
	private AnchorPane leftLayout;

	@FXML
	private TableView displayTable;

	// Required
	private BorderPane rootLayout;
	private Model model;
	private MainController mController;
	
	// Helper variables
	private boolean isDisplayingPerson;
	private DisplayType type;
	
	enum DisplayType {
		STUDENT,
		FACULTY,
		ATTRIBUTE
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
		isDisplayingPerson = false;
		
		mController = controller;
		mController.setLeftTableView(displayTable);
		mController.setLeftLayout(leftLayout);
		rootLayout = mController.getRootLayout();
		displayTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> loadCenterLayoutWithPerson(newValue));
	}

	@FXML
	public void loadStudents() {
		isDisplayingPerson = true;
		type = DisplayType.STUDENT;
		TablePopulator.populateStudent(displayTable, model.getStudents());
		loadCenter(null);
	}

	@FXML
	public void loadFaculties() {
		isDisplayingPerson = true;
		type = DisplayType.FACULTY;
		TablePopulator.populateFaculties(displayTable, model.getFaculties());
		loadCenter(null);
	}

	@FXML
	public void loadFields() {
		isDisplayingPerson = false;
		type = DisplayType.ATTRIBUTE;
		TablePopulator.populateAttributes(displayTable, model.getAttributes());
		loadCenter(null);
	}

	private void loadCenter(Object row) {
		if (isDisplayingPerson)
			loadCenterLayoutWithPerson(row);
		else
			loadCenterLayoutWithAttribute(row);
	}

	private void loadCenterLayoutWithAttribute(Object object) {

		BorderPane center = mController.getCenterLayout();

		try {
			FXMLLoader loader = new FXMLLoader(Match.class.getResource("gui/center/manage/AttributesView.fxml"));
			Parent layout = loader.load(); // fail to load here

			AttributesViewController controller = loader.getController();
			controller.setModel(model);
			controller.setRootLayout(rootLayout);

			center.setCenter(layout);

		} catch (IOException e) {
			System.out.println("Fail to load FXML file in loadAttributesLayout: BottomController");
		}
	}

	private void loadCenterLayoutWithPerson(Object object) {

		BorderPane center = mController.getCenterLayout();

		try {
			FXMLLoader loader = new FXMLLoader(Match.class.getResource("gui/center/manage/PersonTemplate.fxml"));
			Parent layout = loader.load();

			DisplayPersonController controller = loader.getController();
			controller.setModel(model);
			controller.setMainController(mController);

			if (object != null) {
				Row row = (Row) object;
				String name = row.getName();
				
				switch (type) {
				case STUDENT:
					controller.setObject(model.getStudent(name));
					break;
				case FACULTY:
					controller.setObject(model.getFaculty(name));
					break;
				case ATTRIBUTE:
					controller.setObject(model.getAttribute(name));
					break;
				}
			}
			
			center.setCenter(layout);
		} catch (IOException e) {
			System.out.println("Fail to load FXML file in loadCenterLayout: LeftController");
		}
	}
}
