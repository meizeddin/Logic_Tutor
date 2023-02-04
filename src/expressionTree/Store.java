package expressionTree;

//Import the HashMap class
import java.util.HashMap;

public class Store {
	// Create a HashMap object to store the variables and their values
	private HashMap<Expression, Value> map = new HashMap<Expression, Value>();
	

	// Method to add a new variable and its value to the store
	public void addVariable(Expression x, Value value) {
		map.put(x, value);
	}

	// Method to get the value of a variable from the store
	public Value getValue(Expression x) {
		return map.get(x);
	}
}