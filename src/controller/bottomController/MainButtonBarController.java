package controller.bottomController;

import java.io.IOException;

import javax.swing.text.View;

import application.Model;
import helper.LayoutFetcher;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class MainButtonBarController {
	
	private Model model;
	
	private BorderPane rootLayout;
	private boolean addUserUp = false;
	private boolean deleteUserUp = false;
	
	
	public MainButtonBarController(BorderPane rootLayout, Model model) {
		this.rootLayout = rootLayout;
		this.model = model;
	}
	
	
	public void loadLoadView() throws Exception{
		System.out.println("from loadLoadView");
	}
	

	/**
	 * 
	 * user experience: pop up delete user button bar
	 * 
	 * @throws Exception
	 */
	public void loadMatchView() throws Exception{
		System.out.println("from loadMatchView");
	}

	
	/**
	 * 
	 * user experience: pop up add user button bar
	 * 
	 * @throws Exception
	 */
	public void pushUpManagebar() {
		// get center layout
		BorderPane view = LayoutFetcher.getBottomLayout(rootLayout);
		
		if (addUserUp) {
			// remove Top view
			view.setTop(null);
			
			// prepare for collapse
			addUserUp = false;
			
		} else {
			
			// load and set center layout
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/fxml/bottom/ManageUserButtonBar.fxml"));
			loader.setController(new ManageUserBarController(rootLayout, model));
			try {
				Parent layout = loader.load();
				view.setTop(layout);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Fail to load in pushUpAddUserBar: class MainButtonBarController");
			}
			
			// prepare for next time
			addUserUp = true;
		}
	}
	
	
	
	
	

}
