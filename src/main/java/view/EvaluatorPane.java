package view;
import javafx.geometry.Bounds;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import model.ValidationClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
/**
 * This class is the first view of the application where a student profile is created
 * @author meize
 *
 */
public class EvaluatorPane extends GridPane {
	private final Button btnEvaluate, btnManipulate;
	private final Button btnAnd, btnOr, btnImplies, btnIfOnlyIf, btnNegation;
	private final ValidationClass txtFormula;

	/**
	 * A constructor method to initiate createStudentProfile view pane
	 */
	public EvaluatorPane() {

		//styling
		this.setVgap(10);
		this.setHgap(10);
		this.setPadding(new Insets(10, 10, 10, 10));
		this.setAlignment(Pos.CENTER);


		//create labels
		Label lblFormula = new Label("Add An Expression");

		lblFormula.setStyle("-fx-text-fill: white;"
				+ "-fx-font: 30px Harrington;"
				);

		//error labels
		Label lblFormulaError = new Label("Variables can only be alphabetic, all brackets must be closed,\n"
				+ "\t\tand only the below operators are allowed");

		lblFormulaError.setStyle("-fx-font: 25px Harrington;"
				+ "-fx-padding: 8px;"
				+ "-fx-background-color: #ff9999;"
				+ "-fx-text-fill: #800000;"
				+ "-fx-border-color: #800000;"
				+ "-fx-border-width: 2px;"
				+ "-fx-alignment: center;");

		//setup text fields
		txtFormula = new ValidationClass(input -> {
			// Match only alphabets, spaces, and a few special characters
			if (!input.matches("[a-zA-Z !&()~|=<>]*")) {
				return false;
			}
			// Check if brackets are balanced
			int count = 0;
			for (char c : input.toCharArray()) {
				if (c == '(') {
					count++;
				} else if (c == ')') {
					count--;
				}
				if (count < 0) {
					return false;
				}
			}
			return count == 0;
		});

		txtFormula.setStyle("-fx-background-color: linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),"
				+ "linear-gradient(#020b02, #3a3a3a), "
				+ "linear-gradient(#b9b9b9 0%, #c2c2c2 20%, #afafaf 80%, #c8c8c8 100%),"
				+ "linear-gradient(#f5f5f5 0%, #dbdbdb 50%, #cacaca 51%, #d7d7d7 100%);"
				+ "-fx-background-insets: 0,1,4,5,6;"
				+ "-fx-background-radius: 70,69,65,64,63;"
				+ "-fx-padding: 15 30 15 30;"
				+ "-fx-font-size: 30px;"
				+ "-fx-font-weight: bold;"
				+ "-fx-text-fill: #333333;"
				+ "-fx-effect: dropshadow(three-pass-box, rgba(255,255,255,0.2), 1, 0.0, 0, 1);"
				);
		txtFormula.setPromptText("Enter a logical expression");

		// Create a round button with a tooltip
		Button popupButton = new Button("Click me!");
		popupButton.setStyle("-fx-background-color: #37435c; "
				+ "-fx-text-fill: white; "
				+ "-fx-background-radius: 30;");
		popupButton.setOnMouseEntered(e -> {
			popupButton.setEffect(new DropShadow(10, Color.WHITE));
		});

		popupButton.setOnMouseExited(e -> {
			popupButton.setEffect(null);
		});
		Tooltip tooltip = new Tooltip("Click me to display a message");
		popupButton.setTooltip(tooltip);

		// Create a popup message
		Tooltip popupMessage = new Tooltip("""
				Only alphabetic characters and the below operators are allowed
							Make sure you close all the brackets
				(T) and (F) are considered true and false rather than variables""");
		popupMessage.setStyle("-fx-font-size: 18px;");
		popupMessage.setAutoHide(true);

		// Show the popup message when the button is clicked
		popupButton.setOnAction(e -> {
			Bounds bounds = popupButton.localToScreen(popupButton.getBoundsInLocal());
			popupMessage.show(popupButton.getScene().getWindow(), bounds.getMinX(), bounds.getMaxY());
		});

		//initialize the create profile button and binds it to the validation in the validation helper class
		btnEvaluate = new Button("Evaluate");
		btnManipulate = new Button("Manipulate");
		btnAnd = new Button("&");
		btnOr = new Button("|");
		btnImplies = new Button("=>");
		btnIfOnlyIf = new Button("<=>");
		btnNegation = new Button("~");

		btnEvaluate.setStyle(WelcomingPane.idle);
		btnEvaluate.setOnMouseEntered(e -> btnEvaluate.setStyle(WelcomingPane.hover));
		btnEvaluate.setOnMouseExited(e -> btnEvaluate.setStyle(WelcomingPane.idle));

		btnManipulate.setStyle(WelcomingPane.idle);
		btnManipulate.setOnMouseEntered(e -> btnManipulate.setStyle(WelcomingPane.hover));
		btnManipulate.setOnMouseExited(e -> btnManipulate.setStyle(WelcomingPane.idle));
		
		btnAnd.setStyle(WelcomingPane.idle);
		btnAnd.setOnMouseEntered(e -> btnAnd.setStyle(WelcomingPane.hover));
		btnAnd.setOnMouseExited(e -> btnAnd.setStyle(WelcomingPane.idle));
		
		btnOr.setStyle(WelcomingPane.idle);
		btnOr.setOnMouseEntered(e -> btnOr.setStyle(WelcomingPane.hover));
		btnOr.setOnMouseExited(e -> btnOr.setStyle(WelcomingPane.idle));
		
		btnImplies.setStyle(WelcomingPane.idle);
		btnImplies.setOnMouseEntered(e -> btnImplies.setStyle(WelcomingPane.hover));
		btnImplies.setOnMouseExited(e -> btnImplies.setStyle(WelcomingPane.idle));
		
		btnIfOnlyIf.setStyle(WelcomingPane.idle);
		btnIfOnlyIf.setOnMouseEntered(e -> btnIfOnlyIf.setStyle(WelcomingPane.hover));
		btnIfOnlyIf.setOnMouseExited(e -> btnIfOnlyIf.setStyle(WelcomingPane.idle));
		
		btnNegation.setStyle(WelcomingPane.idle);
		btnNegation.setOnMouseEntered(e -> btnNegation.setStyle(WelcomingPane.hover));
		btnNegation.setOnMouseExited(e -> btnNegation.setStyle(WelcomingPane.idle));

		btnEvaluate.setPadding(new Insets(10, 10, 10, 10));
		btnManipulate.setPadding(new Insets(10, 10, 10, 10));
		btnAnd.setPadding(new Insets(10, 10, 10, 10));
		btnOr.setPadding(new Insets(10, 10, 10, 10));
		btnImplies.setPadding(new Insets(10, 10, 10, 10));
		btnIfOnlyIf.setPadding(new Insets(10, 10, 10, 10));
		btnNegation.setPadding(new Insets(10, 10, 10, 10));

		btnEvaluate.setMinSize(50, 50);
		btnManipulate.setMinSize(50, 50);
		btnAnd.setMinSize(35, 35);
		btnOr.setMinSize(35, 35);
		btnImplies.setMinSize(35, 35);
		btnIfOnlyIf.setMinSize(35, 35);
		btnNegation.setMinSize(35, 35);


		btnEvaluate.disableProperty().bind(txtFormula.isValidProperty.not());
		btnManipulate.disableProperty().bind(txtFormula.isValidProperty.not());
		lblFormulaError.visibleProperty().bind(txtFormula.isValidProperty.not());

		//h Boxes
		HBox hb = new HBox(btnAnd, btnOr, btnImplies, btnIfOnlyIf, btnNegation);
		HBox hb1 = new HBox(btnEvaluate, btnManipulate);
		HBox hb2 = new HBox(lblFormula, popupButton);
		hb.setAlignment(Pos.BASELINE_CENTER);
		hb1.setAlignment(Pos.BASELINE_CENTER);
		hb2.setAlignment(Pos.BASELINE_CENTER);
		hb.setSpacing(20);
		hb1.setSpacing(20);
		hb2.setSpacing(20);

		//v Box
		VBox vb = new VBox(hb2, txtFormula, lblFormulaError, hb, hb1);
		vb.setAlignment(Pos.CENTER);
		vb.setSpacing(20);
		this.add(vb, 1, 1);

	}

