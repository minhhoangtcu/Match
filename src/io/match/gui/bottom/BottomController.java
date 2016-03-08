package io.match.gui.bottom;

import java.io.IOException;

import io.match.Match;
import io.match.Model;
import io.match.gui.MainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class BottomController {
	
	private Model model;
	private MainController mController;
	
	@FXML
	private BorderPane bottomLayout;
	private BorderPane rootLayout;
	
	
	public void setModel(Model model) {
		this.model = model;
	}
	
	public void setMainController(MainController controller) {
		mController = controller;
		rootLayout = mController.getRootLayout();
		initialize();
	}
	
	private void initialize() {
		setCenterLayout();
	}
	
	private void setCenterLayout() {
		try {
			FXMLLoader loader = new FXMLLoader(Match.class.getResource("gui/bottom/MainButtonBar.fxml"));
			Parent layout = loader.load();
			MainButtonBarController controller = loader.getController();

			controller.setModel(model);
			controller.setMainController(mController);
			bottomLayout.setCenter(layout);
		} catch (IOException e) {
			System.out.println("Fail to load FXML file in setCenterLayout: BottomController");
		}
	}

}
