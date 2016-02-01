package gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class TestFXML extends Application {
	
	private Stage primaryStage;
	private BorderPane rootLayout;
	
	
	public static void main(String args[]) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		primaryStage.setTitle("Example");
		
		
		rootLayout = (BorderPane) FXMLLoader.load(getClass().getResource("fxml/MainFXML.fxml"));
		Scene scene = new Scene(rootLayout);
		primaryStage.setScene(scene);

		
		primaryStage.show();
	}
}
