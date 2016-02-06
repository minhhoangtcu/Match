package controller.bottomController;

import javax.swing.text.View;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class MainButtonBarController {
	
	@FXML
	private Button btnAddUser;
	@FXML
	private Button btnDeleteUser;
	@FXML
	private Button btnModifyUser;
	@FXML
	private Button btnMatchUser;
	
	BorderPane rootLayout;
	
	public MainButtonBarController(BorderPane rootLayout) {
		this.rootLayout = rootLayout;
	}
	
	/**
	 * 
	 * Load Add user interface and load its Controller
	 * 
	 * @throws Exception
	 */
	public void loadAddUser() throws Exception {
		// get center Layout
		BorderPane view = getCenterLayout(rootLayout);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/fxml/center/AddUserLayout.fxml"));
		Parent layout = loader.load();
		view.setCenter(layout);
		
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
