package gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class BottomController {
	@FXML
	Button button_1;
	@FXML
	Button button_2;
	
	BorderPane rootLayout;	
	
	public BottomController(BorderPane rootLayout) {
		this.rootLayout = rootLayout;
	}
	
	
	@FXML
	private void initialize() {}
	
	
	@FXML
	private void changeView1() throws  Exception{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/CenterFXML.fxml"));

		Parent layout = loader.load();
		rootLayout.setCenter(layout);
	}
	
	@FXML
	private void changeView2() throws Exception{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Center2FXML.fxml"));

		Parent layout = loader.load();
		rootLayout.setCenter(layout);
	}
	
}
