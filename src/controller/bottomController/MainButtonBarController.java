package controller.bottomController;

import java.io.IOException;
import java.util.LinkedList;

import javax.swing.text.View;

import application.Model;
import controller.centerController.DisplayPersonController;
import controller.centerController.LoadViewController;
import controller.centerController.MatchViewController;
import controller.leftController.LeftController;
import controller.leftController.TablePopulator;
import helper.LayoutFetcher;
import io.match.datastructure.Person;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class MainButtonBarController {
	
	@FXML
	private Button btnLoad;
	@FXML
	private Button btnMatch;
	
	private Model model;
	private BorderPane rootLayout;
	
	
	private boolean manageUserUp = false;
	
	
	public MainButtonBarController(BorderPane rootLayout, Model model) {
		this.rootLayout = rootLayout;
		this.model = model;
		
		setEnableButtons(true);
	}
	
	private void setEnableButtons(boolean b) {
//		btnMatch.setDisable(b);
	}


	public void loadLoadView(){
		// set left layout to null
		rootLayout.setLeft(null);
		
		// load center layout
		BorderPane center = LayoutFetcher.getCenterLayout(rootLayout);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/fxml/center/LoadView.fxml"));
		loader.setController(new LoadViewController(rootLayout, model));
		try {
			Parent layout = loader.load();
			center.setCenter(layout);
		} catch (IOException e) {
			System.out.println("Fail to load FXML file in loadLoadView: MainButtonBarController");
		}
	}
	

	/**
	 * 
	 * user experience: pop up delete user button bar
	 * 
	 * @throws Exception
	 */
	public void loadMatchView() throws Exception{
		// set left layout to null
		rootLayout.setLeft(null);
		
		// load center layout
		BorderPane center = LayoutFetcher.getCenterLayout(rootLayout);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/fxml/center/MatchView.fxml"));
		loader.setController(new MatchViewController(rootLayout, model));
		try {
			Parent layout = loader.load();
			center.setCenter(layout);
		} catch (IOException e) {
			System.out.println("Fail to load FXML file in loadLoadView: MainButtonBarController");
		}
	}

	
	/**
	 * 
	 * user experience: pop up add user button bar
	 * 
	 * @throws Exception
	 */
	public void loadManageView() {
		loadLeftLayout(model.getStudents());
		loadCenterLayout();
	}
	
	private void loadLeftLayout(LinkedList<Person> who) {
		loadLeftLayout();
		TableView tableView = LayoutFetcher.getTableInLeftLayout(rootLayout);
		TablePopulator.populateStudent(tableView, who);
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
			System.out.println("Fail to load in loadStudents: MainButtonBarController");
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
			System.out.println("Fail to load FXML file in setCenterLayout: MainButtonBarController");
		}
	}
	
	public void export() {
		System.out.println("From export: MainButtonBarController");
	}
	
	
	
	

}
