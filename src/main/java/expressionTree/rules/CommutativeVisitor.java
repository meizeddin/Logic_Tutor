package expressionTree.rules;

import expressionTree.*;
import java.util.List;
import java.util.Objects;


/**
 * A visitor class that implements Commutative law on an expression tree.
 * This class implements the `ExpressionVisitor` interface, allowing it to
 * traverse an expression tree and manipulate the expressions contained within.
 * <p>
 * Commutative law states that:
 * A and B = B and A.
 * A or B = B or A.
 * <p>
 * When visiting an 'Or' or 'And' expression, this visitor applies Commutative law.
 * For all other expression types, the expression is returned unchanged.
 * <p>
 * This visitor does not modify expressions of type 'Equivalence', 'Imply', 'Not', or 'Variable'.
 */
public class CommutativeVisitor implements ExpressionVisitor {
    public static void main(String[] args) {
        List<String> tokens = Tokenizer.tokenize("P<=>Q");
        List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
        Expression exp = Parser.Evaluator(Objects.requireNonNull(shunting));
        CommutativeVisitor visitor = new CommutativeVisitor();
        System.out.println(exp.accept(visitor));
    }
    @Override
    public Expression visit(And and) {
        Expression left = and.getLeft();
        Expression right = and.getRight();
        return new And(right, left);
    }

    @Override
    public Expression visit(Or or) {
        Expression left = or.getLeft();
        Expression right = or.getRight();
        return new Or(right, left);
    }

    @Override
    public Expression visit(Equivalence equivalence) {
        Expression left = equivalence.getLeft();
        Expression right = equivalence.getRight();
        return new Equivalence(right, left);
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
        if(expr instanceof And){
            result = true;
        }else if(expr instanceof Or){
            result = true;
        }else if(expr instanceof Equivalence){
            result = true;
        }
        return result;
    }
}