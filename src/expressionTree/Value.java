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

class True extends Value{
	
	private static True instance = null;
	
	public boolean t;
	
	True() {

		t = true;
	}
	
	public static True getInstance() {
		if(instance == null)
			instance = new True();
		return instance;
	}
	@Override
	public boolean evaluate(Store s) {

		return t;
	}

	@Override
	public String toString() {
		return "TRUE";
	}

	@Override
	public Expression accept(ExpressionVisitor visitor) {
		return visitor.visit(this);
	}
}

class False extends Value{

private static False instance = null;
	
	public boolean f;
	
	False() {

		f = false;
	}
	
	public static False getInstance() {
		if(instance == null)
			instance = new False();
		return instance;
	}
	@Override
	public boolean evaluate(Store s) {

		return f;
	}

	@Override
	public String toString() {
		return "FALSE";
	}

	@Override
	public Expression accept(ExpressionVisitor visitor) {
		return visitor.visit(this);
	}

}
