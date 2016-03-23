package io.match.gui.center.load;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import io.match.Model;
import io.match.helper.LoadFile;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LoadViewController {

	private Model model;
	private String studentsURL, fixedStudentsURL, facultiesURL, fixedFacultiesURL;

	@FXML
	Label infoLoadStudentData;

	@FXML
	Label infoLoadStudentFA;

	@FXML
	Label infoLoadFalcutyData;

	@FXML
	Label infoLoadFalcutyFA;

	public void setModel(Model model) {
		this.model = model;
	}

	public void loadStudents() {
		System.out.println("From loadStudents: LoadViewController");
		File data = LoadFile.getFile();
		if (data == null)
			alertFileNotFound();
		else {
			studentsURL = data.getAbsolutePath();
			infoLoadStudentData.setText(data.getName());
			tryPopulatePersons();
		}
	}

	public void loadFixedStudents() throws FileNotFoundException {
		System.out.println("From loadFixedStudents: LoadViewController");
		File data = LoadFile.getFile();
		if (data == null)
			alertFileNotFound();
		else {
			fixedStudentsURL = data.getAbsolutePath();
			infoLoadStudentFA.setText(data.getName());
			tryPopulatePersons();
		}
	}

	public void loadFaculties() throws FileNotFoundException {
		System.out.println("From loadFaculties: LoadViewController");
		File data = LoadFile.getFile();
		if (data == null)
			alertFileNotFound();
		else {
			facultiesURL = data.getAbsolutePath();
			infoLoadFalcutyData.setText(data.getName());
			tryPopulatePersons();
		}
	}

	public void loadFixedFaculties() throws FileNotFoundException {
		System.out.println("From loadFixedFaculties: LoadViewController");
		File data = LoadFile.getFile();
		if (data == null)
			alertFileNotFound();
		else {
			fixedFacultiesURL = data.getAbsolutePath();
			infoLoadFalcutyFA.setText(data.getName());
			tryPopulatePersons();
		}
	}

	private void alertFileNotFound() {
		// SHOW POPUP WINDOW ALERTING USER
	}
	
	private void tryPopulatePersons() {
		if (!studentsURL.equals("") && !fixedStudentsURL.equals("") && !facultiesURL.equals("")
				&& !fixedFacultiesURL.equals("")) {
			try {
				model.loadStudents(studentsURL, fixedStudentsURL);
				model.loadFalcuty(facultiesURL, fixedFacultiesURL);
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		} else {
			System.out.println(
					studentsURL + "\n" + fixedStudentsURL + "\n" + facultiesURL + "\n" + fixedFacultiesURL + "\n");
		}
	}
}
