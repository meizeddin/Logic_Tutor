package expressionTree.rules;

import expressionTree.*;
import java.util.List;
import java.util.Objects;

/**
 * A visitor class that implements Idempotence law on an expression tree.
 * This class implements the `ExpressionVisitor` interface, allowing it to
 * traverse an expression tree and manipulate the expressions contained within.
 * <p>
 * Idempotence law states that:
 * A and A = A = A or A
 * <p>
 * When visiting an 'Or' or 'And' expression, this visitor applies Idempotence law if the
 * expression left and right nodes are equal.
 * For all other expression types, the expression is returned unchanged.
 * <p>
 * This visitor does not modify expressions of type 'Equivalence', 'Imply', 'Not', or 'Variable'.
 */
public class IdempotenceVisitor implements ExpressionVisitor {

    public static void main(String[] args) {
        List<String> tokens = Tokenizer.tokenize("(A&A)&(A&A)");
        List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
        Expression exp = Parser.Evaluator(Objects.requireNonNull(shunting));
        IdempotenceVisitor visitor = new IdempotenceVisitor();
        System.out.println(exp.accept(visitor));
    }
    @Override
    public Expression visit(And and) {
        Expression left = and.getLeft().accept(this);
        Expression right = and.getRight().accept(this);
        // If both the left and right operands are equal, return the single operand
        if (left.equals(right)) {
            return left;
        } else {
            return new And(left, right);
        }
    }

    @Override
    public Expression visit(Or or) {
        Expression left = or.getLeft().accept(this);
        Expression right = or.getRight().accept(this);
        // If both the left and right operands are equal, return the single operand
        if (left.equals(right)) {
            return left;
        } else {
            return new Or(left, right);
        }
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
        if(expr instanceof And and){
            Expression left = and.getLeft().accept(this);
            Expression right = and.getRight().accept(this);
            if (left.equals(right)) {
                result = true;
            }
        }else if(expr instanceof Or or){
            Expression left = or.getLeft().accept(this);
            Expression right = or.getRight().accept(this);
            if (left.equals(right)) {
                result = true;
            }
        }
        return result;
    }
}
