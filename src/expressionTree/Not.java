package expressionTree;


//Not class
public class Not extends Unary {
	
	public Not(Expression operand) {

		super(operand);
	}
	
	public boolean evaluate(Store s) {

		return !operand.evaluate(s);
	}

	@Override
	public String toString() {
		return "!" + operand.toString();
	}
}