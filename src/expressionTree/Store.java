package expressionTree;

//Import the HashMap class
import java.util.ArrayList;
import java.util.HashMap;

public class Store {
	// Create a HashMap object to store the variables and their values
	private final HashMap<Variable, Value> map = new HashMap<>();
	

	// Method to add a new variable and its value to the store
	public void addVariable(Variable x, Value value) {
		map.put(x, value);
	}

	// Method to get the value of a variable from the store
	public Value getValue(Variable x) {
		return map.get(x);
	}

	public ArrayList<String> getAll(){
		ArrayList<String> arr = new ArrayList<>();
		map.forEach((key, value)->{
			arr.add(key.toString());
		});
		return arr;
	}

	public void clear(){
		map.clear();
	}

}