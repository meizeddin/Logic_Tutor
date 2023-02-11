package expressionTree;

//Abstract Expression class
public abstract class Expression {
	
	// Abstract methods
	public abstract boolean evaluate(Store s);
	abstract Expression simplify();

	public abstract String toString();
}
