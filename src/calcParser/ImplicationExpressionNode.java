package calcParser;

/**
 * An ExpressionNode that handles exponentiation. The node holds
 * a base and an exponent and calulates base^exponent 
 * 
 */
public class ImplicationExpressionNode implements ExpressionNode
{
  /** the node containing the base */
  private ExpressionNode left;
  /** the node containing the exponent */
  private ExpressionNode right;

  /**
   * Construct the ExponentiationExpressionNode with base and exponent
   * @param base the node containing the base
   * @param exponent the node containing the exponent
   */
  public ImplicationExpressionNode(ExpressionNode left, ExpressionNode right)
  {
    this.left = left;
    this.right = right;
  }

  /**
   * Returns the type of the node, in this case ExpressionNode.EXPONENTIATION_NODE
   */
  public int getType()
  {
    return ExpressionNode.IMPLICATION_NODE;
  }
  
  /**
   * Returns the value of the sub-expression that is rooted at this node.
   * 
   * Calculates base^exponent
   */
  public boolean getValue()
  {
    return (!(left.getValue()) | right.getValue());
  }

  /**
   * Implementation of the visitor design pattern.
   * 
   * Calls visit on the visitor and then passes the visitor on to the accept
   * method of the base and the exponent.
   * 
   * @param visitor
   *          the visitor
   */
  public void accept(ExpressionNodeVisitor visitor)
  {
    visitor.visit(this);
    left.accept(visitor);
    right.accept(visitor);
  }
}
