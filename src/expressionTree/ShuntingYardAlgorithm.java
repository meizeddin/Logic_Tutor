package expressionTree;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
public class ShuntingYardAlgorithm {


	// A map of operators to their precedence values
	private static final HashMap<String, Integer> PRECEDENCE = new HashMap<>();

	static {
		PRECEDENCE.put("!", 5);
		PRECEDENCE.put("~", 5);
		PRECEDENCE.put("&", 4);
		PRECEDENCE.put("|", 3);
		PRECEDENCE.put("=>", 2);
		PRECEDENCE.put("<=>", 1);
		PRECEDENCE.put("(", -1);
		PRECEDENCE.put(")", -1);
		PRECEDENCE.put("", -1);
	}

	/**
	 * Determines if the given string is a letter/variable
	 * @param st the string to check
	 * @return true if the string is a letter, false otherwise
	*/
	private static boolean letter(String st) {
		Pattern TOKEN_PATTERN = Pattern.compile(
				// Variables (single letters)
				"([A-Za-z])");
		Matcher matcher = TOKEN_PATTERN.matcher(st);
		return matcher.find();
	}

	/**
	 * Evaluates an infix expression
	 * @param tokenizedExpression the expression to evaluate in the form of a list of tokens
	 * @return the post-fix expression as a list of tokens
	*/
	public static List<String> infixToPostfix(List<String> tokenizedExpression) {
		// Initialize the stack to hold operators
		Deque<String> stack = new ArrayDeque<>();
		// Initialize the output list to hold the post-fix expression
		List<String> output = new ArrayList<>();
		
		// Iterate through each token in the expression
		// If the current token is a letter, add it to the output
		tokenizedExpression.forEach(st -> {
			if (letter(st))
				output.add(st);

				// If the current token is an open parenthesis, push it onto the stack
			else if (st.equals("("))
				stack.push(st);

				// If the current token is a closing parenthesis,
				// pop operators from the stack and add them to the output
				// until an open parenthesis is found
			else if (st.equals(")")) {
				while (!stack.isEmpty() && !stack.peek().equals("(")) {
					output.add(stack.peek());
					stack.pop();
				}
				stack.pop();
			}
			// If the current token is an operator,
			// pop operators from the stack and add them to the output
			// until the stack is empty
			// or the operator on the top of the stack has lower precedence than the current operator
			else {
				while (!stack.isEmpty() && PRECEDENCE.get(st) <= PRECEDENCE.get(stack.peek())) {
					output.add(stack.peek());
					stack.pop();
				}
				stack.push(st);
			}
		});
		// Pop any remaining operators from the stack and add them to the output
		while (!stack.isEmpty()) {
			if(stack.peek().equals("("))
				return null;
			output.add(stack.peek());
			stack.pop();
		}
		//return the post-fix list.
		return output;
	}

	public static void main(String[] args)
	{
		List<String> tokens = Tokenizer.tokenize("A=>B=>R&C");
		// Function call
		System.out.println(infixToPostfix(tokens));
		//System.out.println(letter("4"));
	}
}