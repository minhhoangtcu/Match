package io.match.gui.center.match;

import io.match.Model;
import io.match.algorithm.Compare;
import io.match.datastructure.Person;
import io.match.gui.MainController;
import io.match.gui.popup.match.PopupMatch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class MatchViewController {
	
	private Model model;
	private MainController mController;
	private static final int STUDENT_COL = 0;
	private static final int FALCUTY_COL = 1;
	private static final int PROBABILITY_COL = 2;
	private static final int SIMILARITIES_NUM = 2;

	/*
	 * GUI's
	 */
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
	
	private PopupMatch popupMatch;
	
	public void setModel(Model model) {
		this.model = model;
	}
	
	public void setMainController(MainController controller) {
		mController = controller;
		populateMatchTable("", "");
		popupMatch = new PopupMatch(controller.getPrimaryStage());

		tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent click) {
				
				if (click.getClickCount() == 2) {
					TablePosition position = tableView.getSelectionModel().getSelectedCells().get(0);
					int row = position.getRow();
					int col = position.getColumn();
					
					if (col == PROBABILITY_COL) {
						Person student = model.getStudent(getStudentName(row));
						Person falcuty = model.getFaculty(getFalcutyName(row));
						popupMatch.showPopup(student.getName(), falcuty.getName(), Compare.getTopSimilarities(student, falcuty, SIMILARITIES_NUM));
						System.out.printf("%s and %s\n", student.getName(), falcuty.getName());
						System.out.println();
					}
					else {
						
						//TODO: Pop up person
						
					}
				}
			}
		});
	}
	
	private String getStudentName(int row) {
		return (String) tableView.getColumns().get(STUDENT_COL).getCellObservableValue(row).getValue();
	}
	
	private String getFalcutyName(int row) {
		return (String) tableView.getColumns().get(FALCUTY_COL).getCellObservableValue(row).getValue();
	}
	
	private double getProbability(int row) {
		return (double) tableView.getColumns().get(PROBABILITY_COL).getCellObservableValue(row).getValue();
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
