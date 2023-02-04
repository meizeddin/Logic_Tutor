package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SimplificationPane extends GridPane{
	private Button btnSave, btnCalc;
	private TextArea txtFunction, txtResult;
	private Label labelFunc, labelResult;


	/**
	 * a constructor method to initiate the overview pane
	 */
	public SimplificationPane() {

		//styling
		this.setVgap(10);
		this.setHgap(5);
		this.setPadding(new Insets(10, 10, 10, 10));
		this.setPrefSize(700, 700);

		//initialize buttons
		btnSave = new Button("Save");
		btnSave.setPrefSize(300, 50);
		btnSave.setPadding(new Insets(10, 10, 10, 10));
		btnSave.setAlignment(Pos.CENTER);
		btnSave.setMinSize(300, 75);

		btnSave.setStyle(WelcomingPane.idel);
		btnSave.setOnMouseEntered(e -> btnSave.setStyle(WelcomingPane.hover));
		btnSave.setOnMouseExited(e -> btnSave.setStyle(WelcomingPane.idel));

		btnCalc = new Button("Calculate");
		btnCalc.setPrefSize(300, 50);
		btnCalc.setPadding(new Insets(10, 10, 10, 10));
		btnCalc.setAlignment(Pos.CENTER);
		btnCalc.setMinSize(300, 75);

		btnCalc.setStyle(WelcomingPane.idel);
		btnCalc.setOnMouseEntered(e -> btnCalc.setStyle(WelcomingPane.hover));
		btnCalc.setOnMouseExited(e -> btnCalc.setStyle(WelcomingPane.idel));

		//setup label
		labelFunc = new Label();
		labelFunc.setText("Function");
		labelFunc.setStyle("-fx-text-fill: white;"
				+ "-fx-font: 30px Georgia;"
				);
		//setup text area
		txtFunction = new TextArea();
		txtFunction.setEditable(false);
		//txtFunction.autosize();
		txtFunction.setPadding(new Insets(10, 10, 10, 10));
		txtFunction.setMaxSize(500, 250);
		txtFunction.setPrefSize(500, 250);
		txtFunction.setStyle("-fx-control-inner-background: lightsteelblue;"
				+ "-fx-background-color: linear-gradient(to right, darkblue, darkcyan);"
				+ "-fx-text-fill: black; -fx-font-size: 30px;"
				);
		//txtFunction

		//setup label
		labelResult = new Label();
		labelResult.setText("Result");
		labelResult.setStyle("-fx-text-fill: white;"
				+ "-fx-font: 30px Georgia;"
				);
		txtResult = new TextArea();
		txtResult.setEditable(false);
		txtResult.setMaxSize(1000, 500);
		txtResult.setPrefSize(1000, 500);
		txtResult.setPadding(new Insets(10, 10, 10, 10));
		//txtResult.setPrefSize(800, 200);
		txtResult.setStyle("-fx-control-inner-background: lightsteelblue;"
				+ "-fx-background-color: linear-gradient(to right, darkblue, darkcyan);"
				+ "-fx-text-fill: black; -fx-font-size: 30px;"
				);

		//setup HBoxs
		HBox hboxButton = new HBox();
		hboxButton.getChildren().addAll(btnSave, btnCalc);
		hboxButton.setAlignment(Pos.CENTER);
		hboxButton.setSpacing(20);

		VBox vbox1 = new VBox();
		vbox1.getChildren().addAll(labelResult, txtResult);
		vbox1.setAlignment(Pos.CENTER);
		vbox1.setSpacing(20);
		
		VBox vbox = new VBox();
		vbox.getChildren().addAll(labelFunc, txtFunction);
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(20);

		VBox vbox2 = new VBox();
		vbox2.getChildren().addAll(vbox, vbox1, hboxButton);
		vbox2.setAlignment(Pos.CENTER);
		vbox2.setSpacing(20);
		



		//add controls and labels to container
		this.add(vbox2, 1, 1);
		this.setAlignment(Pos.CENTER);

	}

	//methods to retrieve overview data
	/**
	 * @param str: accepts a string of student personal details and populate the corresponding textField in the view
	 */
	public void populateFunction(String str) {
		txtFunction.setText(str);
	}
	/**
	 * @param str: accepts a string of student selected modules and populate the corresponding textField in the view
	 */
	public void populateResult(String str) {
		txtResult.setText(str);
	}
	/**
	 * @param str: accepts a string of student reserved modules and populate the corresponding textField in the view
	 */

	/**
	 * @returns the button (Save Overview) to be accessed by the controller
	 */
	public Button getBtnSaveOverview() {
		return btnSave;
	}
	/**
	 * @returns student personal details of type TextArea
	 */
	public TextArea getTxtFunction() {
		return txtFunction;
	}
	/**
	 * @returns student selected modules of type TextArea
	 */
	public TextArea getTxtResult() {
		return txtResult;
	}
	/**
	 * @return student reserved modules of type TextArea
	 */

	/**
	 * method to attach the buttons event handlers
	 * @param handler: accepts an external handler set by the controller
	 */
	public void btnSaveHandler(EventHandler<ActionEvent> handler) {
		btnSave.setOnAction(handler);
	}
	public void btnCalcHandler(EventHandler<ActionEvent> handler) {
		btnCalc.setOnAction(handler);
	}
}
