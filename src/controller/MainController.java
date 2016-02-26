package controller;

import java.awt.Label;
import java.io.IOException;

import application.Model;
import controller.bottomController.BottomController;
import controller.centerController.CenterController;
import controller.leftController.LeftController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class MainController {
	
	private Model model;
	
	@FXML
	private BorderPane rootLayout;
	@FXML
	private BorderPane centerLayout;
	
	public MainController(Model model) {
		this.model = model;
	}
	
	@FXML
	private void initialize() {
		try {
			setCenterLayout();
			setBottomLayout();
		} catch (Exception e) {}
	}
	
	private void setCenterLayout() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/fxml/center/CenterLayout.fxml"));
		loader.setController(new CenterController());
		try {
			Parent layout = loader.load();
			centerLayout.setCenter(layout);
		} catch (IOException e) {
			System.out.println("Fail to load in setCenterLayout: MainController");
		}
	}

	private void setBottomLayout() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/fxml/bottom/BottomLayout.fxml"));
		loader.setController(new BottomController(rootLayout, model));
		try {
			Parent layout = loader.load();
			centerLayout.setBottom(layout);
		} catch (IOException e) {
			System.out.println("Fail to load in setBottomLayout: MainController");
		}
	}
}
