package controller;

import java.awt.Label;
import java.io.IOException;

import controller.bottomController.BottomController;
import controller.centerController.CenterController;
import controller.leftController.LeftController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class MainController {
	
	@FXML
	private BorderPane rootLayout;
	@FXML
	private BorderPane centerLayout;
	
	@FXML
	private void initialize() {
		try {
			setLeftLayout();
			setCenterLayout();
			setBottomLayout();
		} catch (Exception e) {}
	}
	
	private void setCenterLayout() throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/fxml/center/CenterLayout.fxml"));
		loader.setController(new CenterController());
		Parent layout = loader.load();
		centerLayout.setCenter(layout);
	}

	private void setBottomLayout() throws Exception{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/fxml/bottom/BottomLayout.fxml"));
		loader.setController(new BottomController());
		Parent layout = loader.load();
		centerLayout.setBottom(layout);
	}

	private void setLeftLayout() throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/fxml/left/LeftLayout.fxml"));
		loader.setController(new LeftController());
		Parent layout = loader.load();
		rootLayout.setLeft(layout);
	}
}
