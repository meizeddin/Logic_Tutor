package expressionTree;


//Abstract Unary class
public abstract class Unary extends Expression {
	protected Expression operand;

	public Unary(Expression operand) {
		this.operand = operand;
	}

	@Override
	public abstract boolean evaluate(Store s);
	
	@Override
	public Expression simplify() {
		// Simplify operand first
        Expression simplifiedOperand = operand.simplify();
        
        // If operand is a singleton value, return the negated value
        if (simplifiedOperand instanceof True) {
            return False.getInstance();
        }
        if (simplifiedOperand instanceof False) {
            return True.getInstance();
        }
        
        // If none of the above conditions are met, return the original expression
        return this;
    }
}