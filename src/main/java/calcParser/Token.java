package calcParser;

/**
 * A token that is produced by Tokenizer and fed into Parser.parse
 * 
 * A token consists of a token identifier, a string that the token was
 * created from and the position in the input string that the token was found.
 * 
 * The token id must be one of a number of pre-defined values
 */
public class Token
{
  /** Token id for the epsilon terminal */
  public static final int EPSILON = 0;
  /** Token id for and */
  public static final int NEGATION = 1;
  /** Token id for implies */
  public static final int CONJUNCTION = 2;
  /** Token id for or */
  public static final int DISJUNCTION = 3;
  /** Token id for not */
  public static final int IMPLICATION = 4;
  /** Token id for if and only if */
  public static final int EQUIVALENCE = 5;
  /** Token id for opening brackets */
  public static final int OPEN_BRACKET = 6;
  /** Token id for closing brackets */
  public static final int CLOSE_BRACKET = 7;
  /** Token id for variable names */
  public static final int VARIABLE = 8;

  /** the token identifier */
  public final int token;
  /** the string that the token was created from */
  public final String sequence;
  /** the position of the token in the input string */
  public final int pos;

  /**
   * Construct the token with its values
   * @param token the token identifier
   * @param sequence the string that the token was created from
   * @param pos the position of the token in the input string
   */
  public Token(int token, String sequence, int pos)
  {
    super();
    this.token = token;
    this.sequence = sequence;
    this.pos = pos;
  }

}