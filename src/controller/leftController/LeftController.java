package controller.leftController;

import java.io.IOException;
import java.util.LinkedList;

import application.Model;
import controller.centerController.AttributesViewController;
import controller.centerController.DisplayPersonController;
import helper.LayoutFetcher;
import io.match.datastructure.Person;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class LeftController {
	
	@FXML
	private TableView displayTable;
	
	BorderPane rootLayout;
	Model model;
	
	public LeftController(BorderPane rootLayout, Model model) {
		this.rootLayout = rootLayout;
		this.model = model;
	}
	
	@FXML
	public void initialize() {
		System.out.println("From initialize: LeftController");
		TablePopulator.populateStudent(displayTable, model.getStudents());
	}
	
	public void loadStudents() {
		loadLeftLayout(model.getStudents());
		loadCenterLayout();
	}
	
	public void loadFaculties() {
		loadLeftLayout(model.getFaculties());
		loadCenterLayout();
	}
	
	public void loadFields() {
		BorderPane center = LayoutFetcher.getCenterLayout(rootLayout);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/fxml/center/AttributesView.fxml"));
		loader.setController(new AttributesViewController(rootLayout, model));
		try {
			Parent layout = loader.load();
			center.setCenter(layout);
		} catch (IOException e) {
			System.out.println("Fail to load FXML file in loadAttributesLayout: BottomController");
		}
	}
	
	private void loadCenterLayout() {
		BorderPane center = LayoutFetcher.getCenterLayout(rootLayout);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/fxml/center/PersonTemplate.fxml"));
		loader.setController(new DisplayPersonController(rootLayout, model));
		try {
			Parent layout = loader.load();
			center.setCenter(layout);
		} catch (IOException e) {
			System.out.println("Fail to load FXML file in loadCenterLayout: LeftController");
		}
	}
	
	private void loadLeftLayout(LinkedList<Person> who) {
		loadLeftLayout();
		TablePopulator.populateStudent(displayTable, who);
	}
	
	private void loadLeftLayout() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/fxml/left/LeftLayout.fxml"));
		loader.setController(new LeftController(rootLayout, model));
		Parent layout;
		try {
			layout = loader.load();
			rootLayout.setLeft(layout);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Fail to load in loadLeftLayout: LeftController");
		}
	}

}
