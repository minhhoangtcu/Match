package io.match.gui.center.manage;

import io.match.Model;
import io.match.gui.MainController;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class DisplayPersonController {
	
	private Model model;
	private MainController mController;
	
	public void setModel(Model model) {
		this.model = model;
	}
	
	public void setMainController(MainController controller) {
		mController = controller;
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
