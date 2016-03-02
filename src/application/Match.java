package application;

import java.io.FileNotFoundException;
import java.io.IOException;

import controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Match extends Application {
	
	private Model model;
	
	private Stage primaryStage;
	
	
	public static void main(String args[]) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		primaryStage.setTitle("Match - Basic Layout");
		
		setUpModel();
		setUpPrimaryStage();
		
		primaryStage.show();
	}
	
	private void setUpModel() {
		try {
			model = new Model();
		} catch (FileNotFoundException e) {
			// TODO: Handle missing files
			e.printStackTrace();
		} catch (IOException e) {
			// TODO: Handle cannot read files
			e.printStackTrace();
		}
	}
	
	private void setUpPrimaryStage() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/fxml/MainFXML.fxml"));
		loader.setController(new MainController(model));
		
		try {
			BorderPane rootLayout = (BorderPane) loader.load();
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
		} catch (IOException e) {
			// TODO: Handle missing fxml/cannot read
			e.printStackTrace();
		}
	}
	
	public Model getModel() {
		return model;
	}
}
