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
	private Label lbl;
	private Button btnStudy, btnCalc, btnTest;
	static final String idel= "-fx-background-color: linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),"
			+ "linear-gradient(#020b02, #3a3a3a), "
			+ "linear-gradient(#b9b9b9 0%, #c2c2c2 20%, #afafaf 80%, #c8c8c8 100%),"
			+ "linear-gradient(#f5f5f5 0%, #dbdbdb 50%, #cacaca 51%, #d7d7d7 100%);"
			+ "-fx-background-insets: 0,1,4,5,6;"
		    + "-fx-background-radius: 9,8,5,4,3;"
		    + "-fx-padding: 15 30 15 30;"
		    + "-fx-font-alignment: center;"
		    + "-fx-font: 30px Georgia;"
		    + "-fx-font-weight: bold;"
		    + "-fx-text-fill: #333333;"
		    + "-fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);"
			;
    static final String hover= "-fx-background-color: linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),"
			+ "linear-gradient(#020b02, #3a3a3a), "
			+ "linear-gradient(#b9b9b9 0%, #c2c2c2 20%, #afafaf 80%, #c8c8c8 100%),"
			+ "linear-gradient(#add8e6 0%, #dbdbdb 50%, #cacaca 51%, #d7d7d7 100%);"
			+ "-fx-background-insets: 0,1,4,5,6;"
		    + "-fx-background-radius: 9,8,5,4,3;"
		    + "-fx-padding: 15 30 15 30;"
		    + "-fx-font: 30px Georgia;"
		    + "-fx-font-weight: bold;"
		    + "-fx-text-fill: #333333;"
		    + "-fx-font-alignment: center;"
		    + "-fx-effect: dropshadow( three-pass-box , rgba(255,255,255,0.2) , 1, 0.0 , 0 , 1);"
			;;


	public WelcomingPane() {

		//styling
		this.setVgap(10);
		this.setHgap(10);
		this.setPadding(new Insets(10, 10, 10, 10));
		this.setAlignment(Pos.CENTER);


		//create labels
		lbl = new Label(" Welcom To The Logic Tutor\n"
				+ "\tPlease Select One Of\n"
				+ "\t The Options Below");

		lbl.setStyle("-fx-text-fill: white;"
				+ "-fx-font: 30px Georgia;"
				+ "-fx-font-weight: bold;"
				+ "-fx-font-alignment: center;"	
				);
		
		btnStudy = new Button("Study Logic");
		btnCalc = new Button("Calculator");
		btnTest = new Button("     Test    ");
		
		
		btnStudy.setStyle(idel);
		btnStudy.setOnMouseEntered(e -> btnStudy.setStyle(hover));
		btnStudy.setOnMouseExited(e -> btnStudy.setStyle(idel));
		
		btnCalc.setStyle(idel);
		btnCalc.setOnMouseEntered(e -> btnCalc.setStyle(hover));
		btnCalc.setOnMouseExited(e -> btnCalc.setStyle(idel));
		
		btnTest.setStyle(idel);
		btnTest.setOnMouseEntered(e -> btnTest.setStyle(hover));
		btnTest.setOnMouseExited(e -> btnTest.setStyle(idel));
		
		
		btnStudy.setMinSize(200, 75);
		btnCalc.setMinSize(200, 75);
		btnTest.setMinSize(200, 75);
		
		HBox hb = new HBox(btnStudy, btnCalc, btnTest);
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
	public void calculatorHandler(EventHandler<ActionEvent> handler) {
		btnCalc.setOnAction(handler);
	}
	public void testHandler(EventHandler<ActionEvent> handler) {
		btnTest.setOnAction(handler);
	}
}
