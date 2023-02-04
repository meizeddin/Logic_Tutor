package expressionTree;

//Abstract Value class
public abstract class Value implements Expression {
	private static final Value TRUE = new True();
    private static final Value FALSE = new False();
	
    public abstract boolean evaluate();
	
	public Expression simplify() {
		return this;
	}

	public static Value getTrue() {
		return TRUE;
	}

	public static Value getFalse() {
		return FALSE;
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
	public boolean evaluate() {
		return t;
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
	public boolean evaluate() {
		return f;
	}
	
}
