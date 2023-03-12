package expressionTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

import static expressionTree.Variable.of;

public class Parser {
	//array list to save the variables in order to assign them values after they have been created
	public static ArrayList<Variable> list = new ArrayList<>();
	public static Expression Evaluator(List<String> postFixTokens) {
		Stack<Expression> stack = new Stack<>();
		Variable v;
		for (String token : postFixTokens) {
			Expression left, right;
			switch (token) {
				case "&" -> {
					right = stack.pop();
					left = stack.pop();
					stack.push(new And(left, right));
				}
				case "|" -> {
					right = stack.pop();
					left = stack.pop();
					stack.push(new Or(left, right));
				}
				case "=>" -> {
					right = stack.pop();
					left = stack.pop();
					stack.push(new Imply(left, right));
				}
				case "<=>" -> {
					right = stack.pop();
					left = stack.pop();
					stack.push(new Equivalence(left, right));
				}
				case "!", "~" -> {
					right = stack.pop();
					stack.push(new Not(right));
				}
				case "T", "t" -> {
					stack.push(new True());
				}
				case "F", "f" -> {
					stack.push(new False());
				}
				default -> {
					v = Variable.of(token);
					stack.push(v);
					if(!list.contains(v)) {
						list.add(v);
					}
				}
			}
		}
		return stack.pop();
	}

	public static void main(String[] args) {
		// Create a Store object
		Store store = new Store();
		Expression s = Evaluator(Objects.requireNonNull(ShuntingYardAlgorithm.infixToPostfix(Tokenizer.tokenize("A"))));
		store.addVariable(list.get(0), Value.getTrue());

		// Evaluate the expression and print the result
			System.out.println(s.evaluate(store));
			System.out.println(store.getValue(list.get(0)).evaluate(store));

	}
}
