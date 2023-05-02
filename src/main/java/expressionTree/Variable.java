package expressionTree;


import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

//Variable class
public class Variable extends Expression {
	private static final Map<String, Variable> variableMap = new HashMap<>();
	private final String name;
	//delete the value
	private Variable(String name) {

		this.name = name;
	}

	public static Variable of(String name) {
		Variable variable = variableMap.get(name);
		if (variable == null) {
			variable = new Variable(name);
			variableMap.put(name, variable);
		}
		return variable;
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
	public Expression accept(ExpressionVisitor visitor) {
		return visitor.visit(this);
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