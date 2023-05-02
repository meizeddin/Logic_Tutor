package calcParser;

/**
 * An interface for expression nodes.
 * 
 * Every concrete type of expression node has to implement this interface.
 */
public interface ExpressionNode
{
  /** Node id for variable nodes */
  public static final int VARIABLE_NODE = 1;
  /** Node id for negation nodes */
  public static final int NEGATION_NODE = 2;
  /** Node id for conjunction nodes */
  public static final int CONJUNCTION_NODE = 3;
  /** Node id for disjunction nodes */
  public static final int DISJUNCTION_NODE = 4;
  /** Node id for implication nodes */
  public static final int IMPLICATION_NODE = 5;
  /** Node id for equivalence nodes */
  public static final int EQUIVALENCE_NODE = 6;
  

  /**
   * Returns the type of the node.ExpressionNode
   *
   * Each class derived from ExpressionNode representing a specific
   * role in the expression should return the type according to that
   * role.
   * 
   * @return type of the node
   */
  public int getType();
  
  /**
   * Evaluates and returns the value of the sub-expression represented by
   * the node.
   * 
   * @return value of expression
   */
  public boolean getValue();
  
  /**
   * Method needed for the visitor design pattern
   * 
   * @param visitor
   *          the visitor
   */
  public void accept(ExpressionNodeVisitor visitor);

}
