package controller.centerController;

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
	
	private void setCenterLayout() throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/fxml/center/IntroductionView.fxml"));
		Parent layout = loader.load();
		centerLayout.setCenter(layout);
	}
	
	
}
