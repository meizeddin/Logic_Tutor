package expressionTree;


//Not class
public class Not extends Unary {
	
	public Not(Expression operand) {
		super(operand);
	}

	public Expression getExpression() {
		return operand;
	}
	
	public boolean evaluate(Store s) {
		return !operand.evaluate(s);
	}

	@Override
	public String toString() {
		return "!" + "("+ operand.toString() + ")";
	}

	@Override
	public Expression accept(ExpressionVisitor visitor) {
		return visitor.visit(this);
	}
}