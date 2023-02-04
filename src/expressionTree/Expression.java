package expressionTree;

//Abstract Expression class
public interface Expression {
	
	// Abstract methods
	boolean evaluate();
	Expression simplify(); 
}
