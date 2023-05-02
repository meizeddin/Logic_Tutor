package controller;
import expressionTree.*;
import expressionTree.rules.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import model.DataBase;
import model.TruthTableHelperFun;
import view.*;
import model.LogicalFormula;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author meize
 * This is a simple Controller that Accepts input and converts it to commands for the model or the view.
 */
public class LogicTutorController {

	//fields to be used throughout class
	private final LogicTutorRootPane view;
	private final EvaluatorPane ep;
	private final ResultPane rp;
	private final LogicTutorMenuBar ltmb;
	private final LogicalFormula model;
	private final WelcomingPane wp;
	private final ManipulationPane mp;
	private final TestPane testp;
	private final DataBase db;

	/**
	 * a constructor method to initiate the view and model
	 * @param view Accepts a view where the user interaction is happening
	 * @param model Accepts a model to either store the data inputted on the view or-
	 * 				-displays the data exited in the model on the view
	 */
	public LogicTutorController(LogicTutorRootPane view, LogicalFormula model) {
		//initialize view and model fields
		this.view = view;
		this.model = model;

		//initialize view sub-container fields
		ep = view.getLogicTutorPane();
		ltmb = view.getLogicTutorMenuBar();
		rp = view.getResultPane();
		wp = view.getWelcomingPane();
		mp = view.getManipulationPane();
		testp = view.getTestPane();


		//attach event handlers to view using private helper method
		this.attachEventHandlers();

		//starting the database and populating all required fields from the database
		// create the tree view
		db = new DataBase();

		TreeItem<String> rootItem = new TreeItem<>("Lessons");

		// retrieve the lessons from the database
		ObservableList<String> lessons = db.retrieveLessonsFromDatabase();

		for (String lesson : lessons) {
			TreeItem<String> lessonItem = new TreeItem<>(lesson);

			// retrieve the tests for this lesson from the database
			ObservableList<String> tests = db.retrieveTestsFromDatabaseForLesson(lesson);

			for (String test : tests) {
				TreeItem<String> testItem = new TreeItem<>(test);
				lessonItem.getChildren().add(testItem);
			}
			testp.populateTreeView(rootItem, lessonItem);
		}
	}

