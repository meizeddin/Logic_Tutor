package expressionTree;


//Variable class
public class Variable implements Expression {
	private final String name;
	//delete the value
	public Variable(String name) {

		this.name = name;
	}

	public String getName() {

		return name;
    }

	
	@Override
	public Expression simplify() {

		return this;
	}

	@Override
	public boolean evaluate() {

		return false;
	}
}