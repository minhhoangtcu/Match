package controller.bottomController;

import javax.swing.text.View;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class MainButtonBarController {
	
	private BorderPane rootLayout;
	private boolean addUserUp = false;
	private boolean deleteUserUp = false;
	
	
	public MainButtonBarController(BorderPane rootLayout) {
		this.rootLayout = rootLayout;
	}
	
	
	/**
	 * 
	 * user experience: pop up add user button bar
	 * 
	 * @throws Exception
	 */
	public void pushUpAddUserBar() throws Exception{
		// get center layout
		BorderPane view = getBottomLayout(rootLayout);
		
		if (addUserUp) {
			// remove Top view
			view.setTop(null);
			
			// prepare for collapse
			addUserUp = false;
			
		} else {
			
			// load and set center layout
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/fxml/bottom/AddUserButtonBar.fxml"));
			loader.setController(new AddUserBarController(rootLayout));
			Parent layout = loader.load();
			view.setTop(layout);
			
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
		BorderPane view = getBottomLayout(rootLayout);
		
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
		BorderPane view = getCenterLayout(rootLayout);
		
		// load and set center layout
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/fxml/center/AddUserLayout.fxml"));
//				loader.setController(new AddUserController());
		Parent layout = loader.load();
		view.setCenter(layout);
	}
	
	/**
	 * Since the overall layout is a nested layout. This method try to trace through children
	 * and try to fetch out the correct layout wanted to modify
	 * 
	 * The trace is as follow: Main Layout (Border Pane) --> Right Layout (BorderPane) 
	 * --> Bottom layout (Anchor) --> The view wants to modify (Border Pane)
	 * 
	 * @param rootLayout
	 * @return
	 */
	private BorderPane getBottomLayout(BorderPane rootLayout) {
		BorderPane rightLayout = (BorderPane) rootLayout.getChildren().get(0);
		AnchorPane bottomLayout = (AnchorPane) rightLayout.getChildren().get(1);
		BorderPane view = (BorderPane) bottomLayout.getChildren().get(0);
		return view;
	}
	
	/**
	 * Since the overall layout is a nested layout. This method try to trace through children
	 * and try to fetch out the correct layout wanted to modify
	 * 
	 * The trace is as follow: Main Layout (Border Pane) --> Right Layout (BorderPane) 
	 * --> Center layout (Anchor) --> The view wants to modify (Border Pane)
	 * 
	 * @param rootLayout
	 * @return
	 */
	private BorderPane getCenterLayout(BorderPane rootLayout) {
		BorderPane rightLayout = (BorderPane) rootLayout.getChildren().get(0);
		AnchorPane centerLayout = (AnchorPane) rightLayout.getChildren().get(0);
		BorderPane view = (BorderPane) centerLayout.getChildren().get(0);
		return view;
	}

}
