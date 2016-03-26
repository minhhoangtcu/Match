package io.match.gui.bottom;

import java.io.IOException;

import io.match.Match;
import io.match.Model;
import io.match.gui.MainController;
import io.match.gui.center.load.LoadViewController;
import io.match.gui.center.manage.DisplayPersonController;
import io.match.gui.center.match.MatchViewController;
import io.match.gui.left.TablePopulator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

public class MainButtonBarController {

/*
 * TODO: 
 * 	-Fade out or change the style of the button for the current tab.
 * 	-Load manage view after return from Match view
 */
	
	
	@FXML
	private Button btnLoad;

	@FXML
	private Button btnMatch;

	@FXML
	private Button btnManage;
	
	@FXML
	private Button btnExport;
	
	
	
	// When clicking a button, its height will increase
	private static final double buttonOffset = 10.0; 
	
	// Required 
	private Model model;
	private MainController mController;

	// Layout to be initialized and store within the program. 
	private AnchorPane loadCenterView;
	private AnchorPane matchCenterView;
	private AnchorPane manageLeftView;
	private AnchorPane manageCenterView;
	
	@FXML
	private void initialize() {
//		ToggleGroup btnToggleGroup = new ToggleGroup();
//		btnLoad.setToggleGroup(btnToggleGroup);
//		btnMatch.setToggleGroup(btnToggleGroup);
//		btnManage.setToggleGroup(btnToggleGroup);
//		btnExport.setToggleGroup(btnToggleGroup);
	}
	
	public void setModel(Model model) {
		this.model = model;
	}
	
	public void setMainController(MainController controller) {
		mController = controller;
	}

	@FXML
	private void loadLoadView() {
		mController.getRootLayout().setLeft(null);
		if (loadCenterView == null) {
			initLoadView();
		}
		mController.getCenterLayout().setCenter(loadCenterView);
	}

	private void initLoadView() {
		try {
			FXMLLoader loader = new FXMLLoader(Match.class.getResource("gui/center/load/LoadView.fxml"));
			loadCenterView = (AnchorPane) loader.load();

			LoadViewController controller = loader.getController();
			controller.setModel(model);
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

		mController.getRootLayout().setLeft(null);
		if (matchCenterView == null)
			initMatchView();
		mController.getCenterLayout().setCenter(matchCenterView);
	}
	
	private void initMatchView() {
		try {
			FXMLLoader loader = new FXMLLoader(Match.class.getResource("gui/center/match/MatchView.fxml"));
			matchCenterView = (AnchorPane) loader.load();

			MatchViewController controller = loader.getController();
			controller.setModel(model);
			controller.setMainController(mController);
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
			manageLeftView = mController.getLeftLayout();
			mController.getRootLayout().setLeft(manageLeftView);
			TablePopulator.populateStudent(mController.getLeftTableView(), model.getStudents());
		} else {
			mController.getRootLayout().setLeft(manageLeftView);
		}
		
				
		// Set up center view for Manage tab
		if (manageCenterView == null) {
			initManageCenterLayout();
		}
		mController.getCenterLayout().setCenter(manageCenterView);
	}
	
	private void initManageCenterLayout() {

		try {
			FXMLLoader loader = new FXMLLoader(Match.class.getResource("gui/center/manage/PersonTemplate.fxml"));
			manageCenterView = loader.load();

			DisplayPersonController controller = loader.getController();
			controller.setModel(model);
			controller.setMainController(mController);
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
