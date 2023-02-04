package expressionTree;

//Or class
public class Or extends Binary {
	public Or(Expression leftOperand, Expression rightOperand) {
		super(leftOperand, rightOperand);
	}

	public boolean evaluate() {
		return leftOperand.evaluate() || rightOperand.evaluate();
	}
}