package io.match.gui.left;

import java.io.IOException;
import java.util.LinkedList;

import io.match.Match;
import io.match.Model;
import io.match.datastructure.Person;
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
	
	@FXML
	private AnchorPane leftLayout;
	
	@FXML
	private TableView displayTable;
	
	BorderPane rootLayout;
	private Model model;
	private MainController mController;
	
	public void setModel(Model model) {
		this.model = model;
	}
	
	public void setMainController(MainController controller) {
		mController = controller;
		mController.setLeftTableView(displayTable);
		mController.setLeftLayout(leftLayout);
		rootLayout = mController.getRootLayout();
		displayTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> loadCenterLayout(newValue));
	}
	

	@FXML
	public void loadStudents() {
		TablePopulator.populateStudent(displayTable, model.getStudents());
		loadCenterLayout(null);
	}
	
	@FXML
	public void loadFaculties() {
		TablePopulator.populateFaculties(displayTable, model.getFaculties());
		loadCenterLayout(null);
	}
	
	@FXML
	public void loadFields() {
		
		try {
			TablePopulator.populateAttributes(displayTable, model.getAttributes());
		} catch (Exception e) {
			System.err.println("Failed to populate the table.");
			e.printStackTrace();
		}
		
		
		// load center layout
		BorderPane center = mController.getCenterLayout();
		try {
			FXMLLoader loader = new FXMLLoader(Match.class.getResource("gui/center/attribute/AttributesView.fxml"));
			Parent layout = loader.load();
			
			AttributesViewController controller = loader.getController();
			controller.setModel(model);

			center.setCenter(layout);
		} catch (IOException e) {
			System.out.println("Fail to load FXML file in loadAttributesLayout: BottomController");
		}
	}
	
	private void loadCenterLayout(Object object) {
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
				TableColumn column = (TableColumn) displayTable.getColumns().get(0);
				String type = column.getText();
				switch (type) {
				case "Student": controller.setObject(model.getStudents().getFirst()); break;
				case "Faculty": controller.setObject(model.getFaculties().getFirst()); break;
				case "Field": controller.setObject(model.getAttributes().getFirst()); break;
				}
			}
			center.setCenter(layout);
		} catch (IOException e) {
			System.out.println("Fail to load FXML file in loadCenterLayout: LeftController");
		}
	}


}
