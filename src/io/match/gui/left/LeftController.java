package io.match.gui.left;

import java.io.IOException;
import java.util.LinkedList;

import io.match.Match;
import io.match.Model;
import io.match.gui.MainController;
import io.match.gui.center.attribute.AttributesViewController;
import io.match.gui.center.manage.DisplayPersonController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
				(observable, oldValue, newValue) -> System.out.println("listener in table"));
	}
	

	@FXML
	public void loadStudents() {
		TablePopulator.populateStudent(displayTable, model.getStudents());
		loadCenterLayout();
	}
	
	@FXML
	public void loadFaculties() {
		TablePopulator.populateFaculties(displayTable, model.getFaculties());
		loadCenterLayout();
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
			controller.setRootLayout(rootLayout);

			center.setCenter(layout);
		} catch (IOException e) {
			System.out.println("Fail to load FXML file in loadAttributesLayout: BottomController");
		}
	}
	
	private void loadCenterLayout() {
		BorderPane center = mController.getCenterLayout();
		try {
			FXMLLoader loader = new FXMLLoader(Match.class.getResource("gui/center/manage/PersonTemplate.fxml"));
			Parent layout = loader.load();
			
			DisplayPersonController controller = loader.getController();
			controller.setModel(model);
			controller.setRootLayout(rootLayout);
			center.setCenter(layout);
		} catch (IOException e) {
			System.out.println("Fail to load FXML file in loadCenterLayout: LeftController");
		}
	}


}