	/**
	 * @returns
	 */
	public String getFormula(){
		return txtFormula.getText();
	}

	public void setFormula(String str) {
		int index = txtFormula.getText().length();
		txtFormula.insertText(index, str);
	}
	public void removeSpaces(String str) {
		String purified = str.replaceAll(" ", "");
		txtFormula.clear();
		txtFormula.setText(purified.toUpperCase());
	}
	/**
	 * method to attach the create student profile button event handler
	 */
	public void evaluateLogicHandler(EventHandler<ActionEvent> handler) {
		btnEvaluate.setOnAction(handler);
	}
	public void conjunctionLogicHandler(EventHandler<ActionEvent> handler) {
		btnAnd.setOnAction(handler);
	}
	public void disjunctionLogicHandler(EventHandler<ActionEvent> handler) {
		btnOr.setOnAction(handler);
	}
	public void implicationLogicHandler(EventHandler<ActionEvent> handler) {
		btnImplies.setOnAction(handler);
	}
	public void equivalenceLogicHandler(EventHandler<ActionEvent> handler) {
		btnIfOnlyIf.setOnAction(handler);
	}
	public void negationLogicHandler(EventHandler<ActionEvent> handler) {
		btnNegation.setOnAction(handler);
	}
	public void manipulateLogicHandler(EventHandler<ActionEvent> handler) {
		btnManipulate.setOnAction(handler);
	}

}
