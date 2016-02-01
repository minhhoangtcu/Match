package gui;

import java.awt.Label;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class MainController {
	
	@FXML
	private BorderPane rootLayout;
	
	@FXML
	private void initialize() {
		try {
			setLeftLayout();
			setCenterLayout();
			setBottomLayout();
		} catch (Exception e) {}
	}
	
	private void setCenterLayout() throws Exception {		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/CenterFXML.fxml"));
		Parent layout = loader.load();
		rootLayout.setCenter(layout);
	}

	private void setBottomLayout() throws Exception{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/BottomFXML.fxml"));
		loader.setController(new BottomController(rootLayout));
		Parent layout = loader.load();
		rootLayout.setBottom(layout);
	}

	private void setLeftLayout() throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/ListFXML.fxml"));
		Parent layout = loader.load();
		rootLayout.setLeft(layout);		
	}
}
