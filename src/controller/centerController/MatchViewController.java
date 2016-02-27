package controller.centerController;

import application.Model;
import javafx.scene.layout.BorderPane;

public class MatchViewController {
	BorderPane rootLayout;
	Model model;
	
	public MatchViewController(BorderPane rootLayout, Model model) {
		this.rootLayout = rootLayout;
		this.model = model;
	}
	
	public void match() {
		System.out.println("From match: MatchViewController");
	}
}
