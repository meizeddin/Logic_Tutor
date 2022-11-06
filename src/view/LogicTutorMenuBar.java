package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


/**
 * A view class that is responsible of showing the menu bar on the application
 * the class extends MenuBar class from JavaFx
 * @author meize
 *
 */
public class LogicTutorMenuBar extends MenuBar {

	//initiates menu item fields
	private MenuItem saveItem, loadItem, aboutItem, exitItem;

	/**
	 * a constructor method to initiate the menuBar view of the application
	 */
	public LogicTutorMenuBar() { 

		//temp-var for menus and menu items within this MenuBar
		Menu menu;

		menu = new Menu("_File");
		menu.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
		loadItem = new MenuItem("_Load");
		loadItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT+L"));
		menu.getItems().add(loadItem);

		saveItem = new MenuItem("_Save");
		saveItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT+S"));
		menu.getItems().add(saveItem);

		menu.getItems().add( new SeparatorMenuItem());

		exitItem = new MenuItem("E_xit");
		exitItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT+X"));
		menu.getItems().add(exitItem);

		this.getMenus().add(menu);

		menu = new Menu("_Help");
		menu.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
		aboutItem = new MenuItem("_About");
		aboutItem.setAccelerator(KeyCombination.keyCombination("SHORTCUT+A"));
		menu.getItems().add(aboutItem);

		this.getMenus().add(menu); 
	}

	/**
	 * setups about-button scene
	 */
	public void aboutInfo() {
		final Stage about = new Stage();
		about.setTitle("Creators Info");
		about.setHeight(150);
		about.setWidth(300);
		about.setResizable(false);
		about.setAlwaysOnTop(true);

		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER);
		Label lbl = new Label("This app is to be used by students\n"
				+ "  to learn about propositional logic\n\n"
				+ "	 App is created by:\n"
				+ "P2612742@my365.dmu.ac.uk");
		lbl.setFont(Font.font ("Helvetica", 15));
		Scene scene = new Scene(hbox, 300, 150);
		hbox.getChildren().add(lbl);

		about.setScene(scene);
		about.show();
	}

	//these methods allow handlers to be externally attached to this menubar and used by the controller
	/**
	 * @param handler: allows an external handler to be attached to the save item on the menuBar
	 */
	public void addSaveHandler(EventHandler<ActionEvent> handler) {
		saveItem.setOnAction(handler);
	}
	/**
	 * @param handler: allows an external handler to be attached to the load item on the menuBar
	 */
	public void addLoadHandler(EventHandler<ActionEvent> handler) {
		loadItem.setOnAction(handler);
	}
	/**
	 * @param handler: allows an external handler to be attached to the about item on the menuBar
	 */
	public void addAboutHandler(EventHandler<ActionEvent> handler) {
		aboutItem.setOnAction(handler);
	}
	/**
	 * @param handler: allows an external handler to be attached to the exit item on the menuBar
	 */
	public void addExitHandler(EventHandler<ActionEvent> handler) {
		exitItem.setOnAction(handler);
	}

}
