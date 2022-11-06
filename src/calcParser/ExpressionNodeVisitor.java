package calcParser;

/**
 * An interface for the visitor design pattern.
 * 
 * Expression nodes can be visited by ExpressionNodeVisitor by calling their
 * accept methods. The expression nodes, in turn, call the appropriate visit
 * method of the expression node visitor.
 */
public interface ExpressionNodeVisitor
{
  /** Visit a VariableExpressionNode */
  public void visit(VariableExpressionNode node);

  /**  Visit a ConstantExpressionNode */
  public void visit(DisjunctionExpressionNode node);

  /**  Visit a AdditionExpressionNode */
  public void visit(ConjunctionExpressionNode node);

  /**  Visit a MultiplicationExpressionNode */
  public void visit(EquivalenceExpressionNode node);

  /**  Visit a ExponentiationExpressionNode */
  public void visit(ImplicationExpressionNode node);
  
  public void visit(NegationExpressionNode node);
  
  public void visit(IdempotentLawExpressionNode node);

}
