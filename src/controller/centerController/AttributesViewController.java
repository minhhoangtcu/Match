package controller.centerController;

import application.Model;
import javafx.scene.layout.BorderPane;

public class AttributesViewController {
	BorderPane rootLayout;
	Model model;
	
	public AttributesViewController(BorderPane rootLayout, Model model) {
		this.rootLayout = rootLayout;
		this.model = model;
	}
	
	public void add() {
		System.out.println("From add: AttributesViewController");
	}
}
