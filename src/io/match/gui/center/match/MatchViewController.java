package io.match.gui.center.match;

import io.match.Model;
import javafx.scene.layout.BorderPane;

public class MatchViewController {
	BorderPane rootLayout;
	Model model;
	
	public void setModel(Model model) {
		this.model = model;
	}
	
	public void setRootLayout(BorderPane rootLayout) {
		this.rootLayout = rootLayout;
	}
	
	public void match() {
		System.out.println("From match: MatchViewController");
	}
}
