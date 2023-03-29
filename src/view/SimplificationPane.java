package view;

import expressionTree.Expression;
import expressionTree.Parser;
import expressionTree.ShuntingYardAlgorithm;
import expressionTree.Tokenizer;
import expressionTree.rules.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.ValidationClass;

import java.util.List;
import java.util.Objects;

public class SimplificationPane extends ScrollPane {
	private final Button btnSave, btnCalc, btnAdd, btnManipulate, btnUpdateCombo;
	private final Button btnAnd, btnOr, btnImplies, btnIfOnlyIf, btnNegation;
	private final ValidationClass txtFormula;
	private final TextArea txtFunction, txtResult;
	private final ComboBox<String> comboBox;
	private final Label comboLabel;
	private final VBox comboVBox;



	/**
	 * a constructor method to initiate the overview pane
	 */
	public SimplificationPane() {

		//styling
		this.setPadding(new Insets(10, 10, 10, 10));
		this.setPrefSize(700, 700);
		this.setFitToWidth(true);

		Label lblFormula = new Label("Add A Formula");

		lblFormula.setStyle("-fx-text-fill: white;"
				+ "-fx-font: 30px Georgia;"
		);

		//error labels
		Label lblFormulaError = new Label("  Variables can only be alphabetic \n"
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
		btnAdd = new Button("Add");
		btnManipulate = new Button("Manipulate");
		btnAnd = new Button("&");
		btnOr = new Button("|");
		btnImplies = new Button("=>");
		btnIfOnlyIf = new Button("<=>");
		btnNegation = new Button("~");

		btnAdd.setStyle(WelcomingPane.idel);
		btnAdd.setOnMouseEntered(e -> btnAdd.setStyle(WelcomingPane.hover));
		btnAdd.setOnMouseExited(e -> btnAdd.setStyle(WelcomingPane.idel));

		btnManipulate.setStyle(WelcomingPane.idel);
		btnManipulate.setOnMouseEntered(e -> btnManipulate.setStyle(WelcomingPane.hover));
		btnManipulate.setOnMouseExited(e -> btnManipulate.setStyle(WelcomingPane.idel));

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

		btnAdd.setPadding(new Insets(10, 10, 10, 10));
		btnManipulate.setPadding(new Insets(10, 10, 10, 10));
		btnAnd.setPadding(new Insets(10, 10, 10, 10));
		btnOr.setPadding(new Insets(10, 10, 10, 10));
		btnImplies.setPadding(new Insets(10, 10, 10, 10));
		btnIfOnlyIf.setPadding(new Insets(10, 10, 10, 10));
		btnNegation.setPadding(new Insets(10, 10, 10, 10));

		btnAdd.setMinSize(50, 50);
		btnManipulate.setMinSize(50, 50);
		btnAnd.setMinSize(35, 35);
		btnOr.setMinSize(35, 35);
		btnImplies.setMinSize(35, 35);
		btnIfOnlyIf.setMinSize(35, 35);
		btnNegation.setMinSize(35, 35);


		btnAdd.disableProperty().bind(txtFormula.isValidProperty.not());
		lblFormulaError.visibleProperty().bind(txtFormula.isValidProperty.not());



		//h Boxes
		HBox hb = new HBox(btnAnd, btnOr, btnImplies, btnIfOnlyIf, btnNegation);
		HBox hb1 = new HBox(btnAdd);
		hb.setAlignment(Pos.BASELINE_CENTER);
		hb1.setAlignment(Pos.BASELINE_CENTER);
		hb.setPadding(new Insets(5, 5, 5, 5));
		hb1.setPadding(new Insets(5, 5, 5, 5));
		hb.setSpacing(20);
		hb1.setSpacing(20);

		//v Box
		VBox vb = new VBox(lblFormula, txtFormula, lblFormulaError, hb, hb1);
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
		Label labelFunc = new Label();
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
		btnUpdateCombo = new Button("Update");
		btnUpdateCombo.setPadding(new Insets(10, 10, 10, 10));
		btnUpdateCombo.setAlignment(Pos.CENTER);
		btnUpdateCombo.setMinSize(50, 50);
		btnUpdateCombo.setStyle(WelcomingPane.idel);
		btnUpdateCombo.setOnMouseEntered(e -> btnUpdateCombo.setStyle(WelcomingPane.hover));
		btnUpdateCombo.setOnMouseExited(e -> btnUpdateCombo.setStyle(WelcomingPane.idel));
		comboBox = new ComboBox<String>();
		comboBox.getItems().addAll("Select an option..");
		comboBox.setOnAction((event)-> updateModel());
		comboLabel = new Label("Select a rule:");
		comboVBox = new VBox(10, comboLabel, comboBox);
		comboVBox.setPadding(new Insets(10));
		comboBox.setValue(comboBox.getItems().get(0));

		//setup label
		Label labelResult = new Label();
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

		HBox hboxManipulate = new HBox();
		hboxManipulate.getChildren().addAll(btnUpdateCombo, comboVBox, btnManipulate);
		hboxManipulate.setAlignment(Pos.CENTER);
		hboxManipulate.setSpacing(20);

		VBox vbox1 = new VBox();
		vbox1.getChildren().addAll(labelResult, txtResult);
		vbox1.setAlignment(Pos.CENTER);
		vbox1.setSpacing(20);
		
		VBox vbox = new VBox();
		vbox.getChildren().addAll(labelFunc, txtFunction, hboxManipulate);
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(20);

		VBox vbox2 = new VBox();
		vbox2.getChildren().addAll(vb, vbox, vbox1, hboxButton);
		vbox2.setAlignment(Pos.CENTER);
		vbox2.setSpacing(20);
		



		//add controls and labels to container
		this.setContent(vbox2);


		// Set the background color of the viewport
		this.setStyle("-fx-background-color: grey;");
		this.getContent().setStyle("-fx-background-color: grey;");
		//this.setAlignment(Pos.CENTER);

	}

	//methods to retrieve overview data

	public String getFormula(){
		return txtFormula.getText();
	}
	public String getFunction(){
		return txtFunction.getText();
	}
	public String getSelectedFormula(){
		return txtFunction.getSelectedText();
	}
	public IndexRange getIndexRange(){
		return txtFunction.getSelection();
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

	public void updateComboBox(List<String> itemList, Expression expression) {
		comboBox.getItems().clear();
		comboBox.getItems().add("Select an option..");
		comboBox.setValue(comboBox.getItems().get(0));
		for (String item : itemList) {
			if (compliesWithRule(item, expression)) {
				comboBox.getItems().add(item);
			}
		}
	}

	private boolean compliesWithRule(String item, Expression expr) {
		// Write your propositional logic rule here
		boolean result = false;
		switch (item){
			case "Idempotence Rule" -> {
				IdempotenceVisitor visitor = new IdempotenceVisitor();
				result = visitor.canApply(expr);
			}
			case "De Morgan's Rule" -> {
				DeMorganVisitor visitor = new DeMorganVisitor();
				result = visitor.canApply(expr);
			}
			case "Absorption Rule" -> {
				AbsorptionVisitor visitor = new AbsorptionVisitor();
				result = visitor.canApply(expr);
			}
			case "Associative Rule" -> {
				AssociativeVisitor visitor = new AssociativeVisitor();
				result = visitor.canApply(expr);
			}
			case "Commutative Rule" -> {
				CommutativeVisitor visitor = new CommutativeVisitor();
				result = visitor.canApply(expr);
			}
			case "Distributive Rule" -> {
				DistributiveVisitor visitor = new DistributiveVisitor();
				result = visitor.canApply(expr);
			}
			case "Double Negation Rule" -> {
				DoubleNegationVisitor visitor = new DoubleNegationVisitor();
				result = visitor.canApply(expr);
			}
			case "Switcheroo Rule" -> {
				SwitcherooVisitor visitor = new SwitcherooVisitor();
				result = visitor.canApply(expr);
			}
			case "Identity Rule" -> {
				IdentityVisitor visitor = new IdentityVisitor();
				result = visitor.canApply(expr);
			}
		}
		return result;
	}

	private void updateModel() {
		// Call the controller to update the model
	}

	public String getSelectedRule(){
		return comboBox.getValue();
	}

	public void clearFormula(){
		txtFormula.clear();
	}

	public void setFunction(String function, int start, int end){
		txtFunction.replaceText(start, end, function);
	}

	public void addButtonLogicHandler(EventHandler<ActionEvent> handler) {
		btnAdd.setOnAction(handler);
	}
	public void manipulateButtonLogicHandler(EventHandler<ActionEvent> handler) {
		btnManipulate.setOnAction(handler);
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
	public void calcLogicHandler(EventHandler<ActionEvent> handler) {
		btnCalc.setOnAction(handler);
	}
	public void updateBtnLogicHandler(EventHandler<ActionEvent> handler) {
		btnUpdateCombo.setOnAction(handler);
	}

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
		txtResult.appendText(str);
	}

	public void clearResult(){
		txtResult.clear();
	}

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
