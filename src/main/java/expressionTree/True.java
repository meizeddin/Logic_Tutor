package expressionTree;

public class True extends Value {

    private static True instance = null;

    public boolean t;

    public True() {

        t = true;
    }

    public static True getInstance() {
        if (instance == null)
            instance = new True();
        return instance;
    }

    @Override
    public boolean evaluate(Store s) {

        return t;
    }

    @Override
    public String toString() {
        return "T";
    }

    @Override
    public Expression accept(ExpressionVisitor visitor) {
        return visitor.visit(this);
    }
}
