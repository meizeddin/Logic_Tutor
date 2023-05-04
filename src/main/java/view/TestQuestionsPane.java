package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;


public class TestQuestionsPane extends GridPane {
    public TestQuestionsPane(String test, List<String> questions, List<List<String>> answers, List<String> correctAnswers) {
        Stage primaryStage = new Stage();
        primaryStage.setTitle(test);

        VBox questionContainer = new VBox();
        for (int i = 0; i < questions.size(); i++) {
            VBox questionBox = new VBox();
            Label labelQuestion = new Label("Question " + (i+1) + ": ");
            labelQuestion.setStyle("-fx-text-fill: white;"
                    + "-fx-font: 30px Georgia;");
            TextArea questionText = new TextArea(questions.get(i));
            questionText.setEditable(false);
            questionText.setPadding(new Insets(10, 10, 10, 10));
            questionText.setWrapText(true);
            questionText.setPrefSize(500, 250);
            questionText.setStyle("-fx-control-inner-background: #8897b3;"
                    + "-fx-background-color: linear-gradient(to right, #212838, #c2e3fc);"
                    + "-fx-text-fill: black; -fx-font-size: 30px;"
            );

            Button button = new Button("Submit");
            button.setPadding(new Insets(10, 10, 10, 10));
            button.setAlignment(Pos.CENTER);
            button.setMinSize(50, 50);
            button.setStyle(WelcomingPane.idle);
            button.setOnMouseEntered(e -> button.setStyle(WelcomingPane.hover));
            button.setOnMouseExited(e -> button.setStyle(WelcomingPane.idle));
            Label labelResponse = new Label();
            labelResponse.setStyle("-fx-text-fill: white;"
                    + "-fx-font: 20px Georgia;");

            RadioButton radio1, radio2, radio3, radio4;
            radio1 = new RadioButton(answers.get(i).get(0));
            radio1.setStyle("-fx-text-fill: white;"
                    + "-fx-font: 24px Georgia;");
            radio2 = new RadioButton(answers.get(i).get(1));
            radio2.setStyle("-fx-text-fill: white;"
                    + "-fx-font: 24px Georgia;");
            radio3 = new RadioButton(answers.get(i).get(2));
            radio3.setStyle("-fx-text-fill: white;"
                    + "-fx-font: 24px Georgia;");
            radio4 = new RadioButton(answers.get(i).get(3));
            radio4.setStyle("-fx-text-fill: white;"
                    + "-fx-font: 24px Georgia;");
            VBox radioButtons = new VBox();
            radioButtons.setSpacing(10);
            radioButtons.setPadding(new Insets(5, 5, 5, 5));
            radioButtons.getChildren().addAll(radio1, radio2, radio3, radio4);

            ToggleGroup question1 = new ToggleGroup();

            radio1.setToggleGroup(question1);
            radio2.setToggleGroup(question1);
            radio3.setToggleGroup(question1);
            radio4.setToggleGroup(question1);

            button.setDisable(true);

            radio1.setOnAction(e -> button.setDisable(false));
            radio2.setOnAction(e -> button.setDisable(false));
            radio3.setOnAction(e -> button.setDisable(false));
            radio4.setOnAction(e -> button.setDisable(false));

            String correctAnswer = correctAnswers.get(i);

            button.setOnAction(e ->
                    {
                        RadioButton selectedRadioButton = (RadioButton) question1.getSelectedToggle();
                        String selectedText = selectedRadioButton.getText();
                        if (selectedText.equals(correctAnswer)) {
                            labelResponse.setText("Correct Answer");
                            button.setDisable(true);
                        } else {
                            labelResponse.setText("Wrong answer \n The correct answer is: " + correctAnswer);
                            button.setDisable(true);
                        }
                    }
            );



            questionBox.getChildren().addAll(labelQuestion, questionText, radioButtons, button, labelResponse);
            questionBox.setSpacing(10);

            questionContainer.getChildren().add(questionBox);
        }

        questionContainer.setPadding(new Insets(10, 10, 10, 10));
        questionContainer.setSpacing(40);
        // Create a ScrollPane and set the VBox questionContainer as its content
        ScrollPane scrollPane = new ScrollPane(questionContainer);
        scrollPane.setFitToWidth(true); // Expand the ScrollPane to fill the width of its parent

        // create an input stream
        InputStream input;
        try {
            input = ClassLoader.getSystemResourceAsStream("media/black.png");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // create an image
        Image image = new Image(Objects.requireNonNull(input));

        // create a background image
        BackgroundImage backgroundimage = new BackgroundImage(image,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT);

        // create Background
        Background background = new Background(backgroundimage);
        questionContainer.setBackground(background);

        Scene scene1 = new Scene(scrollPane, 1000, 500);
        primaryStage.setScene(scene1);
        primaryStage.show();
    }
}


