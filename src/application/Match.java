package application;

import java.io.IOException;

import controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;

public class Match extends Application {
	
	private Stage primaryStage;
	
	
	public static void main(String args[]) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		primaryStage.setTitle("Example");
		
		
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/fxml/MainFXML.fxml"));
		loader.setController(new MainController());
		BorderPane rootLayout = (BorderPane) loader.load();
		Scene scene = new Scene(rootLayout);
		primaryStage.setScene(scene);

		
		primaryStage.show();
	}
}
