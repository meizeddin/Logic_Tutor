package expressionTree;

/**
 * The abstract class Expression represents a logical expression.
 * It defines the basic structure and functionality for all expressions, such as evaluating an expression, simplifying it, and converting it to a string representation.
 * This class also provides an accept method that can be used to apply a visitor pattern to the expression.
 */
//Abstract Expression class
public abstract class Expression {
	/**
	 * Evaluates the expression with the given store of variables.
	 * @param s the store of variables
	 * @return the result of the evaluation, either true or false
	 */
	public abstract boolean evaluate(Store s);
	/**
	 * Simplifies the expression.
	 * @return a simplified version of the expression
	 */
	abstract Expression simplify();
	/**
	 * Returns a string representation of the expression.
	 * @return a string representation of the expression
	 */
	public abstract String toString();
	/**
	 * Accepts a visitor for the expression.
	 * @param visitor the visitor to be applied to the expression
	 * @return the result of the visitor operation
	 */
	public abstract Expression accept(ExpressionVisitor visitor);
}
