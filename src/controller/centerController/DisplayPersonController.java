package controller.centerController;

import application.Model;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class DisplayPersonController {
	BorderPane rootLayout;
	Model model;
	
	public DisplayPersonController(BorderPane rootLayout, Model model) {
		this.rootLayout = rootLayout;
		this.model = model;
	}
	
	@FXML
	public void initialize() {
		
	}
	
	public void add() {
		System.out.println("From add: DisplayPersonController");
	}
	
	public void modify() {
		System.out.println("From modify: DisplayPersonController");	
	}
	
	public void delete() {
		System.out.println("From delete: DisplayPersonController");
	}
}
