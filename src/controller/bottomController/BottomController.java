package controller.bottomController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class BottomController {
	
	@FXML
	private BorderPane bottomLayout;
	
	@FXML
	private void initialize() {
		try {
			setCenterLayout();
		} catch (Exception e) {}
	}
	
	private void setCenterLayout() throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/fxml/bottom/MainButtonBar.fxml"));
		Parent layout = loader.load();
		bottomLayout.setCenter(layout);
	}

}
