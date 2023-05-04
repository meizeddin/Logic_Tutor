package view;

import javafx.scene.control.TabPane;
import org.junit.jupiter.api.Assertions;
import org.testfx.framework.junit5.ApplicationTest;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WelcomingPaneIntegrationTest extends ApplicationTest {

    public void start(Stage primaryStage) {
        WelcomingPane welcomingPane = new WelcomingPane();

        Scene scene = new Scene(welcomingPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Test
    public void testStudyButton() {
        clickOn("#btnStudy");
        // Add assertions here to test the expected behavior
    }

    @Test
    public void testEvaluateButton() {
        clickOn("#btnEvaluate");
        // Verify that the correct tab is now displayed
        TabPane tabPane = lookup("#tabPane").query();
        Assertions.assertEquals(1, tabPane.getSelectionModel().getSelectedIndex());
    }

    @Test
    public void testManipulateButton() {
        clickOn("#btnManipulate");
        // Add assertions here to test the expected behavior
    }

    @Test
    public void testTestButton() {
        clickOn("#btnTest");
        // Add assertions here to test the expected behavior
    }
}
