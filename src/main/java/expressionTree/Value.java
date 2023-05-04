package expressionTree;

//Abstract Value class
public abstract class Value extends Expression {
	private static final Value TRUE = new True();
    private static final Value FALSE = new False();
	
    public abstract boolean evaluate(Store s);

	public Expression simplify() {
		return this;
	}

	public static Value getTrue() {

		return TRUE;
	}

	public static Value getFalse() {

		return FALSE;
	}
	public Expression accept(ExpressionVisitor visitor){
		return visitor.visit(this);
	}
}

