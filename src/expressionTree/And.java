package expressionTree;

//And class
public class And extends Binary {
	public And(Expression leftOperand, Expression rightOperand) {

		super(leftOperand, rightOperand);
	}

	public boolean evaluate(Store s) {

		return leftOperand.evaluate(s) && rightOperand.evaluate(s);
	}

	@Override
	public String toString() {
		return leftOperand.toString() + "&" + rightOperand.toString();
	}
}