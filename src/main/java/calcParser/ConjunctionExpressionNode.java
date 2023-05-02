package calcParser;

/**
 * An ExpressionNode that handles conjunctions.
 * 
 */
public class ConjunctionExpressionNode implements ExpressionNode
{
	/** the node containing the left variable */
	private ExpressionNode left;
	/** the node containing the right variable */
	private ExpressionNode right;
	/**
	 * Default constructor.
	 */
	public ConjunctionExpressionNode(ExpressionNode left, ExpressionNode right)
	{
		this.left = left;
		this.right = right;
	}

	/**
	 * Returns the type of the node, in this case ExpressionNode.ADDITION_NODE
	 */
	public int getType()
	{
		return CONJUNCTION_NODE;
	}

	/**
	 * Returns the value of the sub-expression that is rooted at this node.
	 * 
	 * All the terms are evaluated and added or subtracted from the total sum.
	 */
	public boolean getValue()
	{
		return (left.getValue() && right.getValue());
	}

	/**
	 * Implementation of the visitor design pattern.
	 * 
	 * Calls visit on the visitor and then passes the visitor on to the accept
	 * method of all the terms in the sum.
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
