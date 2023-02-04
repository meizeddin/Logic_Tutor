package view;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;



/**
 * A class that initiates the view with the main container of the application
 * which then allow sub-continers to be added to it
 * this class extends BorderPane class from JavaFx
 * @author meize
 *
 */
public class LogicTutorRootPane extends BorderPane {

	private WelcomingPane wp;
	private LogicTutorPane ltp;
	private ResultPane rp;
	private LogicTutorMenuBar ltmb;
	private TabPane tp;
	private SimplificationPane sp;
	
	/**
	 * a constructor method to initiate the different tabs and their specific panes
	 */
	public LogicTutorRootPane() {
		//create tab pane and disable tabs from being closed
		tp = new TabPane();
		tp.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		//tp.setPadding(new Insets(5, 5, 5, 5));
		
		//create panes
		wp = new WelcomingPane();
		ltp = new LogicTutorPane();
		rp = new ResultPane();
		sp = new SimplificationPane();
		
		//create tabs with panes added
		Tab t1 = new Tab("Welcome", wp);
		Tab t2 = new Tab("Calculator", ltp);
		Tab t3 = new Tab("Result", rp);
		Tab t4 = new Tab("Simplification", sp);
		t1.setStyle("-fx-font-size: 16px;"
				);
		t2.setStyle("-fx-font-size: 16px;"
				);
		t3.setStyle("-fx-font-size: 16px;"
				);
		t4.setStyle("-fx-font-size: 16px;"
				);
		//add tabs to tab pane
		tp.getTabs().addAll(t1, t2, t3, t4);
		
		//create menu bar
		ltmb = new LogicTutorMenuBar();
		
		//add menu bar and tab pane to this root pane
		this.setTop(ltmb);
		this.setCenter(tp);
		
	}

	//methods allowing sub-containers to be accessed by the controller.
	/**
	 * @returns the Create-Student-Profile-Pane to be accessed by the controller
	 */
	public LogicTutorPane getLogicTutorPane() {
		return ltp;
	}
	/**
	 * @returns the Module-Chooser-MenuBar-pane to be accessed by the controller
	 */
	public LogicTutorMenuBar getLogicTutorMenuBar() {
		return ltmb;
	}
	
	public ResultPane getResultPane() {
		return rp;
	}
	public WelcomingPane getWelcomingPane() {
		return wp;
	}
	public SimplificationPane getSimplificationPane() {
		return sp;
	}
	
	/**
	 * method to allow the controller to change tabs
	 * @param index: an int value that indicates the tab to be changed to. first tab index is 0
	 */
	public void changeTab(int index) {
		tp.getSelectionModel().select(index);
	}
}
