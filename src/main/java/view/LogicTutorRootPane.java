package view;

import javafx.application.Platform;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.*;
import javafx.scene.web.WebView;
import java.util.Objects;


/**
 * A class that initiates the view with the main container of the application
 * which then allow sub-containers to be added to it
 * this class extends BorderPane class from JavaFx
 * @author meize
 *
 */
public class LogicTutorRootPane extends BorderPane {

	private final WelcomingPane wp;
	private final EvaluatorPane ltp;
	private final ResultPane rp;
	private final LogicTutorMenuBar ltmb;
	private final TabPane tp;
	private final ManipulationPane mp;
	private final TestPane testp;

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
		ltp = new EvaluatorPane();
		rp = new ResultPane();
		mp = new ManipulationPane();
		testp = new TestPane();

		//create tabs with panes added
		Tab t1 = new Tab("Welcome", wp);
		Tab t2 = new Tab("Study");
		Tab t3 = new Tab("Evaluate", ltp);
		Tab t4 = new Tab("Result", rp);
		Tab t5 = new Tab("Manipulate", mp);
		Tab t6 = new Tab("Test", testp);

		t1.setStyle("-fx-font-size: 16px;"
				);
		t2.setStyle("-fx-font-size: 16px;"
				);
		t3.setStyle("-fx-font-size: 16px;"
				);
		t4.setStyle("-fx-font-size: 16px;"
				);
		t5.setStyle("-fx-font-size: 16px;"
		);
		t6.setStyle("-fx-font-size: 16px;"
		);

		// Create a WebView and add it to the second tab
		Platform.runLater(() -> {
			WebView webView = new WebView();
			webView.getEngine().load(Objects.requireNonNull(getClass().getResource("/html/StudyPage.html")).toExternalForm());
			webView.getEngine().setJavaScriptEnabled(true);
			t2.setContent(webView);
		});

		//add tabs to tab pane
		tp.getTabs().addAll(t1, t2, t3, t4, t5, t6);
		
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
	public EvaluatorPane getLogicTutorPane() {
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
	public ManipulationPane getManipulationPane() {
		return mp;
	}
	public TestPane getTestPane() {
		return testp;
	}



	/**
	 * method to allow the controller to change tabs
	 * @param index an int value that indicates the tab to be changed to. the first tab index is 0
	 */
	public void changeTab(int index) {
		tp.getSelectionModel().select(index);
	}
}
