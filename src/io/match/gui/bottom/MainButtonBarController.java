package io.match.gui.bottom;

import java.io.IOException;
import java.util.LinkedList;
import io.match.Match;
import io.match.Model;
import io.match.datastructure.Person;
import io.match.gui.center.load.LoadViewController;
import io.match.gui.center.manage.DisplayPersonController;
import io.match.gui.center.match.MatchViewController;
import io.match.gui.left.AssignListener;
import io.match.gui.left.ManageLeftController;
import io.match.gui.left.TablePopulator;
import io.match.helper.LayoutFetcher;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class MainButtonBarController {

	@FXML
	private Button btnLoad;

	@FXML
	private Button btnMatch;

	// Required 
	private Model model;
	private BorderPane rootLayout;

	// Layout to be initialized and store within the program. 
	private AnchorPane loadCenterView;
	private AnchorPane matchCenterView;
	private AnchorPane manageLeftView;
	private AnchorPane manageCenterView;

	private boolean manageUserUp = false;

	public void setModel(Model model) {
		this.model = model;
	}

	public void setRootLayout(BorderPane rootLayout) {
		this.rootLayout = rootLayout;
	}

	@FXML
	private void loadLoadView() {
		rootLayout.setLeft(null);
		if (loadCenterView == null) {
			initLoadView();
		}
		LayoutFetcher.getCenterLayout(rootLayout).setCenter(loadCenterView);
	}

	private void initLoadView() {
		try {
			FXMLLoader loader = new FXMLLoader(Match.class.getResource("gui/center/load/LoadView.fxml"));
			loadCenterView = (AnchorPane) loader.load();

			LoadViewController controller = loader.getController();
			controller.setModel(model);
			controller.setRootLayout(rootLayout);

		} catch (IOException e) {
			System.out.println("Fail to load FXML file in loadLoadView: MainButtonBarController");
		}
	}

	@FXML
	private void loadMatchView() {

		if (model.isEmptyFaculties() && model.isEmptyStudents()) {
			System.out.println(
					"Oop! Didn't load students and faculties files" + "\nFrom loadMatchView: MainButtonBarController");
			return;
		}

		rootLayout.setLeft(null);
		
		if (matchCenterView == null)
			initMatchView();
		
		LayoutFetcher.getCenterLayout(rootLayout).setCenter(matchCenterView);
		
	}
	
	private void initMatchView() {
		try {
			FXMLLoader loader = new FXMLLoader(Match.class.getResource("gui/center/match/MatchView.fxml"));
			matchCenterView = (AnchorPane) loader.load();

			MatchViewController controller = loader.getController();
			controller.setModel(model);
			controller.setRootLayout(rootLayout);
			controller.populateMatchTable();
			
		} catch (IOException e) {
			System.out.println("Fail to load FXML file in loadLoadView: MainButtonBarController");
		}
	}

	@FXML
	private void loadManageView() {

		if (model.isEmptyFaculties() && model.isEmptyStudents()) {
			System.out.println(
					"Oop! Didn't load students and faculties files" + "\nFrom loadMatchView: MainButtonBarController");
			return;
		}

		// Set up the left view for Manage tab
		if (manageLeftView == null) {
			initManageLeftView();
			rootLayout.setLeft(manageLeftView);
			loadLeftLayoutWithPeople(model.getStudents());
		}
		rootLayout.setLeft(manageLeftView);
				
		// Set up center view for Manage tab
		if (manageCenterView == null) {
			initManageCenterLayout();
		}
		LayoutFetcher.getCenterLayout(rootLayout).setCenter(manageCenterView);
	}

	private void initManageLeftView() {
		try {
			FXMLLoader loader = new FXMLLoader(Match.class.getResource("gui/left/LeftLayout.fxml"));
			manageLeftView = (AnchorPane) loader.load();

			ManageLeftController controller = loader.getController();
			controller.setModel(model);
			controller.setRootLayout(rootLayout);
			
		} catch (IOException e) {
			System.out.println("Fail to load in loadStudents: MainButtonBarController");
		}
	}
	
	private void loadLeftLayoutWithPeople(LinkedList<Person> who) {
		TableView tableView = LayoutFetcher.getTableInLeftLayout(rootLayout);
		TablePopulator.populateStudent(tableView, who);
		AssignListener.assignListener(tableView);
	}

	private void initManageCenterLayout() {

		try {
			FXMLLoader loader = new FXMLLoader(Match.class.getResource("gui/center/manage/PersonTemplate.fxml"));
			manageCenterView = loader.load();

			DisplayPersonController controller = loader.getController();
			controller.setModel(model);
			controller.setRootLayout(rootLayout);
		} catch (IOException e) {
			System.out.println("Fail to load FXML file in setCenterLayout: MainButtonBarController");
		}
	}

	@FXML
	private void export() {
		if (model.isEmptyFaculties() && model.isEmptyStudents()) {
			System.out.println(
					"Oop! Didn't load students and faculties files" + "\nFrom loadMatchView: MainButtonBarController");
			return;
		}
		System.out.println("From export: MainButtonBarController");
	}

}
