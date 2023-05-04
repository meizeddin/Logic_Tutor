package expressionTree.rules;

import expressionTree.*;
import java.util.List;
import java.util.Objects;

/**
 * A visitor class that implements Elimination of => law on an expression tree.
 * This class implements the `ExpressionVisitor` interface, allowing it to
 * traverse an expression tree and manipulate the expressions contained within.
 * <p>
 * Elimination of => law states:
 * If P is true, given P=>Q
 * then Q
 * <p>
 * When visiting 'Imply' Expression, this visitor applies the elimination law.
 * For all other expression types, the expression is returned unchanged.
 * <p>
 */
public class ImplyEliminationVisitor implements ExpressionVisitor {

    public static void main(String[] args) {
        List<String> tokens = Tokenizer.tokenize("(!A => C)");
        List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
        Expression exp = Parser.Evaluator(Objects.requireNonNull(shunting));
        ImplyEliminationVisitor visitor = new ImplyEliminationVisitor();
        System.out.println(exp.accept(visitor));
    }
    @Override
    public Expression visit(And and) {
        Expression left = and.getLeft().accept(this);
        Expression right = and.getRight().accept(this);

        return new And(left,right);
    }

    @Override
    public Expression visit(Or or) {
        Expression left = or.getLeft().accept(this);
        Expression right = or.getRight().accept(this);

        return new Or(left, right);
    }

    @Override
    public Expression visit(Equivalence equivalence) {
        Expression left = equivalence.getLeft().accept(this);
        Expression right = equivalence.getRight().accept(this);
        return new Equivalence(left, right);
    }

    @Override
    public Expression visit(Imply imply) {
        Expression left = imply.getLeft().accept(this);
        Expression right = imply.getRight().accept(this);

        return right;
    }

    @Override
    public Expression visit(Not not) {
        Expression expr = not.getExpression().accept(this);
        return new Not(expr);
    }

    @Override
    public Expression visit(Variable variable) {
        return variable;
    }

    @Override
    public Expression visit(Value value) {
        return value;
    }

    public boolean canApply(Expression expr) {
        boolean result = false;
        if (expr instanceof Imply) {
            result = true;
        }
        return result;
    }
}