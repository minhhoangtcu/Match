package io.match.gui.center.manage;

import io.match.Model;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;

public class AttributesViewController {
	
	BorderPane rootLayout;
	Model model;
	
	@FXML
	private Button btnAddNew;
	
	@FXML
	private ScrollPane scrollPane;
	
		
	public void setModel(Model model) {
		this.model = model;
	}
	
	public void setRootLayout(BorderPane rootLayout) {
		this.rootLayout = rootLayout;
	}
	
	public void add() {
		System.out.println("From add: AttributesViewController");
	}
}
