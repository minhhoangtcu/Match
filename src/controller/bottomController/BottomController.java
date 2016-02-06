package controller.bottomController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class BottomController {
	
	@FXML
	private BorderPane bottomLayout;
	private BorderPane rootLayout;
	
	@FXML
	private void initialize() {
		try {
			setCenterLayout();
		} catch (Exception e) {}
	}
	
	public BottomController (BorderPane rootLayout) {
		this.rootLayout = rootLayout;
	}
	
	private void setCenterLayout() throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/fxml/bottom/MainButtonBar.fxml"));
		loader.setController(new MainButtonBarController(rootLayout));
		Parent layout = loader.load();
		bottomLayout.setCenter(layout);
	}

}
