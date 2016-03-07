package io.match;

import java.io.FileNotFoundException;
import java.io.IOException;
import io.match.gui.MainController;
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
		try {
			FXMLLoader loader = new FXMLLoader(Match.class.getResource("gui/MainFXML.fxml"));
						
			BorderPane rootLayout = (BorderPane) loader.load();
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			
			MainController controller = loader.getController();
			controller.setModel(model);
			controller.setCenterLayout();
			controller.setBottomLayout();
			
		} catch (IOException e) {
			System.out.println("Fail to load setUpPrimaryStage: Match");
		}
	}
	
	public Model getModel() {
		return model;
	}
}
