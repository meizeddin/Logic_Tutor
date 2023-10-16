package expressionTree.rules;

import expressionTree.*;

import java.util.List;
import java.util.Objects;

public class BiConditionalVisitor implements ExpressionVisitor {

    public static void main(String[] args) {
        List<String> tokens = Tokenizer.tokenize("((A=>B)&(B=>A))");
        List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
        Expression exp = Parser.Evaluator(Objects.requireNonNull(shunting));
        BiConditionalVisitor visitor = new BiConditionalVisitor();
        System.out.println(exp.accept(visitor));
    }

    @Override
    public Expression visit(And and) {
        Expression left = and.getLeft();
        Expression right = and.getRight();
        if (left instanceof Imply && right instanceof Imply){
            Expression leftImplyLeft = ((Imply) left).getLeft();
            Expression leftImplyRight = ((Imply) left).getRight();
            return new Equivalence(leftImplyLeft, leftImplyRight);
        }else {
            return new And(left, right);
        }
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
        return new And(new Imply(left, right), new Imply(right, left));
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

    public Expression visit(Value value){
        return value;
    }

    public boolean canApply(Expression expr) {
        boolean result = false;
        if(expr instanceof Equivalence){
            result = true;
        }else if(expr instanceof And and){
            Expression left = and.getLeft();
            Expression right= and.getRight();
            if(left instanceof Imply && right instanceof Imply){
                result = true;
            }
        }
        return result;
    }
}