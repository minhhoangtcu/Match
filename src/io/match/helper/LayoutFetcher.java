package io.match.helper;

import javafx.scene.control.TableView;
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
	 * @return center layout
	 */
	public static BorderPane getCenterLayout(BorderPane rootLayout) {
		AnchorPane centerLayout = (AnchorPane) rootLayout.getChildren().get(0);
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
	 * @return bottom layout
	 */
	public static BorderPane getBottomLayout(BorderPane rootLayout) {
		AnchorPane bottomLayout = (AnchorPane) rootLayout.getChildren().get(1);
		BorderPane view = (BorderPane) bottomLayout.getChildren().get(0);
		return view;
	}
	
	/**
	 * Since the overall layout is a nested layout. This method try to trace through children
	 * and try to fetch out the correct layout wanted to modify
	 * 
	 * 
	 * 
	 * @param rootLayout
	 * @return leftLayout
	 */
	
	public static TableView getTableInLeftLayout(BorderPane rootLayout) {
		AnchorPane leftLayout = (AnchorPane) rootLayout.getLeft();
		BorderPane borderPane = (BorderPane) leftLayout.getChildren().get(0);
		TableView table = (TableView) borderPane.getCenter();
		return table;
	}
}
