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
        Expression left = and.getLeft().accept(this);
        Expression right = and.getRight().accept(this);
        // If either the left or right node = t then return the variable
        if (left.toString().equals(Value.getTrue().toString())) {
            return right;
        }else if(right.toString().equals(Value.getTrue().toString())){
            return left;
        }else {
            return new And(left, right);
        }
    }

    @Override
    public Expression visit(Or or) {
        Expression left = or.getLeft().accept(this);
        Expression right = or.getRight().accept(this);
        // If either the left or right node = t then return the variable
        if (left.toString().equals(Value.getFalse().toString())) {
            return right;
        }else if(right.toString().equals(Value.getFalse().toString())){
            return left;
        }else {
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
        if (left.toString().equals(Value.getTrue().toString())) {
            return right;
        }else {
            return new Imply(left, right);
        }
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
            if (left.toString().equals(Value.getTrue().toString())) {
                result = true;
            }else if(right.toString().equals(Value.getTrue().toString())){
                result = true;
            }
        }else if(expr instanceof Or){
            Or or = (Or) expr;
            Expression left = or.getLeft().accept(this);
            Expression right = or.getRight().accept(this);
            if (left.toString().equals(Value.getFalse().toString())) {
                result = true;
            }else if(right.toString().equals(Value.getFalse().toString())){
                result = true;
            }
        }else if(expr instanceof Imply){
            Imply imply = (Imply) expr;
            Expression left = imply.getLeft().accept(this);
            Expression right = imply.getRight().accept(this);
            if (left.toString().equals(Value.getTrue().toString())) {
                result = true;
            }
        }
        return result;
    }
}
