package expressionTree;


import org.jetbrains.annotations.NotNull;

import javax.naming.Context;

//Variable class
public class Variable extends Expression {
	private final String name;
	//delete the value
	public Variable(String name) {

		this.name = name;
	}

	public String getName() {

		return name;
	}


	@Override
	public Expression simplify() {

		return this;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean evaluate(@NotNull Store s) {
		Value value = s.getValue(this);
		if (value == null) {
			// Handle the case where the variable is not stored in the Store
			// You can throw an exception, return a default value, or handle the error in another way
			return false;
		} else {
			return value.evaluate(s);
		}
	}
}