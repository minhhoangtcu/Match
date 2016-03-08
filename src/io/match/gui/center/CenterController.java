package io.match.gui.center;

import java.io.IOException;

import io.match.Match;
import io.match.Model;
import io.match.gui.MainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class CenterController {
	
	private Model model;
	private MainController mController;
	
	@FXML
	private BorderPane centerLayout;
	
	public void setModel(Model model) {
		this.model = model;
	}
	
	public void setMainController(MainController controller) {
		mController = controller;
		mController.setCenterLayout(centerLayout);
	}
	
	public void setIntroductionView() {
		try {
			FXMLLoader loader = new FXMLLoader(Match.class.getResource("gui/center/IntroductionView.fxml"));
			Parent layout = loader.load();
			mController.getCenterLayout().setCenter(layout);
		} catch (IOException e) {
			System.out.println("Fail to load FXML in setCenterLayout: CenterController");
		}
	}
	
	
}
