package expressionTree;

public class Test {
	public static void main(String[] args) {
		// Create a Store object
		Store store = new Store();


		// Create Variable objects for the variables in the expression
		Variable a = Variable.of("A");
		Variable b = Variable.of("B");
		Variable r = Variable.of("R");
		
		// Add the variable "A" and its value to the store
				store.addVariable(a, Value.getFalse());
				store.addVariable(b, Value.getFalse());
				store.addVariable(r, Value.getFalse());

		

		// Create the And expression
		And and = new And(a, a);
		Or or = new Or(and.simplify(), b);
		

		// Evaluate the expression and print the result
		System.out.println(or.evaluate(store));  // Outputs "true"
		System.out.println(a.evaluate(store));  // Outputs "true"

	}

}
