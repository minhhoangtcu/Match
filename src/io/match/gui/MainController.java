package io.match.gui;

import java.io.IOException;

import io.match.Match;
import io.match.Model;
import io.match.gui.bottom.BottomController;
import io.match.gui.center.CenterController;
import io.match.gui.left.LeftController;
import io.match.gui.left.TableRow;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainController {
	
	private Model model;
	
	@FXML
	private BorderPane rootLayout;
	
	@FXML
	private BorderPane centerLayout;
	
	@FXML
	private AnchorPane leftLayout;
	
	@FXML
	private TableView<TableRow> leftTableView;
	
	@FXML
	private Stage primaryStage;
	
	public void setModel(Model model) {
		this.model = model;
		initialize();
	}
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	private void initialize() {
		setCenterLayout();
		setBottomLayout();
		setLeftLayout();
	}
	
	private void setCenterLayout() {
		try {
			FXMLLoader loader = new FXMLLoader(Match.class.getResource("gui/center/Centerlayout.fxml"));
			Parent layout = loader.load();
			
			CenterController controller = loader.getController();
			controller.setMainController(this);
			controller.setIntroductionView();
			rootLayout.setCenter(layout);
		} catch (IOException e) {
			System.out.println("Fail to load in setCenterLayout: MainController");
		}
	}

	private void setBottomLayout() {
		try {
			FXMLLoader loader = new FXMLLoader(Match.class.getResource("gui/bottom/BottomLayout.fxml"));
			Parent layout = loader.load();
			
			BottomController controller = loader.getController();
			controller.setModel(model);
			controller.setMainController(this);
			rootLayout.setBottom(layout);
		} catch (IOException e) {
			System.out.println("Fail to load in setBottomLayout: MainController");
		}
	}
	
	private void setLeftLayout() {
		try {
			FXMLLoader loader = new FXMLLoader(Match.class.getResource("gui/left/LeftLayout.fxml"));
			leftLayout = (AnchorPane) loader.load();

			LeftController controller = loader.getController();
			controller.setModel(model);
			controller.setMainController(this);
			
		} catch (IOException e) {
			System.out.println("Fail to load in loadStudents: MainButtonBarController");
		}
	}
	
	

	public BorderPane getRootLayout() {
		return rootLayout;
	}

	public void setRootLayout(BorderPane rootLayout) {
		this.rootLayout = rootLayout;
	}

	public BorderPane getCenterLayout() {
		return centerLayout;
	}

	public void setCenterLayout(BorderPane centerLayout) {
		this.centerLayout = centerLayout;
	}

	public TableView<TableRow> getLeftTableView() {
		return leftTableView;
	}

	public void setLeftTableView(TableView<TableRow> leftTableView) {
		this.leftTableView = leftTableView;
	}

	public AnchorPane getLeftLayout() {
		return leftLayout;
	}

	public void setLeftLayout(AnchorPane leftLayout) {
		this.leftLayout = leftLayout;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}
}
