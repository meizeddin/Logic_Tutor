package model;

import java.util.function.Predicate;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.TextField;

/**
 * A helper class for validating the formula entered by a student on the logic tutor view pane
 * the class extends TextField class from JavaFx
 * @author meize
 *
 */
public class ValidationClass extends TextField {

	//fields
	private final Predicate<String> validation;
	public BooleanProperty isValidProperty = new SimpleBooleanProperty();

	//constructors
	/**
	 * A method that accepts new text(formula) and validate it 
	 * @param validation: accepts types of validation, regular expressions to which input is allowed
	 */
	public ValidationClass(Predicate<String> validation) {
		this.validation = validation;
		textProperty().addListener((o, oldValue, newText) -> {
			isValidProperty.set(validation.test(newText));
		});
		isValidProperty.set(validation.test(""));	
	}

	/**
	 * @returns the specified validation
	 */
	public Predicate<String> getValidation() {
		return validation;
	}
}
