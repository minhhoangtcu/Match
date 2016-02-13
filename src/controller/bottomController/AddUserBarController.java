package controller.bottomController;

import java.io.FileNotFoundException;
import java.io.IOException;

import application.Model;
import controller.leftController.TablePopulator;
import helper.LayoutFetcher;
import helper.LoadFile;
import io.match.datastructure.Person;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class AddUserBarController {
	
	private Model model;
	
	private BorderPane rootLayout;
	
	public AddUserBarController(BorderPane rootLayout, Model model) {
		this.rootLayout = rootLayout;
		this.model = model;
	}
	
	/**
	 * 
	 * Load Add user interface and load its Controller
	 * 
	 * @throws Exception
	 */
	public void addUser() throws Exception {
		// get center layout
		BorderPane view = LayoutFetcher.getCenterLayout(rootLayout);
		
		// load and set center layout
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/fxml/center/AddUserLayout.fxml"));
		Parent layout = loader.load();
		view.setCenter(layout);
	}
	
	public void addAllUser() {
		String URL = "";
		try {
			URL = LoadFile.getURL();
			model.loadStudents(URL);
			TableView tableView = LayoutFetcher.getTableInLeftLayout(rootLayout);
			TablePopulator.populateStudent(tableView, model.getStudents());
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			return;
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
	
	
	
	
	
}
