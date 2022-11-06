package main;

import controller.LogicTutorController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.LogicalFormula;
import view.LogicTutorRootPane;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import java.io.*;

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
		//sets minimum width and height for the stage window
		stage.setMinWidth(1100); 
		stage.setMinHeight(750);
		stage.setHeight(800);
		stage.setWidth(1100);
		stage.setTitle("Logic Calculator");
		stage.setScene(new Scene(view));
		stage.show();


		// create an input stream
		FileInputStream input = new FileInputStream("C:\\Users\\meize\\De Montfort University\\David Smallwood - Mohammad Eizeddin\\Student's Cartridge\\Development\\Code\\src\\Images\\1200152.png");

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

	}
	// main method to launch the application
	public static void main(String[] args) {
		launch(args);
	}

}
