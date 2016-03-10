package io.match.gui.left;

import java.util.LinkedList;
import io.match.datastructure.Person;
import io.match.datastructure.attributes.Attribute;
import io.match.datastructure.attributes.AttributeUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TablePopulator {

	public static void populateStudent(TableView<TableRow> tableView, LinkedList<Person> students) {
		
		tableView.getColumns().removeAll(tableView.getColumns());
		
		ObservableList<TableRow> observableList = FXCollections.observableArrayList();
		for (Person person: students) {
			String name = person.getName();
			Boolean match = person.isMatched();
			observableList.add(new TableRow(name, convertBoolean(match)));
		}
		
		TableColumn<TableRow, String> nameColumn = new TableColumn<>("Student");
		nameColumn.setCellValueFactory(new PropertyValueFactory<TableRow,String>("name"));
		
		TableColumn<TableRow, String> matchedColumn = new TableColumn<>("Match");
		matchedColumn.setCellValueFactory(new PropertyValueFactory<TableRow,String>("value"));
		
		matchedColumn.setStyle("-fx-alignment: CENTER;");
		
		tableView.getColumns().addAll(nameColumn, matchedColumn);
		tableView.setItems(observableList);
	}

	public static void populateFaculties(TableView<TableRow> tableView, LinkedList<Person> faculties) {
		
		tableView.getColumns().removeAll(tableView.getColumns());
		
		ObservableList<TableRow> observableList = FXCollections.observableArrayList();
		for (Person person: faculties) {
			String name = person.getName();
			Boolean match = person.isMatched();
			observableList.add(new TableRow(name, convertBoolean(match)));
		}
		
		TableColumn<TableRow, String> nameColumn = new TableColumn<>("Faculty");
		nameColumn.setCellValueFactory(new PropertyValueFactory<TableRow,String>("name"));
		
		TableColumn<TableRow, String> matchedColumn = new TableColumn<>("Match");
		matchedColumn.setCellValueFactory(new PropertyValueFactory<TableRow,String>("value"));
		
		matchedColumn.setStyle("-fx-alignment: CENTER;");
		
		tableView.getColumns().addAll(nameColumn, matchedColumn);
		tableView.setItems(observableList);
	}
	
	public static void populateAttributes(TableView<TableRow> tableView, LinkedList<Attribute> attributes) {
		
		tableView.getColumns().removeAll(tableView.getColumns());
		
		ObservableList<TableRow> observableList = FXCollections.observableArrayList();	
		for (Attribute attribute: attributes) {
			String name = attribute.getAttributeName();
			String type = AttributeUtil.getStringFromType(attribute.getAttributeType());
			observableList.add(new TableRow(name, type));
		}
		
		TableColumn<TableRow, String> nameColumn = new TableColumn<>("Field");
		nameColumn.setCellValueFactory(new PropertyValueFactory<TableRow,String>("name"));
		
		TableColumn<TableRow, String> typeColumn = new TableColumn<>("Attribute Type");
		typeColumn.setCellValueFactory(new PropertyValueFactory<TableRow,String>("value"));
		
		tableView.getColumns().addAll(nameColumn, typeColumn);
		tableView.setItems(observableList);
	}
	
	private static String convertBoolean(Boolean match) {
		return match == true ? "X" : "";
	}
	
}
