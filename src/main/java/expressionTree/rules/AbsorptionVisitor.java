package expressionTree.rules;

import expressionTree.*;
import java.util.List;
import java.util.Objects;

/**
 * A visitor class that implements Absorption law on an expression tree.
 * This class implements the 'ExpressionVisitor' interface, allowing it to
 * traverse an expression tree and manipulate the expressions contained within.
 * <p>
 * Absorption law states that:
 * A and (A or B) = A = A or (A and B).
 * <p>
 * When visiting an 'Or' or 'And' expression, this visitor applies Commutative law.
 * For all other expression types, the expression is returned unchanged.
 * <p>
 * This visitor does not modify expressions of type 'Equivalence', 'Imply', 'Not', or 'Variable'.
 */
public class AbsorptionVisitor implements ExpressionVisitor {
    public static void main(String[] args) {
        List<String> tokens = Tokenizer.tokenize("A&(A|B)");
        List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
        Expression exp = Parser.Evaluator(Objects.requireNonNull(shunting));
        AbsorptionVisitor visitor = new AbsorptionVisitor();
        System.out.println(exp.accept(visitor));
    }
    @Override
    public Expression visit(And and) {
        Expression left = and.getLeft().accept(this);
        Expression right = and.getRight().accept(this);

        // A and (A or B) = A
        if (left instanceof Variable && right instanceof Or) {
            Or or = (Or) right;
            Expression orLeft = or.getLeft().accept(this);
            Expression orRight = or.getRight().accept(this);
            if (orLeft.equals(left)) {
                return left;
            }
        }
        if (right instanceof Variable && left instanceof Or){
            Or or = (Or) left;
            Expression orLeft = or.getLeft().accept(this);
            Expression orRight = or.getRight().accept(this);
            if (orLeft.equals(right)) {
                return right;
            }
        }
        return new And (left, right);
    }

    @Override
    public Expression visit(Or or) {
        Expression left = or.getLeft().accept(this);
        Expression right = or.getRight().accept(this);

        // A OR (A AND B) = A
        if (left instanceof Variable && right instanceof And) {
            And and = (And) right;
            Expression andLeft = and.getLeft().accept(this);
            Expression andRight = and.getRight().accept(this);
            if (andLeft.equals(left)) {
                return left;
            }
        }

        // (A AND B) OR A = A
        if (right instanceof Variable && left instanceof And) {
            And and = (And) left;
            Expression andLeft = and.getLeft().accept(this);
            Expression andRight = and.getRight().accept(this);
            if (andLeft.equals(right)) {
                return right;
            }
        }

        // Otherwise, keep the expression unchanged
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
        if(expr instanceof And){
            And and = (And) expr;
            Expression left = and.getLeft().accept(this);
            Expression right = and.getRight().accept(this);
            // A and (A or B) = A
            if (left instanceof Variable && right instanceof Or) {
                Or or = (Or) right;
                Expression orLeft = or.getLeft().accept(this);
                Expression orRight = or.getRight().accept(this);
                if (orLeft.equals(left)) {
                    result = true;
                }
            }
            if (right instanceof Variable && left instanceof Or){
                Or or = (Or) left;
                Expression orLeft = or.getLeft().accept(this);
                Expression orRight = or.getRight().accept(this);
                if (orLeft.equals(right)) {
                    result = true;
                }
            }
        }else if(expr instanceof Or){
            Or or = (Or) expr;
            Expression left = or.getLeft().accept(this);
            Expression right = or.getRight().accept(this);
            // A OR (A AND B) = A
            if (left instanceof Variable && right instanceof And) {
                And and = (And) right;
                Expression andLeft = and.getLeft().accept(this);
                Expression andRight = and.getRight().accept(this);
                if (andLeft.equals(left)) {
                    result = true;
                }
            }
            // (A AND B) OR A = A
            if (right instanceof Variable && left instanceof And) {
                And and = (And) left;
                Expression andLeft = and.getLeft().accept(this);
                Expression andRight = and.getRight().accept(this);
                if (andLeft.equals(right)) {
                    result = true;
                }
            }
        }
        return result;
    }
}
