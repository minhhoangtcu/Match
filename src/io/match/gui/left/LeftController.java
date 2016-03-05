package io.match.gui.left;

import java.io.IOException;
import java.util.LinkedList;

import io.match.Match;
import io.match.Model;
import io.match.datastructure.Person;
import io.match.gui.bottom.MainButtonBarController;
import io.match.gui.center.attribute.AttributesViewController;
import io.match.gui.center.manage.DisplayPersonController;
import io.match.helper.LayoutFetcher;
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
	
	public void loadFields() {
		BorderPane center = LayoutFetcher.getCenterLayout(rootLayout);
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
		BorderPane center = LayoutFetcher.getCenterLayout(rootLayout);
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
	
	private void loadLeftLayout(LinkedList<Person> who) {
		loadLeftLayout();
		TableView tableView = LayoutFetcher.getTableInLeftLayout(rootLayout);
		tableView = TablePopulator.populateStudent(tableView, who);
		AssignListener.assignListener(tableView);
	}

	private void loadLeftLayout() {
		try {
			FXMLLoader loader = new FXMLLoader(Match.class.getResource("gui/left/LeftLayout.fxml"));
			Parent layout = loader.load();
			
			LeftController controller = loader.getController();
			controller.setModel(model);
			controller.setRootLayout(rootLayout);
			rootLayout.setLeft(layout);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Fail to load in loadStudents: LeftController");
		}
	}

}
