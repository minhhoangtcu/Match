package io.match.gui.center.manage;

import io.match.Model;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class DisplayPersonController {
	BorderPane rootLayout;
	Model model;
	
	public void setModel(Model model) {
		this.model = model;
	}
	
	public void setRootLayout(BorderPane rootLayout) {
		this.rootLayout = rootLayout;
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
