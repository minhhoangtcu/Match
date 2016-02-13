package controller.bottomController;

import application.Model;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class BottomController {
	
	private Model model;
	
	@FXML
	private BorderPane bottomLayout;
	private BorderPane rootLayout;
	
	
	public BottomController (BorderPane rootLayout, Model model) {
		this.rootLayout = rootLayout;
		this.model = model;
	}
	
	@FXML
	private void initialize() {
		try {
			setCenterLayout();
		} catch (Exception e) {}
	}
	
	private void setCenterLayout() throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/fxml/bottom/MainButtonBar.fxml"));
		loader.setController(new MainButtonBarController(rootLayout, model));
		Parent layout = loader.load();
		bottomLayout.setCenter(layout);
	}

}
