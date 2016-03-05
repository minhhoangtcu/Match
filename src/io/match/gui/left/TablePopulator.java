package io.match.gui.left;

import java.util.LinkedList;

import javax.security.auth.callback.Callback;

import io.match.datastructure.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TablePopulator {

	public static TableView populateStudent(TableView tableView, LinkedList<Person> students) {
		
		
		ObservableList<Row> observableList = FXCollections.observableArrayList();
		for (Person person: students) {
			String name = person.getName();
			Boolean match = person.isMatched();
			observableList.add(new Row(name, convertBoolean(match)));
		}
		
		TableColumn nameColumn = (TableColumn) tableView.getColumns().get(0);
		nameColumn.setCellValueFactory(new PropertyValueFactory<Row,String>("name"));
		
		TableColumn matchedColumn = (TableColumn) tableView.getColumns().get(1);
		matchedColumn.setCellValueFactory(new PropertyValueFactory<Row,String>("match"));
		
		matchedColumn.setStyle("-fx-alignment: CENTER;");

		tableView.setItems(observableList);
		
		return tableView;
	}

	public static TableView populateFaculties(TableView tableView, LinkedList<Person> faculties) {
				
		ObservableList<Row> observableList = FXCollections.observableArrayList();
		for (Person person: faculties) {
			String name = person.getGeneralAttribute("name");
			Boolean match = person.isMatched();
			observableList.add(new Row(name, convertBoolean(match)));
		}
		
		TableColumn nameColumn = (TableColumn) tableView.getColumns().get(0);
		nameColumn.setCellValueFactory(new PropertyValueFactory<Row,String>("name"));
		
		TableColumn matchedColumn = (TableColumn) tableView.getColumns().get(1);
		matchedColumn.setCellValueFactory(new PropertyValueFactory<Row,String>("match"));

		
		tableView.setItems(observableList);
		
		return tableView;
	}
	

	
	private static String convertBoolean(Boolean match) {
		return match == true ? "X" : "";
	}
	
}
