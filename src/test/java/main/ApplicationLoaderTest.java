package main;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javafx.stage.Stage;
import javafx.application.Platform;


/**
 * The ApplicationLoaderTest class is responsible for testing the ApplicationLoader class.
 * It checks that the ApplicationLoader class runs correctly and the stage is showing.
 */
public class ApplicationLoaderTest {

    private Stage stage;


    /**
     * The testApplicationLoader() method tests that the ApplicationLoader class runs correctly.
     * It starts the JavaFX application and checks that the stage is showing.
     */
    @Test
    public void testApplicationLoader() {
        // Start the JavaFX application
        //this test needs to run on JavaFX Thread,
        //that's why we use the Platform.startup() and
        //Platform.runLater() methods.
        Platform.startup(() -> {
            stage = new Stage();
            try {
                new ApplicationLoader().start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        // Wait for the application to start
        Platform.runLater(() -> {
            // Assert that the stage is showing
            assertTrue(stage.isShowing());
            // Close the stage
            stage.close();
        });
    }
}
