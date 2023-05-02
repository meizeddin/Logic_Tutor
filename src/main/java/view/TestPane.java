package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.*;



public class TestPane extends GridPane {
    private final Label lbl;
    private final Button btnTakeTest;
    private final TreeView<String> treeView;



    public TestPane() {

        //styling
        this.setVgap(10);
        this.setHgap(10);
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setAlignment(Pos.CENTER);


        //create labels
        lbl = new Label("Select A Test");

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


        btnTakeTest = new Button("Start the Test");


        btnTakeTest.setStyle(WelcomingPane.idle);
        btnTakeTest.setOnMouseEntered(e -> btnTakeTest.setStyle(WelcomingPane.hover));
        btnTakeTest.setOnMouseExited(e -> btnTakeTest.setStyle(WelcomingPane.idle));

        btnTakeTest.setMinSize(200, 75);

        treeView = new TreeView<>();
        treeView.setMaxSize(1000, 500);
        treeView.setPrefSize(1000, 1000);
        treeView.setPadding(new Insets(10, 10, 10, 10));
        treeView.setStyle("-fx-control-inner-background: #8897b3;"
                + "-fx-background-color: linear-gradient(to right, #212838, #c2e3fc);"
                + "-fx-text-fill: black; -fx-font-size: 30px;"
        );


        HBox hb = new HBox(btnTakeTest);
        hb.setAlignment(Pos.BASELINE_CENTER);
        hb.setPadding(new Insets(5, 5, 5, 5));
        hb.setSpacing(20);

        HBox hb1 = new HBox(lbl);
        hb1.setAlignment(Pos.BASELINE_CENTER);
        hb1.setPadding(new Insets(5, 5, 5, 5));
        hb1.setSpacing(20);

        VBox vb = new VBox(hb1, treeView, hb);
        vb.setSpacing(10);
        this.add(vb, 1, 1);
    }
    public void takeTestHandler(EventHandler<ActionEvent> handler) {
        btnTakeTest.setOnAction(handler);
    }

    public void populateTreeView(TreeItem<String> rootItem, TreeItem<String> lessonItem){
        rootItem.setExpanded(true);
        lessonItem.setExpanded(true);
        rootItem.getChildren().add(lessonItem);
        treeView.setRoot(rootItem);
    }

    public TreeView<String> getTreeView(){
        return treeView;
    }

}
