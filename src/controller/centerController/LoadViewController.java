package controller.centerController;

import application.Model;
import javafx.scene.layout.BorderPane;

public class LoadViewController {
	BorderPane rootLayout;
	Model model;
	
	public LoadViewController(BorderPane rootLayout, Model model) {
		this.rootLayout = rootLayout;
		this.model = model;
	}
	
	public void loadStudents() {
		System.out.println("From loadStudents: LoadViewController");
	}
	
	public void loadFixedStudents() {
		System.out.println("From loadFixedStudents: LoadViewController");	
	}
	
	public void loadFaculties() {
		System.out.println("From loadFaculties: LoadViewController");
	}
	
	public void loadFixedFaculties() {
		System.out.println("From loadFixedFaculties: LoadViewController");
	}
}
