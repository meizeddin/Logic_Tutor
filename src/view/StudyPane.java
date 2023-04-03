package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class StudyPane extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a WebView
        WebView webView = new WebView();

        // Load a URL in the WebView
        webView.getEngine().load("https://www.google.com");

        // Put the WebView in a StackPane
        StackPane root = new StackPane(webView);

        // Create a Scene with the StackPane as the root
        Scene scene = new Scene(root, 800, 600);

        // Set the Scene on the primary stage
        primaryStage.setScene(scene);

        // Show the primary stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
