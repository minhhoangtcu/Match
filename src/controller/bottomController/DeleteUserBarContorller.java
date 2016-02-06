package controller.bottomController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class DeleteUserBarContorller {
private BorderPane rootLayout;
	
	public DeleteUserBarContorller (BorderPane rootLayout) {
		this.rootLayout = rootLayout;
	}
	
	/**
	 * 
	 * Load Add user interface and load its Controller
	 * 
	 * @throws Exception
	 */
	public void loadDeleteUser() throws Exception {
		// get center layout
		BorderPane view = getCenterLayout(rootLayout);
		
		// load and set center layout
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/fxml/center/DeleteUserButtonBar.fxml"));
//		loader.setController(new AddUserController());
//		Parent layout = loader.load();
//		view.setCenter(layout);
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
