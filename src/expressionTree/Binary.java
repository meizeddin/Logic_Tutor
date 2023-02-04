package expressionTree;


 //Abstract Binary class
public abstract class Binary implements Expression {
	protected Expression leftOperand;
	protected Expression rightOperand;

	public Binary(Expression leftOperand, Expression rightOperand) {
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
	}

	@Override
	public abstract boolean evaluate();

	@Override
	public Expression simplify() {
		// Simplify operands first
		Expression simplifiedLeftOperand = leftOperand.simplify();
		Expression simplifiedRightOperand = rightOperand.simplify();

		// If both operands are the same, return one of them
		if (simplifiedLeftOperand.equals(simplifiedRightOperand)) {
			return simplifiedRightOperand;
		}
		// If one of the operands is a singleton value, return the other operand
		if (simplifiedLeftOperand instanceof True) {
			return simplifiedRightOperand;
		}
		if (simplifiedLeftOperand instanceof False) {
			return simplifiedLeftOperand;
		}
		if (simplifiedRightOperand instanceof True) {
			return simplifiedLeftOperand;
		}
		if (simplifiedRightOperand instanceof False) {
			return simplifiedRightOperand;
		}

		// If none of the above conditions are met, return the original expression
		return this;
	}
}

