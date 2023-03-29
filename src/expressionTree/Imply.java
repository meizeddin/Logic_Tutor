package expressionTree;

//Implication class
public class Imply extends Binary {
	public Imply(Expression leftOperand, Expression rightOperand) {

		super(leftOperand, rightOperand);
	}

	public Expression getLeft() {
		return leftOperand;
	}
	public Expression getRight() {
		return rightOperand;
	}

	public boolean evaluate(Store s) {

		return !leftOperand.evaluate(s) || rightOperand.evaluate(s);
	}

	@Override
	public String toString() {
		return "(" + leftOperand.toString() + "=>" + rightOperand.toString() + ")";
	}

	@Override
	public Expression accept(ExpressionVisitor visitor) {
		return visitor.visit(this);
	}
}
