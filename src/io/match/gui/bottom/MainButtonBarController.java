package io.match.gui.bottom;

import java.io.IOException;
import java.util.LinkedList;

import javax.swing.text.View;

import io.match.Match;
import io.match.Model;
import io.match.datastructure.Person;
import io.match.gui.center.load.LoadViewController;
import io.match.gui.center.manage.DisplayPersonController;
import io.match.gui.center.match.MatchViewController;
import io.match.gui.left.LeftController;
import io.match.gui.left.TablePopulator;
import io.match.helper.LayoutFetcher;
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
	
	
	public void setModel(Model model) {
		this.model = model;
	}
	
	public void setRootLayout(BorderPane rootLayout) {
		this.rootLayout = rootLayout;
	}


	public void loadLoadView(){
		rootLayout.setLeft(null);
		
		// load center layout
		BorderPane center = LayoutFetcher.getCenterLayout(rootLayout);
		try {
			FXMLLoader loader = new FXMLLoader(Match.class.getResource("gui/center/load/LoadView.fxml"));
			Parent layout = loader.load();
			
			LoadViewController controller = loader.getController();
			controller.setModel(model);
			controller.setRootLayout(rootLayout);
			center.setCenter(layout);
		} catch (IOException e) {
			System.out.println("Fail to load FXML file in loadLoadView: MainButtonBarController");
		}
	}
	

	public void loadMatchView() {
		
		if (model.isEmptyFaculties() && model.isEmptyStudents()) { 
			System.out.println("Oop! Didn't load students and faculties files"
					+ "\nFrom loadMatchView: MainButtonBarController");
			return;
		}
		
		// set left layout to null
		rootLayout.setLeft(null);
		
		// load center layout
		
		
		try {
			BorderPane center = LayoutFetcher.getCenterLayout(rootLayout);
			FXMLLoader loader = new FXMLLoader(Match.class.getResource("gui/center/match/MatchView.fxml"));
			Parent layout = loader.load();

			MatchViewController controller = loader.getController();
			controller.setModel(model);
			controller.setRootLayout(rootLayout);
			center.setCenter(layout);
		} catch (IOException e) {
			System.out.println("Fail to load FXML file in loadLoadView: MainButtonBarController");
		}
	}

	public void loadManageView() {
		
		if (model.isEmptyFaculties() && model.isEmptyStudents()) { 
			System.out.println("Oop! Didn't load students and faculties files"
					+ "\nFrom loadMatchView: MainButtonBarController");
			return;
		}
		
		loadLeftLayout(model.getStudents());
		loadCenterLayout();
	}
	
	private void loadLeftLayout(LinkedList<Person> who) {
		loadLeftLayout();
		TableView tableView = LayoutFetcher.getTableInLeftLayout(rootLayout);
		TablePopulator.populateStudent(tableView, who);
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
			System.out.println("Fail to load in loadStudents: MainButtonBarController");
		}
	}
	
	private void loadCenterLayout() {
		
		try {
			BorderPane center = LayoutFetcher.getCenterLayout(rootLayout);
			FXMLLoader loader = new FXMLLoader(Match.class.getResource("gui/center/manage/PersonTemplate.fxml"));
			Parent layout = loader.load();
			
			
			DisplayPersonController controller = loader.getController();
			controller.setModel(model);
			controller.setRootLayout(rootLayout);
			center.setCenter(layout);
		} catch (IOException e) {
			System.out.println("Fail to load FXML file in setCenterLayout: MainButtonBarController");
		}
	}
	
	public void export() {
		if (model.isEmptyFaculties() && model.isEmptyStudents()) {  
			System.out.println("Oop! Didn't load students and faculties files"
					+ "\nFrom loadMatchView: MainButtonBarController");
			return;
		}
		System.out.println("From export: MainButtonBarController");
	}
	
	
	
	

}
