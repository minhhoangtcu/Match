package controller.centerController;

import java.io.FileNotFoundException;
import java.io.IOException;

import application.Model;
import helper.LoadFile;
import io.match.datastructure.Person;
import io.match.datastructure.attributes.Attribute;
import io.match.datastructure.attributes.AttributeType;
import javafx.scene.layout.BorderPane;

public class LoadViewController {
	private BorderPane rootLayout;
	private Model model;
	private String studentsURL = "", 
			fixedStudentsURL = "",
			facultiesURL = "", 
			fixedFacultiesURL = "";
	
	public LoadViewController(BorderPane rootLayout, Model model) {
		this.rootLayout = rootLayout;
		this.model = model;
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
				System.out.println("test");
				for (Person person : model.getStudents()) {
					System.out.println(person.getName());
				}
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
