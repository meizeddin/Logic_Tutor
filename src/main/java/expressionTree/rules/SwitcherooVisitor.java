package expressionTree.rules;

import expressionTree.*;

import java.util.List;
import java.util.Objects;

/**
 * A visitor class that implements Switcheroo law on an expression tree.
 * This class implements the 'ExpressionVisitor' interface, allowing it to
 * traverse an expression tree and manipulate the expressions contained within.
 * <p>
 * Switcheroo law states that:
 * p imply q == (not p) or q
 * <p>
 * When visiting an 'Imply' expression, this visitor applies Switcheroo law.
 * For all other expression types, the expression is returned unchanged.
 * <p>
 * This visitor does not modify expressions of type 'Equivalence', 'Not', 'And', 'Or', or 'Variable'.
 */
public class SwitcherooVisitor implements ExpressionVisitor {

    public static void main(String[] args) {
        List<String> tokens = Tokenizer.tokenize("(!((!(P)|Q))|R)");
        List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
        Expression exp = Parser.Evaluator(Objects.requireNonNull(shunting));
        SwitcherooVisitor visitor = new SwitcherooVisitor();
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
        if(left instanceof Not){
            Not not = (Not) left;
            return new Imply(not.getExpression(), right);
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
        return new Or(new Not(left), right);
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
        Boolean result = false;
        if(expr instanceof Imply){
            result = true;
        }else if(expr instanceof Or){
            Or or = (Or) expr;
            Expression left = or.getLeft();
            Expression right = or.getRight();
            if(left instanceof Not){
                Not not = (Not) left;
                result = true;
            }
        }
        return result;
    }
}