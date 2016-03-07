package io.match.gui.bottom;

import java.io.IOException;

import io.match.Match;
import io.match.Model;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class BottomController {
	
	private Model model;
	
	@FXML
	private BorderPane bottomLayout;
	private BorderPane rootLayout;
	
	
	public void setModel(Model model) {
		this.model = model;
	}
	
	public void setRootLayout(BorderPane rootLayout) {
		this.rootLayout = rootLayout;
	}
	
	public void setCenterLayout() {
		try {
			FXMLLoader loader = new FXMLLoader(Match.class.getResource("gui/bottom/MainButtonBar.fxml"));
			Parent layout = loader.load();
			MainButtonBarController controller = loader.getController();
			
			controller.setModel(model);
			controller.setRootLayout(rootLayout);
			bottomLayout.setCenter(layout);
		} catch (IOException e) {
			System.out.println("Fail to load FXML file in setCenterLayout: BottomController");
		}
	}

}
