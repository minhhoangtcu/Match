package helper;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class LayoutFetcher {
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
	public static BorderPane getCenterLayout(BorderPane rootLayout) {
		BorderPane rightLayout = (BorderPane) rootLayout.getChildren().get(0);
		AnchorPane centerLayout = (AnchorPane) rightLayout.getChildren().get(0);
		BorderPane view = (BorderPane) centerLayout.getChildren().get(0);
		return view;
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
	public static BorderPane getBottomLayout(BorderPane rootLayout) {
		BorderPane rightLayout = (BorderPane) rootLayout.getChildren().get(0);
		AnchorPane bottomLayout = (AnchorPane) rightLayout.getChildren().get(1);
		BorderPane view = (BorderPane) bottomLayout.getChildren().get(0);
		return view;
	}
}
