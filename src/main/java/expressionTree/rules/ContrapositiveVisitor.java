package expressionTree.rules;

import expressionTree.*;
import java.util.List;
import java.util.Objects;

/**
 * A visitor class that implements Contrapositive law on an expression tree.
 * This class implements the 'ExpressionVisitor' interface, allowing it to
 * traverse an expression tree and manipulate the expressions contained within.
 * <p>
 * Contrapositive law states that:
 * p imply q == (not p) imply (not q)
 * <p>
 * When visiting an 'Imply' expression, this visitor applies Contrapositive law.
 * For all other expression types, the expression is returned unchanged.
 * <p>
 * This visitor does not modify expressions of type 'Equivalence', 'Not', 'And', 'Or', or 'Variable'.
 */
public class ContrapositiveVisitor implements ExpressionVisitor {

    public static void main(String[] args) {
        List<String> tokens = Tokenizer.tokenize("A=>B");
        List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
        Expression exp = Parser.Evaluator(Objects.requireNonNull(shunting));
        ContrapositiveVisitor visitor = new ContrapositiveVisitor();
        System.out.println(exp.accept(visitor));
    }

    @Override
    public Expression visit(And and) {
        Expression left = and.getLeft();
        Expression right = and.getRight();
        return new And(left, right);
    }

    @Override
    public Expression visit(Or or) {
        Expression left = or.getLeft();
        Expression right = or.getRight();
        return new Or(left, right);
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
        if(left instanceof Not notL && right instanceof Not notR) {
            Expression leftNotEx = notL.getExpression();
            Expression rightNotEx = notR.getExpression();
            return new Imply(leftNotEx, rightNotEx);
        }else if(!(left instanceof Not) && !(right instanceof Not)){
            return new Imply(new Not(left), new Not(right));
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

    public Expression visit(Value value){
        return value;
    }

    public boolean canApply(Expression expr) {
        boolean result = false;
        if(expr instanceof Imply){
            result = true;
        }else if(expr instanceof Or or){
            Expression left = or.getLeft();
            if(left instanceof Not){
                result = true;
            }
        }
        return result;
    }
}