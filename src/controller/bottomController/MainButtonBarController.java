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
	
	
	/**
	 * 
	 * user experience: pop up add user button bar
	 * 
	 * @throws Exception
	 */
	public void pushUpAddUserBar() {
		// get center layout
		BorderPane view = LayoutFetcher.getBottomLayout(rootLayout);
		
		if (addUserUp) {
			// remove Top view
			view.setTop(null);
			
			// prepare for collapse
			addUserUp = false;
			
		} else {
			
			// load and set center layout
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/fxml/bottom/AddUserButtonBar.fxml"));
			loader.setController(new AddUserBarController(rootLayout, model));
			try {
				Parent layout = loader.load();
				view.setTop(layout);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("In pushUpAddUserBar: class MainButtonBarController");
			}
			
			// prepare for next time
			addUserUp = true;
		}
	}
	
	/**
	 * 
	 * user experience: pop up delete user button bar
	 * 
	 * @throws Exception
	 */
	public void pushUpDeleteUserBar() throws Exception{
		// get center layout
		BorderPane view = LayoutFetcher.getBottomLayout(rootLayout);
		
		if (deleteUserUp) {
			// remove Top view
			view.setTop(null);
			
			// prepare for collapse
			deleteUserUp = false;
			
		} else {
			
			// load and set center layout
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/fxml/bottom/DeleteUserButtonBar.fxml"));
//			loader.setController(new AddUserController());
			Parent layout = loader.load();
			view.setTop(layout);
			
			// prepare for next time
			deleteUserUp = true;
		}
	}
	
	public void loadModifyUser() throws Exception{
		// get center layout
		BorderPane view = LayoutFetcher.getCenterLayout(rootLayout);
		
		// load and set center layout
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/fxml/center/AddUserLayout.fxml"));
//				loader.setController(new AddUserController());
		Parent layout = loader.load();
		view.setCenter(layout);
	}
	
	
	
	

}
