package io.match.gui.center.load;

import java.io.FileNotFoundException;
import java.io.IOException;

import io.match.Model;
import io.match.datastructure.Person;
import io.match.datastructure.attributes.Attribute;
import io.match.datastructure.attributes.AttributeType;
import io.match.helper.LoadFile;
import javafx.scene.layout.BorderPane;

public class LoadViewController {
	
	private BorderPane rootLayout;
	private Model model;
	private String studentsURL = "", 
			fixedStudentsURL = "",
			facultiesURL = "", 
			fixedFacultiesURL = "";
	
	public void setModel(Model model) {
		this.model = model;
	}
	
	public void setRootLayout(BorderPane rootLayout) {
		this.rootLayout = rootLayout;
	}
	
	public void loadStudents() {
		System.out.println("From loadStudents: LoadViewController");
		try {
			studentsURL = LoadFile.getURL();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		tryPopulatePersons();
	}
	
	public void loadFixedStudents() {
		System.out.println("From loadFixedStudents: LoadViewController");
		try {
			fixedStudentsURL = LoadFile.getURL();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		tryPopulatePersons();
	}
	
	public void loadFaculties() {
		System.out.println("From loadFaculties: LoadViewController");
		try {
			facultiesURL = LoadFile.getURL();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		tryPopulatePersons();
	}
	
	public void loadFixedFaculties() {
		System.out.println("From loadFixedFaculties: LoadViewController");
		try {
			fixedFacultiesURL = LoadFile.getURL();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		tryPopulatePersons();
	}

	private void tryPopulatePersons() {
		if (!studentsURL.equals("")
				&& !fixedStudentsURL.equals("")
				&& !facultiesURL.equals("")
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
			System.out.println(studentsURL + "\n" + fixedStudentsURL + "\n" + facultiesURL + "\n" + fixedFacultiesURL + "\n");
		}
	}
}
