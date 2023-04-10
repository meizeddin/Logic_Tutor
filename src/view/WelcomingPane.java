package view;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class WelcomingPane extends GridPane{
	private final Label lbl;
	private final Button btnStudy, btnEvaluate, btnManipulate, btnTest;
	static final String idle = "-fx-background-color: linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),"
			+ "linear-gradient(#020b02, #3a3a3a), "
			+ "linear-gradient(#1c2331 0%, #c2c2c2 20%, #afafaf 80%, #1c2331 100%),"
			+ "linear-gradient(#1c2331 0%, #dbdbdb 50%, #cacaca 51%, #1c2331 100%);"
			+ "-fx-background-insets: 0,1,4,5,6;"
		    + "-fx-background-radius: 30,29,25,24,23;"
		    + "-fx-padding: 15 30 15 30;"
		    + "-fx-font-alignment: center;"
		    + "-fx-font: 30px Georgia;"
		    + "-fx-text-fill: #333333;"
		    + "-fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);"
			;
    static final String hover= "-fx-background-color: linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),"
			+ "linear-gradient(#020b02, #3a3a3a), "
			+ "linear-gradient(#b3b3b3 0%, #c2c2c2 20%, #afafaf 80%, #c8c8c8 100%),"
			+ "linear-gradient(#304461 0%, #dbdbdb 50%, #cacaca 51%, #d7d7d7 100%);"
			+ "-fx-background-insets: 0,1,4,5,6;"
		    + "-fx-background-radius: 30,29,25,24,23;"
		    + "-fx-padding: 15 30 15 30;"
		    + "-fx-font: 30px Georgia;"
		    + "-fx-text-fill: #333333;"
		    + "-fx-font-alignment: center;"
		    + "-fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);"
			;


	public WelcomingPane() {

		//styling
		this.setVgap(10);
		this.setHgap(10);
		this.setPadding(new Insets(10, 10, 10, 10));
		this.setAlignment(Pos.CENTER);


		//create labels
		lbl = new Label("\tWelcome To The Logic Tutor\nPlease Select One Of The Options Below");

		// apply CSS to the label to add a blur effect to the background
		lbl.setStyle("-fx-font-family: Harrington;" +
				"-fx-padding: 10px;" +
				"-fx-background-color: #1c2331;" +
				"-fx-background-radius: 25;" +
				"-fx-border-radius: 25; " +
				"-fx-border-color: white; " +
				"-fx-border-width: 2px;" +
				"-fx-text-fill: white;" +
				"-fx-font-size: 50px;" +
				"-fx-alignment: center;");

		// add a hover effect to the label to make it shine
		lbl.setOnMouseEntered(e -> lbl.setStyle("-fx-font-family: Harrington;" +
				"-fx-effect: dropshadow(three-pass-box, white, 10, 0, 0, 0);" +
				"-fx-padding: 10px;" +
				"-fx-background-color: black;" +
				"-fx-background-radius: 25;" +
				"-fx-border-radius: 25; " +
				"-fx-border-color: white; " +
				"-fx-border-width: 2px;" +
				"-fx-text-fill: white;" +
				"-fx-font-size: 50px;" +
				"-fx-alignment: center;"));

		lbl.setOnMouseExited(e -> lbl.setStyle("-fx-font-family: Harrington;" +
				"-fx-padding: 10px;" +
				"-fx-background-color: #1c2331;" +
				"-fx-background-radius: 25;" +
				"-fx-border-radius: 25; " +
				"-fx-border-color: white; " +
				"-fx-border-width: 2px;" +
				"-fx-text-fill: white;" +
				"-fx-font-size: 50px;" +
				"-fx-alignment: center;"));

		
		btnStudy = new Button("Study Logic");
		btnEvaluate = new Button("Evaluator");
		btnManipulate = new Button("Manipulator");
		btnTest = new Button("     Test    ");
		
		
		btnStudy.setStyle(idle);
		btnStudy.setOnMouseEntered(e -> btnStudy.setStyle(hover));
		btnStudy.setOnMouseExited(e -> btnStudy.setStyle(idle));

		btnEvaluate.setStyle(idle);
		btnEvaluate.setOnMouseEntered(e -> btnEvaluate.setStyle(hover));
		btnEvaluate.setOnMouseExited(e -> btnEvaluate.setStyle(idle));

		btnManipulate.setStyle(idle);
		btnManipulate.setOnMouseEntered(e -> btnManipulate.setStyle(hover));
		btnManipulate.setOnMouseExited(e -> btnManipulate.setStyle(idle));
		
		btnTest.setStyle(idle);
		btnTest.setOnMouseEntered(e -> btnTest.setStyle(hover));
		btnTest.setOnMouseExited(e -> btnTest.setStyle(idle));
		
		
		btnStudy.setMinSize(200, 75);
		btnEvaluate.setMinSize(200, 75);
		btnManipulate.setMinSize(200, 75);
		btnTest.setMinSize(200, 75);
		
		HBox hb = new HBox(btnStudy, btnEvaluate, btnManipulate, btnTest);
		hb.setAlignment(Pos.BASELINE_CENTER);
		hb.setPadding(new Insets(5, 5, 5, 5));
		hb.setSpacing(20);
		
		HBox hb1 = new HBox(lbl);
		hb1.setAlignment(Pos.BASELINE_CENTER);
		hb1.setPadding(new Insets(5, 5, 5, 5));
		hb1.setSpacing(20);
		
		VBox vb = new VBox(hb1, hb);
		vb.setSpacing(200);
		this.add(vb, 1, 1);

	}
	public void studyHandler(EventHandler<ActionEvent> handler) {
		btnStudy.setOnAction(handler);
	}
	public void evaluatorHandler(EventHandler<ActionEvent> handler) {
		btnEvaluate.setOnAction(handler);
	}
	public void manipulatorHandler(EventHandler<ActionEvent> handler) {
		btnManipulate.setOnAction(handler);
	}
	public void testHandler(EventHandler<ActionEvent> handler) {
		btnTest.setOnAction(handler);
	}
}
