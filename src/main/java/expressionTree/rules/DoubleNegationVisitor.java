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
 * <p>
 * When visiting a 'Not' expression, this visitor applies Double Negation law if the
 * expression contained within the 'Not' node is another 'Not' expression.
 * For all other expression types, the expression is returned unchanged.
 * <p>
 * This visitor does not modify expressions of type 'Equivalence', 'Imply', 'And', 'Or', or 'Variable'.
 */
public class DoubleNegationVisitor implements ExpressionVisitor {

    public static void main(String[] args) {
        List<String> tokens = Tokenizer.tokenize("!(!(!A))");
        List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
        Expression exp = Parser.Evaluator(Objects.requireNonNull(shunting));
        DoubleNegationVisitor visitor = new DoubleNegationVisitor();
        System.out.println(exp.accept(visitor));
    }

    @Override
    public Expression visit(And and) {
        Expression left = and.getLeft().accept(this);
        Expression right = and.getRight().accept(this);
        return new And(left, right);
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
        return new Imply(left, right);
    }

    @Override
    public Expression visit(Not not) {
        Expression expr = not.getExpression();
        if (expr instanceof Not) {
            Not notE = (Not) expr;
            // Apply De Morgan's law
            Expression left = notE.getExpression().accept(this);
            return left;
        } else {
            // Keep the expression unchanged
            return not;
        }
    }

    @Override
    public Expression visit(Variable variable) {
        return variable;
    }

    public Expression visit(Value value){
        return value;
    }

    public boolean canApply(Expression expr) {
        boolean result = false;
        if(expr instanceof Not){
            Not not = (Not) expr;
            Expression exprInside = not.getExpression();
            if (exprInside instanceof Not) {
                result =true;
            }
        }
        return result;
    }
}
