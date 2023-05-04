package expressionTree.rules;

import expressionTree.*;

import java.util.List;
import java.util.Objects;

/**
 * A visitor class that implements Identity laws on an expression tree.
 * This class implements the `ExpressionVisitor` interface, allowing it to
 * traverse an expression tree and manipulate the expressions contained within.
 * <p>
 * Identity laws state that:
 * A Or F = A,
 * A And T = A,
 * T Imply A = A
 * <p>
 * When visiting an 'Or', 'And', or 'Imply' expression, this visitor applies Identity law if the
 * expression left or right nodes are equal to True for 'And' and 'Or' and left node equal to T for 'Imply'.
 * For all other expression types, the expression is returned unchanged.
 * <p>
 * This visitor does not modify expressions of type 'Equivalence', 'Not', or 'Variable'.
 */
public class IdentityVisitor implements ExpressionVisitor {

    public static void main(String[] args) {
        List<String> tokens = Tokenizer.tokenize("(T=>A)");
        List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
        Expression exp = Parser.Evaluator(Objects.requireNonNull(shunting));
        IdentityVisitor visitor = new IdentityVisitor();
        System.out.println(exp.accept(visitor));
    }
    @Override
    public Expression visit(And and) {
        Expression left = and.getLeft();
        Expression right = and.getRight();
        // If either the left or right node = t then return the variable
        if (left instanceof True) {
            return right;
        }else if(right instanceof True){
            return left;
        }else {
            return new And(left, right);
        }
    }

    @Override
    public Expression visit(Or or) {
        Expression left = or.getLeft();
        Expression right = or.getRight();
        // If either the left or right node = t then return the variable
        if (left instanceof False) {
            return right;
        }else if(right instanceof False){
            return left;
        }else {
            return new Or(left, right);
        }
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
        if (left instanceof True) {
            return right;
        }else {
            return new Imply(left, right);
        }
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
            if (left instanceof True) {
                result = true;
            }else if(right instanceof True){
                result = true;
            }
        }else if(expr instanceof Or or){
            Expression left = or.getLeft();
            Expression right = or.getRight();
            if (left instanceof False) {
                result = true;
            }else if(right instanceof False){
                result = true;
            }
        }else if(expr instanceof Imply imply){
            Expression left = imply.getLeft();
            if (left instanceof True) {
                result = true;
            }
        }
        return result;
    }
}
