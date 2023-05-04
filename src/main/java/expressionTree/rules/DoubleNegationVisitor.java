package expressionTree.rules;

import expressionTree.*;

import java.util.List;
import java.util.Objects;

/**
 * A visitor class that implements Double Negation law on an expression tree.
 * This class implements the 'ExpressionVisitor' interface, allowing it to
 * traverse an expression tree and manipulate the expressions contained within.
 * <p>
 * Double Negation law states that:
 * !(!A) = A
 * and
 * A = !(!A)
 * <p>
 * When visiting a 'Not' expression, this visitor applies Double Negation law if the
 * expression contained within the 'Not' node is another 'Not' expression.
 * For all other expression types, the expression is returned unchanged.
 * <p>
 * This visitor does not modify expressions of type 'Equivalence', 'Imply', 'And', 'Or', or 'Variable'.
 */
public class DoubleNegationVisitor implements ExpressionVisitor {

    public static void main(String[] args) {
        List<String> tokens = Tokenizer.tokenize("A&A");
        List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
        Expression exp = Parser.Evaluator(Objects.requireNonNull(shunting));
        DoubleNegationVisitor visitor = new DoubleNegationVisitor();
        System.out.println(exp.accept(visitor));
    }

    @Override
    public Expression visit(And and) {
        Expression left = and.getLeft();
        Expression right = and.getRight();
        return new Not(new Not(new And(left, right)));
    }

    @Override
    public Expression visit(Or or) {
        Expression left = or.getLeft();
        Expression right = or.getRight();
        return new Not(new Not(new Or(left, right)));
    }

    @Override
    public Expression visit(Equivalence equivalence) {
        Expression left = equivalence.getLeft();
        Expression right = equivalence.getRight();
        return new Not(new Not(new Equivalence(left, right)));    }

    @Override
    public Expression visit(Imply imply) {
        Expression left = imply.getLeft();
        Expression right = imply.getRight();
        return new Not(new Not(new Imply(left, right)));    }

    @Override
    public Expression visit(Not not) {
        Expression insideExpr = not.getExpression();
        if (insideExpr instanceof Not) {
            Not notE = (Not) insideExpr;
            // Apply De Morgan's law
            Expression expr = notE.getExpression();
                return expr;
        } else {
            // Keep the expression unchanged
            return not;
        }
    }

    @Override
    public Expression visit(Variable variable) {

        return new Not(new Not(variable));
    }

    public Expression visit(Value value){
        return new Not(new Not(value));
    }

    public boolean canApply(Expression expr) {
        boolean result = true;
        return result;
    }
}
