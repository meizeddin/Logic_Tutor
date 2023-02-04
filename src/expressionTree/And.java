package expressionTree;

//And class
public class And extends Binary {
	public And(Expression leftOperand, Expression rightOperand) {
		super(leftOperand, rightOperand);
	}

	public boolean evaluate() {
		return leftOperand.evaluate() && rightOperand.evaluate();
	}
}