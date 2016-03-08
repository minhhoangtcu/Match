package io.match.gui.center.match;

import java.beans.EventHandler;

import io.match.Model;
import io.match.datastructure.Person;
import io.match.gui.left.Row;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class MatchViewController {
	private BorderPane rootLayout;
	private Model model;

	@FXML
	private TableView tableView;

	@FXML
	private TableColumn studentColumn;

	@FXML
	private TableColumn facultyColumn;

	@FXML
	private TableColumn probabilityColumn;

	@FXML
	private Button btnMatch;

	@FXML
	private TextField tfStudent;

	@FXML
	private TextField tfFaculty;

	public void setModel(Model model) {
		this.model = model;
	}

	public void setRootLayout(BorderPane rootLayout) {
		this.rootLayout = rootLayout;
	}

	public void match() {
		System.out.println("From match: MatchViewController");
	}

	public void populateMatchTable() {
		populateMatchTable("", "");
	}

	@FXML
	private void shortenTable() {
		String studentName = tfStudent.getText().toLowerCase();
		String facultyName = tfFaculty.getText().toLowerCase();
		populateMatchTable(studentName, facultyName);
	}

	private void populateMatchTable(String studentRegex, String facultyRegex) {
		ObservableList<MatchRow> observableList = FXCollections.observableArrayList();
		for (Person student : model.getStudents()) {
			for (Person faculty : model.getFaculties()) {
				String studentName = student.getName();
				String facultyName = faculty.getName();
				boolean matchedStudent = student.isMatched();
				boolean matchedFaculty = faculty.isMatched();
				boolean matchedStudentReg = studentName.toLowerCase().contains(studentRegex);
				boolean matchedFacultyReg = facultyName.toLowerCase().contains(facultyRegex);
				if (!matchedStudent && !matchedFaculty && matchedStudentReg && matchedFacultyReg) {
					observableList.add(new MatchRow(studentName, facultyName, 0.0));
				}
			}
		}
		studentColumn.setCellValueFactory(new PropertyValueFactory<MatchRow, String>("studentName"));
		facultyColumn.setCellValueFactory(new PropertyValueFactory<MatchRow, String>("facultyName"));
		probabilityColumn.setCellValueFactory(new PropertyValueFactory<MatchRow, Double>("probability"));
		tableView.setItems(observableList);
	}
}