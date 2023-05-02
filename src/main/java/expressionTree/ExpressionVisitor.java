package expressionTree;
/**
 * ExpressionVisitor is an interface for visiting Expression objects.
 * <p>
 * This interface defines methods for each type of Expression (And, Or, Equivalence, Imply, Not, Variable, and Value).
 */
public interface ExpressionVisitor {
    /**
     * Visits an And Expression object.
     * @param and the And Expression object to be visited
     * @return the modified Expression object
     */
    Expression visit(And and);
    /**
     * Visits an Or Expression object.
     * @param or the Or Expression object to be visited
     * @return the modified Expression object
     */
    Expression visit(Or or);
    /**
     * Visits an Equivalence Expression object.
     * @param equivalence the Equivalence Expression object to be visited
     * @return the modified Expression object
     */
    Expression visit(Equivalence equivalence);
    /**
     * Visits an Imply Expression object.
     * @param imply the Imply Expression object to be visited
     * @return the modified Expression object
     */
    Expression visit(Imply imply);
    /**
     * Visits a Not Expression object.
     * @param not the Not Expression object to be visited
     * @return the modified Expression object
     */
    Expression visit(Not not);
    /**
     * Visits a Variable Expression object.
     * @param variable the Variable Expression object to be visited
     * @return the modified Expression object, however, the variable is never going to be modified
     */
    Expression visit(Variable variable);
    /**
     * Visits a Value Expression object.
     * @param value the Value Expression object to be visited
     * @return the modified Expression object, however, the value is never going to be modified
     */
    Expression visit(Value value);

}
