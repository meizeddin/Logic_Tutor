package controller;
import calcParser.EvaluationException;
import calcParser.ParserException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import view.LogicTutorRootPane;
import view.ResultPane;
import view.WelcomingPane;
import view.LogicTutorPane;
import view.LogicTutorMenuBar;
import model.LogicalFormula;
import model.autoTruth;

/**
 * @author meize
 * This is a simple Controller that Accepts input and converts it to commands for the model or the view.
 */
public class LogicTutorController {

	//fields to be used throughout class
	private LogicTutorRootPane view;
	private LogicTutorPane ltp;
	private ResultPane rp;
	private LogicTutorMenuBar ltmb;
	private LogicalFormula model;
	private WelcomingPane wp;

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


		//attach event handlers to view using private helper method
		this.attachEventHandlers();
	}

	/**
	 * helper method - used to attach event handlers
	 */
	private void attachEventHandlers() {

		//attach an event handler to the create student profile pane
		ltp.calculateLogicHandler(new LogicTutorPaneHandler());
		ltp.conjunctionLogicHandler(new conjunctionBtnHandler());
		ltp.disjunctionLogicHandler(new disjunctionBtnHandler());
		ltp.negationLogicHandler(new negationBtnHandler());
		ltp.implicationLogicHandler(new implicationBtnHandler());
		ltp.equivalenceLogicHandler(new equivalenceBtnHandler());
		
		//
		wp.studyHandler(new moveToStudyPaneHandler());
		wp.calculatorHandler(new moveToCalcPaneHandler());
		wp.testHandler(new moveToTestPaneHandler());

		//attach an event handler to the menu bar that closes the application
		ltmb.addExitHandler(e -> System.exit(0));

		//attach an event handler to the about menu bar of the application
		ltmb.addAboutHandler(e -> {ltmb.aboutInfo();});

	}
	//event handler private class, which can be used for creating a profile
	private class LogicTutorPaneHandler implements EventHandler<ActionEvent> {
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
				alertDialogBuilder(AlertType.ERROR, "Error Dialog", null, "To calculate, you need to enter a formula");
			}else {
				try
				{
					int counter = 0;
					counter += autoTruth.countVariables(model.getFormula()).length();
					String str =  "" + counter;
					String ch = autoTruth.countVariables(model.getFormula());
					model.setResult(autoTruth.resultTable(autoTruth.valuesTable(ch), autoTruth.booleanTable(counter), model.getFormula()));
					model.setTruthTable(autoTruth.truthTable(autoTruth.valuesTable(ch), Integer.parseInt(str)));
					
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
			//view.changeTab(0);
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

	/**
	 * helper method - alerts for validation
	 * @param type: accepts alert type e.g. confirmation, warning, etc.
	 * @param title: accepts a string of alert title
	 * @param header: accepts a string of header text
	 * @param content: accepts a string of the text content e.g. save successful 
	 */
	private void alertDialogBuilder(AlertType type, String title, String header, String content) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}
}