package expressionTree.rules;

import expressionTree.*;
import java.util.List;
import java.util.Objects;

/**
 * A visitor class that implements Elimination Left law on an expression tree.
 * This class implements the `ExpressionVisitor` interface, allowing it to
 * traverse an expression tree and manipulate the expressions contained within.
 * <p>
 * Elimination of & Left law states:
 * If A & B is true,
 * then B
 * <p>
 * When visiting 'And' Expression, this visitor applies the elimination law.
 * For all other expression types, the expression is returned unchanged.
 * <p>
 */
public class AndEliminationLVisitor implements ExpressionVisitor {

    public static void main(String[] args) {
        List<String> tokens = Tokenizer.tokenize("(!A & C)");
        List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
        Expression exp = Parser.Evaluator(Objects.requireNonNull(shunting));
        AndEliminationLVisitor visitor = new AndEliminationLVisitor();
        System.out.println(exp.accept(visitor));
    }
    @Override
    public Expression visit(And and) {

        return and.getRight();
    }

    @Override
    public Expression visit(Or or) {
        Expression left = or.getLeft();
        Expression right = or.getRight();

        return new Or(left, right);
    }

    @Override
    public Expression visit(Equivalence equivalence) {
        Expression left = equivalence.getLeft();
        Expression right = equivalence.getRight();
        return new Equivalence(left, right);
    }

    @Override
    public Expression visit(Imply imply) {
        Expression left = imply.getLeft();
        Expression right = imply.getRight();

        return new Imply(left, right);
    }

    @Override
    public Expression visit(Not not) {
        Expression expr = not.getExpression();
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
        return expr instanceof And;
    }
}