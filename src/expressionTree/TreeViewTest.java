package expressionTree;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;

public class TreeViewTest extends Application {

    @Override
    public void start(Stage primaryStage) {

        // create tree view root item for (A&B)
        TreeItem<Expression> rootItem = new TreeItem<>(new And(Variable.of("A"), Variable.of("B")));

        // create tree view child items for A and B
        TreeItem<Expression> itemA = new TreeItem<>(Variable.of("A"));
        TreeItem<Expression> itemB = new TreeItem<>(Variable.of("B"));

        // add child items to root item
        rootItem.getChildren().addAll(itemA, itemB);

        // create tree view and set root item
        TreeView<Expression> treeView = new TreeView<>(rootItem);

        // create scene and add tree view to it
        Scene scene = new Scene(treeView, 200, 200);

        // set stage title and show it
        primaryStage.setTitle("Tree View Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
