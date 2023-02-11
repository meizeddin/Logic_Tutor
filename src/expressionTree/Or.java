package expressionTree;

//Or class
public class Or extends Binary {
	public Or(Expression leftOperand, Expression rightOperand) {

		super(leftOperand, rightOperand);
	}

	public boolean evaluate(Store s) {

		return leftOperand.evaluate(s) || rightOperand.evaluate(s);
	}

	@Override
	public String toString() {
		return leftOperand.toString() + "|" + rightOperand.toString();
	}
}