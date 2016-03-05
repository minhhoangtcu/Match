package io.match.gui;

import java.io.IOException;

import io.match.Match;
import io.match.Model;
import io.match.gui.bottom.BottomController;
import io.match.gui.center.CenterController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class MainController {
	
	private Model model;
	
	@FXML
	private BorderPane rootLayout;
	
	public void setModel(Model model) {
		this.model = model;
	}
	
	public void setCenterLayout() {
		try {
			FXMLLoader loader = new FXMLLoader(Match.class.getResource("gui/center/Centerlayout.fxml"));
			Parent layout = loader.load();
			
			CenterController controller = loader.getController();
			controller.setModel(model);
			controller.setCenterLayout();
			rootLayout.setCenter(layout);
		} catch (IOException e) {
			System.out.println("Fail to load in setCenterLayout: MainController");
		}
	}

	public void setBottomLayout() {
		try {
			FXMLLoader loader = new FXMLLoader(Match.class.getResource("gui/bottom/BottomLayout.fxml"));
			Parent layout = loader.load();
			
			BottomController controller = loader.getController();
			controller.setModel(model);
			controller.setRootLayout(rootLayout);
			controller.setCenterLayout();
			rootLayout.setBottom(layout);
		} catch (IOException e) {
			System.out.println("Fail to load in setBottomLayout: MainController");
		}
	}
}
