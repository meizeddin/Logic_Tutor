package expressionTree;

public class False extends Value {

    private static False instance = null;

    public boolean f;

    public False() {

        f = false;
    }

    public static False getInstance() {
        if (instance == null)
            instance = new False();
        return instance;
    }

    @Override
    public boolean evaluate(Store s) {

        return f;
    }

    @Override
    public String toString() {
        return "F";
    }

    @Override
    public Expression accept(ExpressionVisitor visitor) {
        return visitor.visit(this);
    }

}
