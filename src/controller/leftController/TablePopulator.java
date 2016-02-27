package controller.leftController;

import java.util.LinkedList;

import io.match.datastructure.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TablePopulator {

	public static void populateStudent(TableView tableView, LinkedList<Person> students) {
		
		System.out.println("From populateStudent: TablePopulator");
		
		tableView.getColumns().remove(0);
		tableView.getColumns().remove(0);
		
		ObservableList<Row> observableList = FXCollections.observableArrayList();
		for (Person person: students) {
			String name = person.getGeneralAttribute("name");
			observableList.add(new Row(name));
		}
		
		TableColumn nameColumn = new TableColumn("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<Row,String>("name"));
		
		TableColumn matchedColumn = new TableColumn("Matched");
		matchedColumn.setCellValueFactory(new PropertyValueFactory<Row,String>("name"));

		
		tableView.setItems(observableList);
		tableView.getColumns().addAll(nameColumn, matchedColumn);
	}
	
	public static void populateFaculties(TableView tableView, LinkedList<Person> faculties) {
		
		tableView.getColumns().remove(0);
		tableView.getColumns().remove(0);
		
		ObservableList<Row> observableList = FXCollections.observableArrayList();
		for (Person person: faculties) {
			String name = person.getGeneralAttribute("name");
			observableList.add(new Row(name));
		}
		
		TableColumn nameColumn = new TableColumn("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<Row,String>("name"));
		
		TableColumn matchedColumn = new TableColumn("Matched");
		matchedColumn.setCellValueFactory(new PropertyValueFactory<Row,String>("name"));

		
		tableView.setItems(observableList);
		tableView.getColumns().addAll(nameColumn, matchedColumn);
	}
	
}
