package view;

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
public class LogicTutorPane extends GridPane {
	private Button btncalculate, btnSimplify;
	private Button btnAnd, btnOr, btnImplies, btnIfOnlyIf, btnNegation;
	private ValidationClass txtFormula;

	/**
	 * A constructor method to initiate createStudentProfile view pane
	 */
	public LogicTutorPane() {

		//styling
		this.setVgap(10);
		this.setHgap(10);
		this.setPadding(new Insets(10, 10, 10, 10));
		this.setAlignment(Pos.CENTER);


		//create labels
		Label lblFormula = new Label("Add A Formula");

		lblFormula.setStyle("-fx-text-fill: white;"
				+ "-fx-font: 30px Georgia;"
				);

		//error labels
		Label lblFormulaError = new Label("  Variables can only be alphapatic \n"
				+ "and for logical operators see below");

		lblFormulaError.setStyle("-fx-text-fill: red;"
				+ "-fx-font: 25px Georgia;"
				);


		//setup text fields
		txtFormula = new ValidationClass(input -> input.matches("[a-zA-Z !&()~|=<>]*"));

		txtFormula.setPadding(new Insets(10, 10, 10, 10));

		txtFormula.setStyle("-fx-background-color: linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),"
				+ "linear-gradient(#020b02, #3a3a3a), "
				+ "linear-gradient(#b9b9b9 0%, #c2c2c2 20%, #afafaf 80%, #c8c8c8 100%),"
				+ "linear-gradient(#f5f5f5 0%, #dbdbdb 50%, #cacaca 51%, #d7d7d7 100%);"
				+ "-fx-background-insets: 0,1,4,5,6;"
				+ "-fx-background-radius: 9,8,5,4,3;"
				+ "-fx-padding: 15 30 15 30;"
				+ "-fx-font-size: 30px;"
				+ "-fx-font-weight: bold;"
				+ "-fx-text-fill: #333333;"
				+ "-fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);"
				);


		//initialize the create profile button and binds it to the validation in the validation helper class
		btncalculate = new Button("Evaluate");
		btnSimplify = new Button("Simplify");
		btnAnd = new Button("&");
		btnOr = new Button("|");
		btnImplies = new Button("=>");
		btnIfOnlyIf = new Button("<=>");
		btnNegation = new Button("~");
		
		btncalculate.setStyle(WelcomingPane.idel);
		btncalculate.setOnMouseEntered(e -> btncalculate.setStyle(WelcomingPane.hover));
		btncalculate.setOnMouseExited(e -> btncalculate.setStyle(WelcomingPane.idel));
		
		btnSimplify.setStyle(WelcomingPane.idel);
		btnSimplify.setOnMouseEntered(e -> btnSimplify.setStyle(WelcomingPane.hover));
		btnSimplify.setOnMouseExited(e -> btnSimplify.setStyle(WelcomingPane.idel));
		
		btnAnd.setStyle(WelcomingPane.idel);
		btnAnd.setOnMouseEntered(e -> btnAnd.setStyle(WelcomingPane.hover));
		btnAnd.setOnMouseExited(e -> btnAnd.setStyle(WelcomingPane.idel));
		
		btnOr.setStyle(WelcomingPane.idel);
		btnOr.setOnMouseEntered(e -> btnOr.setStyle(WelcomingPane.hover));
		btnOr.setOnMouseExited(e -> btnOr.setStyle(WelcomingPane.idel));
		
		btnImplies.setStyle(WelcomingPane.idel);
		btnImplies.setOnMouseEntered(e -> btnImplies.setStyle(WelcomingPane.hover));
		btnImplies.setOnMouseExited(e -> btnImplies.setStyle(WelcomingPane.idel));
		
		btnIfOnlyIf.setStyle(WelcomingPane.idel);
		btnIfOnlyIf.setOnMouseEntered(e -> btnIfOnlyIf.setStyle(WelcomingPane.hover));
		btnIfOnlyIf.setOnMouseExited(e -> btnIfOnlyIf.setStyle(WelcomingPane.idel));
		
		btnNegation.setStyle(WelcomingPane.idel);
		btnNegation.setOnMouseEntered(e -> btnNegation.setStyle(WelcomingPane.hover));
		btnNegation.setOnMouseExited(e -> btnNegation.setStyle(WelcomingPane.idel));

		btncalculate.setPadding(new Insets(10, 10, 10, 10));
		btnSimplify.setPadding(new Insets(10, 10, 10, 10));
		btnAnd.setPadding(new Insets(10, 10, 10, 10));
		btnOr.setPadding(new Insets(10, 10, 10, 10));
		btnImplies.setPadding(new Insets(10, 10, 10, 10));
		btnIfOnlyIf.setPadding(new Insets(10, 10, 10, 10));
		btnNegation.setPadding(new Insets(10, 10, 10, 10));

		btncalculate.setMinSize(50, 50);
		btnSimplify.setMinSize(50, 50);
		btnAnd.setMinSize(35, 35);
		btnOr.setMinSize(35, 35);
		btnImplies.setMinSize(35, 35);
		btnIfOnlyIf.setMinSize(35, 35);
		btnNegation.setMinSize(35, 35);


		btncalculate.disableProperty().bind(txtFormula.isValidProperty.not());
		btnSimplify.disableProperty().bind(txtFormula.isValidProperty.not());
		lblFormulaError.visibleProperty().bind(txtFormula.isValidProperty.not());



		//h Boxes
		HBox hb = new HBox(btnAnd, btnOr, btnImplies, btnIfOnlyIf, btnNegation);
		HBox hb1 = new HBox(btncalculate, btnSimplify);
		hb.setAlignment(Pos.BASELINE_CENTER);
		hb1.setAlignment(Pos.BASELINE_CENTER);
		hb.setPadding(new Insets(5, 5, 5, 5));
		hb1.setPadding(new Insets(5, 5, 5, 5));
		hb.setSpacing(20);
		hb1.setSpacing(20);

		//v Box
		VBox vb = new VBox(lblFormula, txtFormula, lblFormulaError, hb, hb1);
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
	 * @param handler
	 */
	public void calculateLogicHandler(EventHandler<ActionEvent> handler) {
		btncalculate.setOnAction(handler);
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
	public void simplifyLogicHandler(EventHandler<ActionEvent> handler) {
		btnSimplify.setOnAction(handler);
	}

}
