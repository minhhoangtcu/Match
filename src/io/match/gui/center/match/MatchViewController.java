package io.match.gui.center.match;

import io.match.Model;
import io.match.algorithm.Compare;
import io.match.datastructure.Person;
import io.match.gui.MainController;
import io.match.gui.left.TableRow;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class MatchViewController {
	private Model model;
	private MainController mController;

	@FXML
	private TableView<MatchRow> tableView;

	@FXML
	private TableColumn<MatchRow, String> studentColumn;

	@FXML
	private TableColumn<MatchRow, String> facultyColumn;

	@FXML
	private TableColumn<MatchRow, Double> probabilityColumn;

	@FXML
	private Button btnMatch;

	@FXML
	private TextField tfStudent;

	@FXML
	private TextField tfFaculty;

	public void setModel(Model model) {
		this.model = model;
	}
	
	public void setMainController(MainController controller) {
		mController = controller;
		populateMatchTable("", "");
		

		tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent click) {
				if (click.getClickCount() == 2) {
					TablePosition position = tableView.getSelectionModel().getSelectedCells().get(0);
					int row = position.getRow();
					TableColumn column = position.getTableColumn();
					
					String data = "" + column.getCellObservableValue(row).getValue();
					System.out.println(data);
				}
			}
		});
	}
	
	@FXML
	private void match() {
		System.out.println("From match: MatchViewController");
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
				double probability = Math.round(Compare.getMatch(student, faculty) * 10.0) / 10.0;
				
				boolean matchedStudent = student.isMatched();
				boolean matchedFaculty = faculty.isMatched();
				boolean matchedStudentReg = studentName.toLowerCase().contains(studentRegex);
				boolean matchedFacultyReg = facultyName.toLowerCase().contains(facultyRegex);
				
				if (!matchedStudent && !matchedFaculty && matchedStudentReg && matchedFacultyReg) {
					observableList.add(new MatchRow(studentName, facultyName, probability));
				}
			}
		}
		
		studentColumn.setCellValueFactory(new PropertyValueFactory<MatchRow, String>("studentName"));
		facultyColumn.setCellValueFactory(new PropertyValueFactory<MatchRow, String>("facultyName"));
		probabilityColumn.setCellValueFactory(new PropertyValueFactory<MatchRow, Double>("probability"));
		probabilityColumn.setStyle("-fx-alignment: CENTER;");
		
		tableView.setItems(observableList);
	}
}
