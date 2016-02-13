package controller.bottomController;

import java.io.FileNotFoundException;

import helper.LoadFile;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class AddUserBarController {
	
	private BorderPane rootLayout;
	
	public AddUserBarController(BorderPane rootLayout) {
		this.rootLayout = rootLayout;
	}
	
	/**
	 * 
	 * Load Add user interface and load its Controller
	 * 
	 * @throws Exception
	 */
	public void addUser() throws Exception {
		// get center layout
		BorderPane view = getCenterLayout(rootLayout);
		
		// load and set center layout
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/fxml/center/AddUserLayout.fxml"));
		Parent layout = loader.load();
		view.setCenter(layout);
	}
	
	public void addAllUser() {
		String URL = "";
		try {
			URL = LoadFile.getURL();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			return;
		}
		System.out.println(URL);
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
