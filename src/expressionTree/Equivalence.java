package expressionTree;

//Equivalence class
public class Equivalence extends Binary {
	public Equivalence(Expression leftOperand, Expression rightOperand) {

		super(leftOperand, rightOperand);
	}

	public boolean evaluate(Store s) {

		return leftOperand.evaluate(s) == rightOperand.evaluate(s);
	}

	@Override
	public String toString() {
		return leftOperand.toString() + "<=>" + rightOperand.toString();
	}
}
