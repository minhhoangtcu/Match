package controller.bottomController;

import java.io.IOException;
import java.util.LinkedList;

import application.Model;
import controller.leftController.LeftController;
import controller.leftController.TablePopulator;
import helper.LayoutFetcher;
import io.match.datastructure.Person;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class ManageUserBarController {
	
	BorderPane rootLayout;
	Model model;
	
	public ManageUserBarController(BorderPane rootLayout, Model model) {
		this.rootLayout = rootLayout;
		this.model = model;
	}
	
	public void loadStudents() {
		loadLeftLayout(model.getStudents());
	}
	
	public void loadFaculties() {
		loadLeftLayout(model.getFaculties());
	}
	
	public void loadAttributes() {
		rootLayout.setLeft(null);
		
	}
	
	private void loadLeftLayout(LinkedList<Person> who) {
		loadLeftLayout();
		TableView tableView = LayoutFetcher.getTableInLeftLayout(rootLayout);
		TablePopulator.populateStudent(tableView, who);
	}
	
	private void loadLeftLayout() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/fxml/left/LeftLayout.fxml"));
		loader.setController(new LeftController());
		Parent layout;
		try {
			layout = loader.load();
			rootLayout.setLeft(layout);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Fail to load in loadStudents: ManageUserBarController");
		}
	}
	
}
