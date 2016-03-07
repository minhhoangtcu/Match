package io.match.gui.bottom;

import java.io.IOException;
import java.util.LinkedList;

import io.match.Match;
import io.match.Model;
import io.match.datastructure.Person;
import io.match.gui.center.attribute.AttributesViewController;
import io.match.gui.center.manage.DisplayPersonController;
import io.match.gui.left.ManageLeftController;
import io.match.gui.left.TablePopulator;
import io.match.helper.LayoutFetcher;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class ManageUserBarController {
	
	BorderPane rootLayout;
	Model model;
	
	public void setModel(Model model) {
		this.model = model;
	}
	
	public void setRootLayout(BorderPane rootLayout) {
		this.rootLayout = rootLayout;
	}
	
	public void loadStudents() {
		loadLeftLayout(model.getStudents());
		loadCenterLayout();
	}

	public void loadFaculties() {
		loadLeftLayout(model.getFaculties());
		loadCenterLayout();
	}
	
	public void loadAttributes() {
		rootLayout.setLeft(null);
		loadAttributesLayout();
	}
	
	
	private void loadAttributesLayout() {
		BorderPane center = LayoutFetcher.getCenterLayout(rootLayout);
		
		FXMLLoader loader = new FXMLLoader(Match.class.getResource("gui/center/attribute/AttributesView.fxml"));
		AttributesViewController controller = loader.getController();
		controller.setModel(model);
		controller.setRootLayout(rootLayout);
		try {
			Parent layout = loader.load();
			center.setCenter(layout);
		} catch (IOException e) {
			System.out.println("Fail to load FXML file in loadAttributesLayout: BottomController");
		}
	}

	private void loadCenterLayout() {
		BorderPane center = LayoutFetcher.getCenterLayout(rootLayout);
		
		FXMLLoader loader = new FXMLLoader(Match.class.getResource("gui/center/display/PersonTemplate.fxml"));
		DisplayPersonController controller = loader.getController();
		controller.setModel(model);
		controller.setRootLayout(rootLayout);
		try {
			Parent layout = loader.load();
			center.setCenter(layout);
		} catch (IOException e) {
			System.out.println("Fail to load FXML file in setCenterLayout: BottomController");
		}
	}
	
	private void loadLeftLayout(LinkedList<Person> who) {
		loadLeftLayout();
		TableView tableView = LayoutFetcher.getTableInLeftLayout(rootLayout);
		TablePopulator.populateStudent(tableView, who);
	}
	
	private void loadLeftLayout() {
		FXMLLoader loader = new FXMLLoader(Match.class.getResource("gui/left/LeftLayout.fxml"));
		ManageLeftController controller = loader.getController();
		controller.setModel(model);
		controller.setRootLayout(rootLayout);
		try {
			Parent layout = loader.load();
			rootLayout.setLeft(layout);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Fail to load in loadStudents: LeftController");
		}
	}
	
}
