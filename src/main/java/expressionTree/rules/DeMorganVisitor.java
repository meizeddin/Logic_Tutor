package expressionTree.rules;

import expressionTree.*;
import java.util.List;
import java.util.Objects;

/**
 * A visitor class that implements De Morgan's law on an expression tree.
 * This class implements the 'ExpressionVisitor' interface, allowing it to
 * traverse an expression tree and manipulate the expressions contained within.
 * <p>
 * De Morgan's law states that:
 * !(A and B) = !A or !B
 * !(A or B) = !A and !B
 * <p>
 * When visiting a 'Not' expression, this visitor applies De Morgan's law if the
 * expression contained within the 'Not' node is either an 'And' or 'Or' expression.
 * For all other expression types, the expression is returned unchanged.
 * <p>
 * This visitor does not modify expressions of type 'Equivalence', 'Imply', or 'Variable'.
 */
public class DeMorganVisitor implements ExpressionVisitor {

    public static void main(String[] args) {
        List<String> tokens = Tokenizer.tokenize("!(A|B)");
        List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
        Expression exp = Parser.Evaluator(Objects.requireNonNull(shunting));
        DeMorganVisitor visitor = new DeMorganVisitor();
        System.out.println(exp.accept(visitor));
    }

    @Override
    public Expression visit(And and) {
        Expression left = and.getLeft().accept(this);
        Expression right = and.getRight().accept(this);
        if (right instanceof Not && left instanceof Not) {
            Not notR = (Not) right;
            Not notL = (Not) left;
            Or or = new Or(notL.getExpression(), notR.getExpression());
            return new Not(or);
        }else{
            return new And(left, right);
        }
    }

    @Override
    public Expression visit(Or or) {
        Expression left = or.getLeft().accept(this);
        Expression right = or.getRight().accept(this);
        if (right instanceof Not && left instanceof Not) {
            Not notR = (Not) right;
            Not notL = (Not) left;
            And and = new And(notL.getExpression(), notR.getExpression());
            return new Not(and);
        }else{
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
        Expression expr = not.getExpression();
        if (expr instanceof And and) {
            // Apply De Morgan's law
            Expression left = and.getLeft().accept(this);
            Expression right = and.getRight().accept(this);
            return new Or(new Not(left), new Not(right));
        } else if (expr instanceof Or or) {
            // Apply De Morgan's law
            Expression left = or.getLeft().accept(this);
            Expression right = or.getRight().accept(this);
            return new And(new Not(left), new Not(right));
        } else {
            // Keep the expression unchanged
            return not;
        }
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
        if(expr instanceof And){
            And and = (And) expr;
            Expression left = and.getLeft().accept(this);
            Expression right = and.getRight().accept(this);
            if (right instanceof Not && left instanceof Not) {
                result = true;
            }
        }else if(expr instanceof Or){
            Or or = (Or) expr;
            Expression left = or.getLeft().accept(this);
            Expression right = or.getRight().accept(this);
            if (right instanceof Not && left instanceof Not) {
                result = true;
            }
        }else if(expr instanceof Not){
            Not not = (Not) expr;
            Expression insideExpr = not.getExpression();
            if (insideExpr instanceof And ) {
                result = true;
            } else if (insideExpr instanceof Or) {
                result = true;
            }
        }
        return result;
    }
}
