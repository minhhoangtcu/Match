package controller.centerController;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class CenterController {
	
	@FXML
	private BorderPane centerLayout;
	
	@FXML
	private void initialize() {
		try {
			setCenterLayout();
		} catch (Exception e) {}
	}
	
	private void setCenterLayout() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/fxml/center/IntroductionView.fxml"));
		try {
			Parent layout = loader.load();
			centerLayout.setCenter(layout);
		} catch (IOException e) {
			System.out.println("Fail to load FXML in setCenterLayout: CenterController");
		}
	}
	
	
}
