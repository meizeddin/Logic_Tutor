package controller;
import calcParser.EvaluationException;
import calcParser.ParserException;
import expressionTree.*;
import expressionTree.rules.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.TruthTableHelperFun;
import view.*;
import model.LogicalFormula;
import simplifier.SimplifyLogicalStrings;
import java.util.List;
import java.util.Objects;

/**
 * @author meize
 * This is a simple Controller that Accepts input and converts it to commands for the model or the view.
 */
public class LogicTutorController {

	//fields to be used throughout class
	private final LogicTutorRootPane view;
	private final LogicTutorPane ltp;
	private final ResultPane rp;
	private final LogicTutorMenuBar ltmb;
	private final LogicalFormula model;
	private final WelcomingPane wp;
	private final SimplificationPane sp;
	private final StudyPane stp;

	/**
	 * a constructor method to initiate the view and model
	 * @param view: Accepts a view where the user interaction is happening
	 * @param model: Accepts a model to either store the data inputed on the view or-
	 * 				-displays the data exited in the model on the view
	 */
	public LogicTutorController(LogicTutorRootPane view, LogicalFormula model) {
		//initialize view and model fields
		this.view = view;
		this.model = model;

		//initialize view sub-container fields
		ltp = view.getLogicTutorPane();
		ltmb = view.getLogicTutorMenuBar();
		rp = view.getResultPane();
		wp = view.getWelcomingPane();
		sp = view.getSimplificationPane();
		stp = view.getStudyPane();


		//attach event handlers to view using private helper method
		this.attachEventHandlers();
	}

