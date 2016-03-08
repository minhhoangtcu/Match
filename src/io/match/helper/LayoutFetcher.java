package io.match.helper;

import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


/*
 * 
 * 
 * 
 * 
 * 
 * UNUSED
 * 
 * 
 * 
 * 
 * 
 */

public class LayoutFetcher {
	
	private static BorderPane centerLayout;
	private static BorderPane bottomLayout;
	private static TableView tableView;
	
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
		if (centerLayout == null) {
			AnchorPane temp = (AnchorPane) rootLayout.getChildren().get(0);
			centerLayout = (BorderPane) temp.getChildren().get(0);
		}
		return centerLayout;
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
		if (bottomLayout == null) {
			AnchorPane temp = (AnchorPane) rootLayout.getChildren().get(1);
			bottomLayout = (BorderPane) temp.getChildren().get(0);
		}
		return bottomLayout;
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
		if (tableView == null) {
			AnchorPane leftLayout = (AnchorPane) rootLayout.getLeft();
			BorderPane borderPane = (BorderPane) leftLayout.getChildren().get(0);
			tableView = (TableView) borderPane.getCenter();
		}
		return tableView;
	}
}
