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
        Expression left = and.getLeft();
        Expression right = and.getRight();

        if ((left instanceof Variable || left instanceof Not) && right instanceof Or) {
            Or or = (Or) right;
            Expression orLeft = or.getLeft();
            Expression orRight = or.getRight();
            return new Or(new And(left, orLeft), new And(left, orRight));
        } else if (left instanceof Or && (right instanceof Variable || right instanceof Not)){
            Or or = (Or) left;
            Expression orLeft = or.getLeft();
            Expression orRight = or.getRight();
            return new Or(new And(orLeft, right), new And(orRight, right));
        } else if (left instanceof Or orL && right instanceof Or orR){
            Expression orLLeft = orL.getLeft();
            Expression orLRight = orL.getRight();
            Expression orRLeft = orR.getLeft();
            Expression orRRight = orR.getRight();
            if(orLLeft.toString().equals(orRLeft.toString()) ){
                return new Or(orLLeft, new And(orLRight, orRRight));
            }else if (orLRight.toString().equals(orRRight.toString())){
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
        Expression left = or.getLeft();
        Expression right = or.getRight();

        if ((left instanceof Variable || left instanceof Not) && right instanceof And) {
            And and = (And) right;
            Expression andLeft = and.getLeft();
            Expression andRight = and.getRight();
            return new And(new Or(left, andLeft), new Or(left, andRight));
        } else if (left instanceof And && (right instanceof Variable || right instanceof Not)){
            And and = (And) left;
            Expression andLeft = and.getLeft();
            Expression andRight = and.getRight();
            return new And(new Or(andLeft, right), new Or(andRight, right));
        }else if (left instanceof And andL && right instanceof And andR){
            Expression andLLeft = andL.getLeft();
            Expression andLRight = andL.getRight();
            Expression andRLeft = andR.getLeft();
            Expression andRRight = andR.getRight();
            if(andLLeft.toString().equals(andRLeft.toString()) ){
                return new And(andLLeft, new Or(andLRight, andRRight));
            }else if (andLRight.toString().equals(andRRight.toString())){
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

            if ((left instanceof Variable || left instanceof Not) && right instanceof Or) {
                result = true;
            } else if (left instanceof Or && (right instanceof Variable || right instanceof Not)){
                result = true;
            }else if (left instanceof Or orL && right instanceof Or orR) {
                Expression orLLeft = orL.getLeft();
                Expression orLRight = orL.getRight();
                Expression orRLeft = orR.getLeft();
                Expression orRRight = orR.getRight();
                if (orLLeft.toString().equals(orRLeft.toString())) {
                    result = true;
                } else if (orLRight.toString().equals(orRRight.toString())) {
                    result = true;
                }
            }
        }else if(expr instanceof Or or){
            Expression left = or.getLeft();
            Expression right = or.getRight();

            if ((left instanceof Variable || left instanceof Not) && right instanceof And) {
                result = true;
            } else if (left instanceof And && (right instanceof Variable || right instanceof Not)){
                result = true;
            } else if (left instanceof And andL && right instanceof And andR) {
                Expression andLLeft = andL.getLeft();
                Expression andLRight = andL.getRight();
                Expression andRLeft = andR.getLeft();
                Expression andRRight = andR.getRight();
                if (andLLeft.toString().equals(andRLeft.toString())) {
                    result = true;
                } else if (andLRight.toString().equals(andRRight.toString())) {
                    result = true;
                }
            }
        }
        return result;
    }
}