	/**
	 * helper method - used to attach event handlers
	 */
	private void attachEventHandlers() {
		//WelcomePane Handlers
		wp.studyHandler(new moveToStudyPaneHandler());
		wp.evaluatorHandler(new moveToEvaluatePaneHandler());
		wp.manipulatorHandler(new moveToManipulatePaneHandler());
		wp.testHandler(new moveToTestPaneHandler());

		//attach an event handler to create student profile pane
		ep.evaluateLogicHandler(new evalLogicTutorPaneHandler());
		ep.manipulateLogicHandler(new simplificationBtnHandler());
		ep.conjunctionLogicHandler(new conjunctionBtnHandler());
		ep.disjunctionLogicHandler(new disjunctionBtnHandler());
		ep.negationLogicHandler(new negationBtnHandler());
		ep.implicationLogicHandler(new implicationBtnHandler());
		ep.equivalenceLogicHandler(new equivalenceBtnHandler());

		//resultPane Handlers
		rp.btnSaveHandler(new saveResultBtnHandler());

		//Manipulation pane handlers
		mp.btnSaveHandler(new saveSimplificationBtnHandler());
		mp.btnCalcHandler(new calcSimplificationBtnHandler());
		mp.addButtonLogicHandler(new addSimplificationPaneHandler());
		mp.manipulateButtonLogicHandler(new manipulateSimplificationPaneHandler());
		mp.conjunctionLogicHandler(new conjunctionSimplificationBtnHandler());
		mp.disjunctionLogicHandler(new disjunctionSimplificationBtnHandler());
		mp.negationLogicHandler(new negationSimplificationBtnHandler());
		mp.implicationLogicHandler(new implicationSimplificationBtnHandler());
		mp.equivalenceLogicHandler(new equivalenceSimplificationBtnHandler());
		mp.updateBtnLogicHandler(new updateComboSimplificationBtnHandler());

		//Test pane handlers
		testp.takeTestHandler(new takeTestHandler());

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
				ep.removeSpaces(ep.getFormula());
				model.setFormula(ep.getFormula());
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
				catch (ParserException | EvaluationException Pex)
				{
					Pex.printStackTrace();
				}
				view.changeTab(3);
			}
		}
	}

	private class simplificationBtnHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			//retrieves data from the view
			try {
				ep.removeSpaces(ep.getFormula());
				model.setFormula(ep.getFormula());
			} catch (NullPointerException ex) {
				ex.printStackTrace();
			}
			//check input not empty
			if (model.getFormula().equals("")) {
				//output error
				alertDialogBuilder("To calculate, you need to enter a formula");
			}else {

				mp.populateFunction(model.getFormula());
				mp.populateResult(model.getFormula());
				view.changeTab(4);
			}
		}
	}

	private class conjunctionBtnHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			ep.setFormula("&");
		}
	}

	private class disjunctionBtnHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			ep.setFormula("|");
		}
	}
	private class negationBtnHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			ep.setFormula("~");
		}
	}
	private class implicationBtnHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			ep.setFormula("=>");
		}
	}
	private class equivalenceBtnHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			ep.setFormula("<=>");
		}
	}

	private class moveToStudyPaneHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			view.changeTab(1);
		}
	}
	private class moveToEvaluatePaneHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			view.changeTab(2);
		}
	}

	private class moveToManipulatePaneHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			view.changeTab(4);
		}
	}

	private class moveToTestPaneHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			view.changeTab(5);
		}
	}

	private class saveSimplificationBtnHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			// Get the contents of the TextArea
			String text = mp.getTxtResult().getText();
			if(!mp.getTxtResult().getText().isEmpty()) {
				DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
				String timeStamp = dateFormat.format(new Date());
				String fileName = "file_" + timeStamp + ".txt";
				// Set the file path
				String filePath = String.format(".//savedManipulations//%s", fileName);

				// Create a File object
				File file = new File(filePath);

				// Write the contents of the TextArea to the file

				try {
					FileWriter fileWriter = new FileWriter(file);
					fileWriter.write(text);
					fileWriter.close();
					successDialogBuilder("Save Complete");
				} catch (IOException e) {
					e.printStackTrace();
					alertDialogBuilder("Save InComplete");
				}
			}else {
				alertDialogBuilder("There is nothing to save");
			}
		}
	}

	private class saveResultBtnHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			// Get the contents of the TextArea
			String text = rp.getTxtFunction().getText() +"\n"+ rp.getTxtTruthTable().getText() +"\n"+ rp.getTxtResult().getText();
			if(!rp.getTxtResult().getText().isEmpty()) {
				DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
				String timeStamp = dateFormat.format(new Date());
				String fileName = "file_" + timeStamp + ".txt";
				// Set the file path
				String filePath = String.format(".//savedEvaluations//%s", fileName);
				// Create a File object
				File file = new File(filePath);
				// Write the contents of the TextArea to the file
				try {
					FileWriter fileWriter = new FileWriter(file);
					fileWriter.write(text);
					fileWriter.close();
					successDialogBuilder("Save Complete");
				} catch (IOException e) {
					e.printStackTrace();
					alertDialogBuilder("Save InComplete");
				}
			}else{
				alertDialogBuilder("There is nothing to save");
			}
		}
	}

	private class calcSimplificationBtnHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			try {
				model.setFormula(mp.getFormula());
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
				catch (ParserException | EvaluationException Pex)
				{
					Pex.printStackTrace();
				}
				view.changeTab(3);
			}
		}
	}
	private class conjunctionSimplificationBtnHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			mp.setFormula("&");
		}
	}

	private class disjunctionSimplificationBtnHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			mp.setFormula("|");
		}
	}
	private class negationSimplificationBtnHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			mp.setFormula("~");
		}
	}
	private class implicationSimplificationBtnHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			mp.setFormula("=>");
		}
	}
	private class equivalenceSimplificationBtnHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			mp.setFormula("<=>");
		}
	}

	private class addSimplificationPaneHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			mp.clearResult();
			//retrieves data from the view
			try {
				mp.removeSpaces(mp.getFormula());
				model.setFormula(mp.getFormula());
			} catch (NullPointerException ex) {
				ex.printStackTrace();
			}
			//check input not empty
			if (model.getFormula().equals("")) {
				//output error
				alertDialogBuilder("You need to enter a formula");
			}else {
				mp.populateFunction(model.getFormula());
				mp.populateResult(model.getFormula());
				model.setFormula(model.getFormula());
			}
		}
	}

	private class updateComboSimplificationBtnHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			model.setSelectedFormula(mp.getSelectedFormula());
			if (!model.getSelectedFormula().isEmpty()) {
				List<String> itemList = model.getRulesList();
				String expression = model.getSelectedFormula();
				Parser.list.clear();
				List<String> tokens = Tokenizer.tokenize(expression);
				List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
				Expression expr = Parser.Evaluator(Objects.requireNonNull(shunting));
				mp.updateComboBox(itemList, expr);
			} else{
				alertDialogBuilder("Highlight an expression");
			}
		}
	}

	private class manipulateSimplificationPaneHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent e) {
			//retrieves data from the view
			try {
				model.setSelectedFormula(mp.getSelectedFormula());
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
				switch (mp.getSelectedRule()) {
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
					case "Select an option.." -> alertDialogBuilder("Select a rule");
				}
				mp.setFunction(Objects.requireNonNull(result).toString(), mp.getIndexRange().getStart(), mp.getIndexRange().getEnd());
				model.updateResult(mp.getFunction());
				mp.clearFormula();
				mp.populateResult(model.getResult());
				mp.setFormula(mp.getFunction());
				model.setResult("");
			}
		}
	}

	private class takeTestHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			if(testp.getTreeView().getSelectionModel().getSelectedItem() != null ) {
				String selectedTest = testp.getTreeView().getSelectionModel().getSelectedItem().getValue();
				if(selectedTest.contains("Test")) {
					List<String> questions = db.retrieveQuestionsForTest(selectedTest);
					List<List<String>> answers = new ArrayList<>();
					List<String> correctAnswers= new ArrayList<>();
					for(String question: questions){
						List<String> questionAnswers = db.retrieveAnswersForQuestion(question);
						correctAnswers.add(db.retrieveCorrectAnswersForQuestion(question).get(0));
						answers.add(questionAnswers);
					}
					System.out.println(correctAnswers);

					TestQuestionsPane tqp = new TestQuestionsPane(selectedTest, questions, answers, correctAnswers);
				}else{
					alertDialogBuilder("You selected a lesson. Please select a test");
				}
			}else{
				alertDialogBuilder("You did not select a test");
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

	private void successDialogBuilder(String str) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText(null);
		alert.setContentText(str);
		alert.showAndWait();
	}
}
