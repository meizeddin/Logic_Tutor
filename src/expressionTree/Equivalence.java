package expressionTree;

//Equivalence class
public class Equivalence extends Binary {
	public Equivalence(Expression leftOperand, Expression rightOperand) {
		super(leftOperand, rightOperand);
	}

	public boolean evaluate() {
		return leftOperand.evaluate() == rightOperand.evaluate();
	}
}
