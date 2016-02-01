package GUI;

import java.awt.Label;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 
public class TestJavaFX extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {

		BorderPane root = new BorderPane();
		Scene scene = new Scene(root,400,400);
		
		HBox layout1 = new HBox();
		layout1.setStyle("-fx-background-color: #CCFF99;");
		HBox layout2 = new HBox();
		layout2.setStyle("-fx-backgroun-color:#ffffff;");
		HBox layout3 = new HBox();
		HBox layout4 = new HBox();
		
		
		Button button1 = new Button("This is view 1");
		Button button2 = new Button("This is view 2");
		Button button3 = new Button("This is view 3");
		
		Button button = new Button("Change view");
		
		root.setBottom(layout3);
		root.setCenter(layout1);
		root.setLeft(layout4);
		
		layout3.getChildren().addAll(button);
		layout2.getChildren().add(button2);
		layout1.getChildren().addAll(button1);
		layout4.getChildren().add(button3);
		
		button.setOnAction(e -> {
			root.setCenter(layout2);
		});
		
		
		primaryStage.setScene(scene);
		primaryStage.show();
    }
}