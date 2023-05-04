package expressionTree.rules;

import expressionTree.*;
import java.util.List;
import java.util.Objects;

/**
 * A visitor class that implements Associative law on an expression tree.
 * This class implements the 'ExpressionVisitor' interface, allowing it to
 * traverse an expression tree and manipulate the expressions contained within.
 * <p>
 * Associative law states that:
 * A and (B and C) = (A and B) and C.
 * A or (B or C) = (A or B) or C.
 * <p>
 * When visiting an 'Or' or 'And' expression, this visitor applies Commutative law.
 * For all other expression types, the expression is returned unchanged.
 * <p>
 * This visitor does not modify expressions of type 'Equivalence', 'Imply', 'Not', or 'Variable'.
 */
public class AssociativeVisitor implements ExpressionVisitor {
    public static void main(String[] args) {
        List<String> tokens = Tokenizer.tokenize("A<=>(B<=>C)");
        List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
        Expression exp = Parser.Evaluator(Objects.requireNonNull(shunting));
        AssociativeVisitor visitor = new AssociativeVisitor();
        System.out.println(exp.accept(visitor));
    }
    @Override
    public Expression visit(And and) {
        Expression left = and.getLeft();
        Expression right = and.getRight();

        if (left instanceof And leftAnd) {
            return new And(leftAnd.getLeft(), new And(leftAnd.getRight(), right));
        } else if (right instanceof And rightAnd){
            return new And(new And(left, rightAnd.getLeft()), rightAnd.getRight());
        }else {
            return new And(left, right);
        }
    }

    @Override
    public Expression visit(Or or) {
        Expression left = or.getLeft();
        Expression right = or.getRight();

        if (left instanceof Or leftOr) {
            return new Or(leftOr.getLeft(), new Or(leftOr.getRight(), right));
        } else if (right instanceof Or rightOr){
            return new Or(new Or(left, rightOr.getLeft()), rightOr.getRight());
        }else {
            return new Or(left, right);
        }
    }

    @Override
    public Expression visit(Equivalence equivalence) {
        Expression left = equivalence.getLeft();
        Expression right = equivalence.getRight();
        if (left instanceof Equivalence leftEquiv) {
            return new Equivalence(leftEquiv.getLeft(), new Equivalence(leftEquiv.getRight(), right));
        } else if (right instanceof Equivalence rightEquiv){
            return new Equivalence(new Equivalence(left, rightEquiv.getLeft()), rightEquiv.getRight());
        }else {
            return new Equivalence(left, right);
        }
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
        boolean result = false;
        if(expr instanceof And and){
            Expression left = and.getLeft();
            Expression right = and.getRight();

            if (left instanceof And) {
                result = true;
            } else if (right instanceof And){
                result = true;
            }
        }else if(expr instanceof Or or){
            Expression left = or.getLeft();
            Expression right = or.getRight();

            if (left instanceof Or) {
                result = true;
            } else if (right instanceof Or){
                result = true;
            }
        }else if(expr instanceof Equivalence equivalence){
            Expression left = equivalence.getLeft();
            Expression right = equivalence.getRight();
            if (left instanceof Equivalence) {
                result = true;
            } else if (right instanceof Equivalence){
                result = true;
            }
        }
        return result;
    }
}
