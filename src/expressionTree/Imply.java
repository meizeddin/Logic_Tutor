package expressionTree;

//Implication class
public class Imply extends Binary {
	public Imply(Expression leftOperand, Expression rightOperand) {
		super(leftOperand, rightOperand);
	}

	public boolean evaluate() {
		return !leftOperand.evaluate() || rightOperand.evaluate();
	}
}
