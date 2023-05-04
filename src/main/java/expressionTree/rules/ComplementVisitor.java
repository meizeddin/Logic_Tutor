package expressionTree.rules;

import expressionTree.*;
import java.util.List;
import java.util.Objects;

/**
 * A visitor class that implements Complement laws on an expression tree.
 * This class implements the `ExpressionVisitor` interface, allowing it to
 * traverse an expression tree and manipulate the expressions contained within.
 * <p>
 * Complement laws state that:
 * P & ¬P ≡ F
 * P | ¬P ≡ T
 * ¬T ≡ F
 * ¬F ≡ T
 * <p>
 * When visiting an 'Or', 'And' or 'Not' expression, this visitor applies Complement laws if they follow the rules.
 * For all other expression types, the expression is returned unchanged.
 * <p>
 * This visitor does not modify expressions of type 'Equivalence', 'Imply', or 'Variable'.
 */
public class ComplementVisitor implements ExpressionVisitor {

    public static void main(String[] args) {
        List<String> tokens = Tokenizer.tokenize("A&!(A)");
        List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
        Expression exp = Parser.Evaluator(Objects.requireNonNull(shunting));
        ComplementVisitor visitor = new ComplementVisitor();
        System.out.println(visitor.canApply(exp));
        System.out.println(exp.accept(visitor));
    }
    @Override
    public Expression visit(And and) {
        Expression left = and.getLeft();
        Expression right = and.getRight();
        // If both the left and right operands are equal, return the single operand
        if (right instanceof Not) {
            Not not = (Not) right;
            if(not.getExpression().toString().equals(left.toString())){
                return new False();
            } else {
                return new And(left, right);
            }
        } else if (left instanceof Not){
            Not not = (Not) left;
            if(not.getExpression().toString().equals(right.toString())){
                return new False();
            } else {
                return new And(left, right);
            }
        }else {
            return new And(left, right);
        }
    }



    @Override
    public Expression visit(Or or) {
        Expression left = or.getLeft();
        Expression right = or.getRight();
        // If both the left and right operands are equal, return the single operand
        if (right instanceof Not) {
            Not not = (Not) right;
            if(not.getExpression().toString().equals(left.toString())){
                return new True();
            } else {
                return new Or(left, right);
            }
        } else if (left instanceof Not){
            Not not = (Not) left;
            if(not.getExpression().toString().equals(right.toString())){
                return new True();
            } else {
                return new Or(left, right);
            }
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
        return new Imply(left, right);
    }

    @Override
    public Expression visit(Not not) {
        Expression expr = not.getExpression();
        if (expr instanceof True){
            return new False();
        }else if (expr instanceof False){
            return new True();
        }
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
            if (right instanceof Not) {
                Not not = (Not) right;
                if (not.getExpression().toString().equals(left.toString())) {
                    result = true;
                }
            }else if (left instanceof Not){
                Not not = (Not) left;
                if (not.getExpression().toString().equals(right.toString())) {
                    result = true;
                }
            }
        }else if(expr instanceof Or or){
            Expression left = or.getLeft();
            Expression right = or.getRight();
            if (right instanceof Not) {
                Not not = (Not) right;
                if (not.getExpression().toString().equals(left.toString())) {
                    result = true;
                }
            }else if (left instanceof Not){
                Not not = (Not) left;
                if (not.getExpression().toString().equals(right.toString())) {
                    result = true;
                }
            }
        }else if (expr instanceof Not){
            Not not = (Not) expr;
            Expression expr1 = not.getExpression();
            if (expr1 instanceof True){
                result = true;
            }else if (expr1 instanceof False){
                result = true;
            }
        }
        return result;
    }
}