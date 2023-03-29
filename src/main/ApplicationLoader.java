package main;

import controller.LogicTutorController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.LogicalFormula;
import view.LogicTutorRootPane;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import java.io.*;
import java.util.Objects;

public class ApplicationLoader extends Application {

	private LogicTutorRootPane view;

	@Override
	public void init() throws FileNotFoundException {
		//create view and model and pass their references to the controller
		view = new LogicTutorRootPane();
		LogicalFormula model = new LogicalFormula();
		//Logic tutor model = new LogicTutor();
		new LogicTutorController(view, model);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// create an input stream
		FileInputStream icon = new FileInputStream(".//res//logic.png");

		// create an image
		Image iconImage = new Image(icon);
		//sets minimum width and height for the stage window
		stage.setMinWidth(1100); 
		stage.setMinHeight(750);
		stage.setHeight(800);
		stage.setWidth(1100);
		stage.setTitle("Logic Tutor");
		stage.setScene(new Scene(view));
		stage.getIcons().add(iconImage);
		stage.initStyle(StageStyle.DECORATED);
		stage.show();


		// create an input stream
		FileInputStream input = new FileInputStream(".//res//black.png");

		// create an image
		Image image = new Image(input);

		// create a background image
		BackgroundImage backgroundimage = new BackgroundImage(image, 
				BackgroundRepeat.REPEAT, 
				BackgroundRepeat.REPEAT, 
				BackgroundPosition.CENTER, 
				BackgroundSize.DEFAULT);

		// create Background
		Background background = new Background(backgroundimage);

		// set background
		view.setBackground(background);
		/*
		// Create a new WebView control
		WebView webView = new WebView();

		// Load an HTML file into the WebView
		webView.getEngine().load(Objects.requireNonNull(getClass().getResource("/view/layout.html")).toExternalForm());

		// Create a new Scene with the WebView as the root node
		Scene scene = new Scene(webView);

		// Set the Scene to the Stage and show the Stage
		stage.setScene(scene);
		stage.show();
		 */

	}
	// main method to launch the application
	public static void main(String[] args) {
		launch(args);
	}

}
