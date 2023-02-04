package calcParser;

import java.util.LinkedList;

/**
 * A parser for Logical expressions. The parser class defines a method
 * parse() which takes a string and returns an ExpressionNode that holds a
 * representation of the expression.
 * 
 * Parsing is implemented in the form of a recursive descent parser.
 * 
 */
public class Parser
{
	/** the tokens to parse */
	LinkedList<Token> tokens;
	/** the next token */
	Token lookahead;

	/**
	 * Parse a logical expression in a string and return an ExpressionNode.
	 * 
	 * This is a convenience method that first converts the string into a linked
	 * list of tokens using the expression tokenizer provided by the Tokenizer
	 * class.
	 * 
	 * @param expression
	 *          the string holding the input
	 * @return the internal representation of the expression in form of an
	 *         expression tree made out of ExpressionNode objects
	 */
	public ExpressionNode parse(String expression)
	{
		Tokenizer tokenizer = Tokenizer.getExpressionTokenizer();
		tokenizer.tokenize(expression);
		LinkedList<Token> tokens = tokenizer.getTokens();
		return this.parse(tokens);
	}

	/**
	 * Parse a logical expression that is contained in a list of tokens and return
	 * an ExpressionNode.
	 * 
	 * @param tokens
	 *          a list of tokens holding the tokenized input
	 * @return the internal representation of the expression in form of an
	 *         expression tree made out of ExpressionNode objects
	 */
	@SuppressWarnings("unchecked")
	public ExpressionNode parse(LinkedList<Token> tokens)
	{
		// implementing a recursive descent parser
		this.tokens = (LinkedList<Token>) tokens.clone();
		lookahead = this.tokens.getFirst();

		// top level non-terminal expression
		ExpressionNode expr = expression();

		if (lookahead.token != Token.EPSILON)
			throw new ParserException("Unexpected symbol %s found", lookahead);

		return expr;
	}

	/** handles the non-terminal expression */
	private ExpressionNode expression()
	{
		// only one rule
		// expression -> negatedTerm conjunctionDisjunction
		ExpressionNode expr = firstTerm();
		expr = negation(expr);
		return expr;
	}

	private ExpressionNode firstTerm()
	{
		// only one rule
		// expression -> negatedTerm conjunctionDisjunction
		ExpressionNode expr = conjunc();
		expr = conjunction(expr);
		return expr;
	}

	private ExpressionNode conjunc()
	{
		// only one rule
		// expression -> negatedTerm conjunctionDisjunction
		ExpressionNode expr = disjunc();
		expr = disjunction(expr);
		return expr;
	}

	private ExpressionNode disjunc()
	{
		// only one rule
		// expression -> negatedTerm conjunctionDisjunction
		ExpressionNode expr = implies();
		expr = implication(expr);
		return expr;
	}
	private ExpressionNode implies()
	{
		// only one rule
		// expression -> negatedTerm conjunctionDisjunction
		ExpressionNode expr = equiv();
		expr = equivalence(expr);
		return expr;
	}

	/** handles the non-terminal signed_term */
	private ExpressionNode equiv()
	{
		// signed_factor -> PLUSMINUS factor
		if (lookahead.token == Token.NEGATION)
		{
			nextToken();
			ExpressionNode t = firstTerm();
			return new NegationExpressionNode(t);
		}
		else if (lookahead.token == Token.OPEN_BRACKET)
		{
			nextToken();
			ExpressionNode expr = expression();
			if (lookahead.token != Token.CLOSE_BRACKET)
				throw new ParserException("Closing brackets expected", lookahead);
			nextToken();
			return expr;
		}
		// signed_term -> term
		return value();
	}

	/** handles the non-terminal value */
	private ExpressionNode value()
	{

		// argument -> VARIABLE
		if (lookahead.token == Token.VARIABLE)
		{
			ExpressionNode expr = new VariableExpressionNode(lookahead.sequence);
			nextToken();
			return expr;
		}

		if (lookahead.token == Token.EPSILON)
			throw new ParserException("Unexpected end of input");
		else
			throw new ParserException("Unexpected symbol %s found", lookahead);
	}

	private ExpressionNode negation(ExpressionNode expr)
	{
		// negated_term -> not
		if (lookahead.token == Token.NEGATION)
		{
			nextToken();
			ExpressionNode right = firstTerm();
			return new NegationExpressionNode(right);
		}
		// signed_term -> term
		return expr;
	}

	/** handles the non-terminal conjunctionDisjunction */
	private ExpressionNode conjunction(ExpressionNode expr)
	{
		if (lookahead.token == Token.CONJUNCTION)
		{
			nextToken();
			ExpressionNode right = conjunc();
			return new ConjunctionExpressionNode(expr, right);
		}
		// sum_op -> EPSILON
		return expr;
	}

	private ExpressionNode disjunction(ExpressionNode expr)
	{
		if (lookahead.token == Token.DISJUNCTION) {
			nextToken();
			ExpressionNode right = disjunc();
			return new DisjunctionExpressionNode(expr, right);
		}
		// sum_op -> EPSILON
		return expr;
	}

	/** handles the non-terminal term_op */
	private ExpressionNode implication(ExpressionNode expression)
	{
		// term_op -> MULTDIV factor term_op
		if (lookahead.token == Token.IMPLICATION)
		{

			nextToken();
			ExpressionNode right = implies();
			return new ImplicationExpressionNode(expression, right);
		}

		// term_op -> EPSILON
		return expression;
	}
	
	/** handles the non-terminal factor_op */
	private ExpressionNode equivalence(ExpressionNode expr1)
	{
		// factor_op -> RAISED expression
		if (lookahead.token == Token.EQUIVALENCE)
		{
			nextToken();
			ExpressionNode right = equiv();

			return new EquivalenceExpressionNode(expr1, right);
		}

		// factor_op -> EPSILON
		return expr1;
	}


	/**
	 * Remove the first token from the list and store the next token in lookahead
	 */
	private void nextToken()
	{
		tokens.pop();
		// at the end of input we return an epsilon token
		if (tokens.isEmpty())
			lookahead = new Token(Token.EPSILON, "", -1);
		else
			lookahead = tokens.getFirst();
	}
}