	/**
	 * helper method - used to attach event handlers
	 */
	private void attachEventHandlers() {
		//
		wp.studyHandler(new moveToStudyPaneHandler());
		wp.calculatorHandler(new moveToCalcPaneHandler());
		wp.testHandler(new moveToTestPaneHandler());

		//attach an event handler to create student profile pane
		ltp.calculateLogicHandler(new evalLogicTutorPaneHandler());
		ltp.simplifyLogicHandler(new simplificationBtnHandler());
		ltp.conjunctionLogicHandler(new conjunctionBtnHandler());
		ltp.disjunctionLogicHandler(new disjunctionBtnHandler());
		ltp.negationLogicHandler(new negationBtnHandler());
		ltp.implicationLogicHandler(new implicationBtnHandler());
		ltp.equivalenceLogicHandler(new equivalenceBtnHandler());

		//result

		//simplification
		sp.btnSaveHandler(new saveSimplificationBtnHandler());
		sp.btnCalcHandler(new calcSimplificationBtnHandler());
		sp.addButtonLogicHandler(new addSimplificationPaneHandler());
		sp.manipulateButtonLogicHandler(new manipulateSimplificationPaneHandler());
		sp.conjunctionLogicHandler(new conjunctionSimplificationBtnHandler());
		sp.disjunctionLogicHandler(new disjunctionSimplificationBtnHandler());
		sp.negationLogicHandler(new negationSimplificationBtnHandler());
		sp.implicationLogicHandler(new implicationSimplificationBtnHandler());
		sp.equivalenceLogicHandler(new equivalenceSimplificationBtnHandler());
		sp.updateBtnLogicHandler(new updateComboSimplificationBtnHandler());

		//attach an event handler to the menu bar that closes the application
		ltmb.addExitHandler(e -> System.exit(0));

		//attach an event handler to the about menu bar of the application
		ltmb.addAboutHandler(e -> ltmb.aboutInfo());

	}
	//event handler private class, which can be used for creating a profile
	private class evalLogicTutorPaneHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			//retrieves data from the view
			try {
				ltp.removeSpaces(ltp.getFormula());
				model.setFormula(ltp.getFormula());
			} catch (NullPointerException ex) {
				ex.printStackTrace();
			}
			//check input not empty
			if (model.getFormula().equals("")) {
				//output error
				alertDialogBuilder("To calculate, you need to enter a formula");
			}else {
				try
				{
					Parser.list.clear();
					String expression = model.getFormula();
					List<String> tokens = Tokenizer.tokenize(expression);
					List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
					Expression expr = Parser.Evaluator(Objects.requireNonNull(shunting));
					List<Value> boolTbl = TruthTableHelperFun.booleanTable(Parser.list);
					String truthTbl = TruthTableHelperFun.truthTable(Parser.list);
					String resultTbl = TruthTableHelperFun.resultTable(Parser.list, boolTbl, expr, expression);


					model.setTruthTable(truthTbl);
					model.setResult(resultTbl);

					rp.populateFunction(model.getFormula());
					rp.populateResult(model.getResult());
					rp.populateTruthTable(model.getTruthTable());
				}
				catch (ParserException Pex)
				{
					Pex.printStackTrace();
				}
				catch (EvaluationException ex)
				{
					ex.printStackTrace();
				}
				view.changeTab(2);
			}
		}
	}

	private class simplificationBtnHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			//retrieves data from the view
			try {
				ltp.removeSpaces(ltp.getFormula());
				model.setFormula(ltp.getFormula());
			} catch (NullPointerException ex) {
				ex.printStackTrace();
			}
			//check input not empty
			if (model.getFormula().equals("")) {
				//output error
				alertDialogBuilder("To calculate, you need to enter a formula");
			}else {

				sp.populateFunction(model.getFormula());
				sp.populateResult(SimplifyLogicalStrings.simplify(model.getFormula()));
				model.setFormula(SimplifyLogicalStrings.simplify(model.getFormula()));
				view.changeTab(3);
			}
		}
	}

	private class conjunctionBtnHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			ltp.setFormula("&");
		}
	}

	private class disjunctionBtnHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			ltp.setFormula("|");
		}
	}
	private class negationBtnHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			ltp.setFormula("~");
		}
	}
	private class implicationBtnHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			ltp.setFormula("=>");
		}
	}
	private class equivalenceBtnHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			ltp.setFormula("<=>");
		}
	}

	private class moveToStudyPaneHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			view.changeTab(4);
		}
	}
	private class moveToCalcPaneHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			view.changeTab(1);
		}
	}
	private class moveToTestPaneHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			//view.changeTab(3);
		}
	}

	private class saveSimplificationBtnHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			// TODO Auto-generated method stub

		}
	}

	private class calcSimplificationBtnHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			try {
				model.setFormula(sp.getFormula());
			} catch (NullPointerException ex) {
				ex.printStackTrace();
			}
			//check input not empty
			if (model.getFormula().equals("")) {
				//output error
				alertDialogBuilder("To calculate, you need to enter a formula");
			}else {
				try
				{
					Parser.list.clear();
					String expression = model.getFormula();
					List<String> tokens = Tokenizer.tokenize(expression);
					List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
					Expression expr = Parser.Evaluator(Objects.requireNonNull(shunting));
					List<Value> boolTbl = TruthTableHelperFun.booleanTable(Parser.list);
					String truthTbl = TruthTableHelperFun.truthTable(Parser.list);
					String resultTbl = TruthTableHelperFun.resultTable(Parser.list, boolTbl, expr, expression);


					model.setTruthTable(truthTbl);
					model.setResult(resultTbl);

					rp.populateFunction(model.getFormula());
					rp.populateResult(model.getResult());
					rp.populateTruthTable(model.getTruthTable());
				}
				catch (ParserException Pex)
				{
					Pex.printStackTrace();
				}
				catch (EvaluationException ex)
				{
					ex.printStackTrace();
				}
				view.changeTab(2);
			}
		}
	}
	private class conjunctionSimplificationBtnHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			sp.setFormula("&");
		}
	}

	private class disjunctionSimplificationBtnHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			sp.setFormula("|");
		}
	}
	private class negationSimplificationBtnHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			sp.setFormula("~");
		}
	}
	private class implicationSimplificationBtnHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			sp.setFormula("=>");
		}
	}
	private class equivalenceSimplificationBtnHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			sp.setFormula("<=>");
		}
	}

	private class addSimplificationPaneHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			sp.clearResult();
			//retrieves data from the view
			try {
				sp.removeSpaces(sp.getFormula());
				model.setFormula(sp.getFormula());
			} catch (NullPointerException ex) {
				ex.printStackTrace();
			}
			//check input not empty
			if (model.getFormula().equals("")) {
				//output error
				alertDialogBuilder("You need to enter a formula");
			}else {
				sp.populateFunction(model.getFormula());
				sp.populateResult(model.getFormula());
				model.setFormula(model.getFormula());
			}
		}
	}

	private class updateComboSimplificationBtnHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			model.setSelectedFormula(sp.getSelectedFormula());
			if (!model.getSelectedFormula().isEmpty()) {
				List<String> itemList = model.getRulesList();
				String expression = model.getSelectedFormula();
				Parser.list.clear();
				List<String> tokens = Tokenizer.tokenize(expression);
				List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
				Expression expr = Parser.Evaluator(Objects.requireNonNull(shunting));
				sp.updateComboBox(itemList, expr);
			} else{
				alertDialogBuilder("Highlight an expression");
			}
		}
	}

	private class manipulateSimplificationPaneHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			//retrieves data from the view
			try {
				model.setSelectedFormula(sp.getSelectedFormula());
			} catch (NullPointerException ex) {
				ex.printStackTrace();
			}
			//check input not empty
			if (model.getSelectedFormula().equals("")) {
				//output error
				alertDialogBuilder("Add a formula or highlight an expression");
			}else {
				Parser.list.clear();
				String expression = model.getSelectedFormula();
				List<String> tokens = Tokenizer.tokenize(expression);
				List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
				Expression expr = Parser.Evaluator(Objects.requireNonNull(shunting));
				Expression result = null;
				model.setResult("");
				switch (sp.getSelectedRule()) {
					case "Idempotence Rule" -> {
						IdempotenceVisitor visitor = new IdempotenceVisitor();
						result = expr.accept(visitor);
						model.updateResult("Using Idempotence Law");
					}
					case "De Morgan's Rule" -> {
						DeMorganVisitor visitor = new DeMorganVisitor();
						result = expr.accept(visitor);
						model.updateResult("Using De Morgan's Law");
					}
					case "Absorption Rule" -> {
						AbsorptionVisitor visitor = new AbsorptionVisitor();
						result = expr.accept(visitor);
						model.updateResult("Using Absorption Law");
					}
					case "Associative Rule" -> {
						AssociativeVisitor visitor = new AssociativeVisitor();
						result = expr.accept(visitor);
						model.updateResult("Using Associative Law");
					}
					case "Commutative Rule" -> {
						CommutativeVisitor visitor = new CommutativeVisitor();
						result = expr.accept(visitor);
						model.updateResult("Using Commutative Law");
					}
					case "Distributive Rule" -> {
						DistributiveVisitor visitor = new DistributiveVisitor();
						result = expr.accept(visitor);
						model.updateResult("Using Distributive Law");
					}
					case "Double Negation Rule" -> {
						DoubleNegationVisitor visitor = new DoubleNegationVisitor();
						result = expr.accept(visitor);
						model.updateResult("Using Double Negation Law");
					}
					case "Switcheroo Rule" -> {
						SwitcherooVisitor visitor = new SwitcherooVisitor();
						result = expr.accept(visitor);
						model.updateResult("Using Switcheroo Law");
					}
					case "Identity Rule" -> {
						IdentityVisitor visitor = new IdentityVisitor();
						result = expr.accept(visitor);
						model.updateResult("Using Identity Law");
					}
					case "Select an option.." -> {
						alertDialogBuilder("Select a rule");
					}
				}
				sp.setFunction(result.toString(), sp.getIndexRange().getStart(), sp.getIndexRange().getEnd());
				model.updateResult(sp.getFunction());
				sp.clearFormula();
				sp.populateResult(model.getResult());
				sp.setFormula(sp.getFunction());
				model.setResult("");
			}
		}
	}

	/**
	 * helper method - alerts for validation
	 */
	private void alertDialogBuilder(String str) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error Dialog");
		alert.setHeaderText(null);
		alert.setContentText(str);
		alert.showAndWait();
	}
}
