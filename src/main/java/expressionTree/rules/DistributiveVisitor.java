package expressionTree.rules;

import expressionTree.*;

import java.util.List;
import java.util.Objects;

/**
 * A visitor class that implements Distributive law on an expression tree.
 * This class implements the 'ExpressionVisitor' interface, allowing it to
 * traverse an expression tree and manipulate the expressions contained within.
 * <p>
 * Distributive law states that:
 * A and (B or C) = (A and B) or (A and C).
 * A or (B and C) = (A or B) and (A or C).
 * <p>
 * When visiting an 'Or' or 'And' expression, this visitor applies Distributive law.
 * For all other expression types, the expression is returned unchanged.
 * <p>
 * This visitor does not modify expressions of type 'Equivalence', 'Imply', 'Not', or 'Variable'.
 */
public class DistributiveVisitor implements ExpressionVisitor {
    public static void main(String[] args) {
        List<String> tokens = Tokenizer.tokenize("((A|B)&(A|C))");
        List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
        Expression exp = Parser.Evaluator(Objects.requireNonNull(shunting));
        DistributiveVisitor visitor = new DistributiveVisitor();
        System.out.println(exp.accept(visitor));
    }
    @Override
    public Expression visit(And and) {
        Expression left = and.getLeft().accept(this);
        Expression right = and.getRight().accept(this);

        if (left instanceof Variable && right instanceof Or) {
            Or or = (Or) right;
            Expression orLeft = or.getLeft().accept(this);
            Expression orRight = or.getRight().accept(this);
            return new Or(new And(left, orLeft), new And(left, orRight));
        } else if (left instanceof Or && right instanceof Variable){
            Or or = (Or) left;
            Expression orLeft = or.getLeft().accept(this);
            Expression orRight = or.getRight().accept(this);
            return new Or(new And(orLeft, right), new And(orRight, right));
        } else if (left instanceof Or && right instanceof Or){
            Or orL = (Or) left;
            Expression orLLeft = orL.getLeft().accept(this);
            Expression orLRight = orL.getRight().accept(this);
            Or orR = (Or) right;
            Expression orRLeft = orR.getLeft().accept(this);
            Expression orRRight = orR.getRight().accept(this);
            if(orLLeft.equals(orRLeft) ){
                return new Or(orLLeft, new And(orLRight, orRRight));
            }else if (orLRight.equals(orRRight)){
                return new Or(new And(orLLeft, orRLeft), orLRight);
            }else{
                return new And(orL, orR);
            }
        }else {
            return new And(left, right);
        }
    }

    @Override
    public Expression visit(Or or) {
        Expression left = or.getLeft().accept(this);
        Expression right = or.getRight().accept(this);

        if (left instanceof Variable && right instanceof And) {
            And and = (And) right;
            Expression andLeft = and.getLeft().accept(this);
            Expression andRight = and.getRight().accept(this);
            return new And(new Or(left, andLeft), new Or(left, andRight));
        } else if (left instanceof And && right instanceof Variable){
            And and = (And) left;
            Expression andLeft = and.getLeft().accept(this);
            Expression andRight = and.getRight().accept(this);
            return new And(new Or(andLeft, right), new Or(andRight, right));
        }else if (left instanceof And && right instanceof And){
            And andL = (And) left;
            Expression andLLeft = andL.getLeft().accept(this);
            Expression andLRight = andL.getRight().accept(this);
            And andR = (And) right;
            Expression andRLeft = andR.getLeft().accept(this);
            Expression andRRight = andR.getRight().accept(this);
            if(andLLeft.equals(andRLeft) ){
                return new And(andLLeft, new Or(andLRight, andRRight));
            }else if (andLRight.equals(andRRight)){
                return new And(new Or(andLLeft, andRLeft), andLRight);
            }else{
                return new And(andL, andR);
            }
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

            if (left instanceof Variable && right instanceof Or) {
                result = true;
            } else if (left instanceof Or && right instanceof Variable){
                result = true;
            }else if (left instanceof Or && right instanceof Or) {
                Or orL = (Or) left;
                Expression orLLeft = orL.getLeft().accept(this);
                Expression orLRight = orL.getRight().accept(this);
                Or orR = (Or) right;
                Expression orRLeft = orR.getLeft().accept(this);
                Expression orRRight = orR.getRight().accept(this);
                if (orLLeft.equals(orRLeft)) {
                    result = true;
                } else if (orLRight.equals(orRRight)) {
                    result = true;
                }
            }
        }else if(expr instanceof Or){
            Or or = (Or) expr;
            Expression left = or.getLeft().accept(this);
            Expression right = or.getRight().accept(this);

            if (left instanceof Variable && right instanceof And) {
                result = true;
            } else if (left instanceof And && right instanceof Variable){
                result = true;
            } else if (left instanceof And && right instanceof And) {
                And andL = (And) left;
                Expression andLLeft = andL.getLeft().accept(this);
                Expression andLRight = andL.getRight().accept(this);
                And andR = (And) right;
                Expression andRLeft = andR.getLeft().accept(this);
                Expression andRRight = andR.getRight().accept(this);
                if (andLLeft.equals(andRLeft)) {
                    result = true;
                } else if (andLRight.equals(andRRight)) {
                    result = true;
                }
            }
        }
        return result;
    }
}
