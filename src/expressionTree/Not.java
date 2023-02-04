package expressionTree;


//Not class
public class Not extends Unary {
	
	public Not(Expression operand) {
		super(operand);
	}
	
	public boolean evaluate() {
		return !operand.evaluate();
	}
}