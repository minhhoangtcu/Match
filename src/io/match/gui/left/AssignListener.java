package io.match.gui.left;

import javafx.scene.control.TableView;


/*
 * 
 * 
 * UNUSED
 * 
 * 
 * 
 */



public class AssignListener {
	
	public static void assignListener(TableView tableView) {
		tableView.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> popupPerson(newValue));
	}
	

	
	private static void popupPerson(Object newValue) {
		TableRow row = (TableRow) newValue;
		System.out.println(row);
	}

}
