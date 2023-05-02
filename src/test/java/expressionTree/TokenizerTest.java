/**
 * 
 */
package expressionTree;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author meize
 * Test methods for {@link expressionTree.Tokenizer#tokenize(java.lang.String)}.
 */
public class TokenizerTest {

	/**
	 * Tests That a String gets tokenized correctly
	 */
	@Test
	public void testTokenize() {
		String st = "A&B";
		List<String> tokens = new ArrayList<>();
		tokens.add("A");
		tokens.add("&");
		tokens.add("B");
		assertEquals(tokens, Tokenizer.tokenize(st),"Tokenizer Should return a list of tokens");
	}
	
	/**
	 * Tests That the tokenizer ignores digits 
	 */
	
	@Test
	public void testTokenizeIgnoresDigits() {
		String st = "A&B3";
		List<String> tokens = new ArrayList<>();
		tokens.add("A");
		tokens.add("&");
		tokens.add("B");
		assertEquals(tokens, Tokenizer.tokenize(st),"Tokenizer Should return a list of tokens ignoring 3");
	}
	/**
	 * Tests That the tokenizer accepts all propositional logic symbols
	 * and ignores the rest 
	 */
	@Test
	public void testTokenizePropSymbols() {
		String st = "&|=><=>!+=";
		List<String> tokens = new ArrayList<>();
		tokens.add("&");
		tokens.add("|");
		tokens.add("=>");
		tokens.add("<=>");
		tokens.add("!");
		assertEquals(tokens, Tokenizer.tokenize(st),"Tokenizer Should return a list of tokens ignoring 3");
	}
	
	/**
	 * Tests That the tokenizer tokenizes a parenthesis
	 */
	@Test
	public void testTokenizeParentheses() {
		String st = "())(";
		List<String> tokens = new ArrayList<>();
		tokens.add("(");
		tokens.add(")");
		tokens.add(")");
		tokens.add("(");
		assertEquals(tokens, Tokenizer.tokenize(st),"Tokenizer Should return a list of tokens ignoring 3");
	}
	
	/**
	 * Tests That the tokenizer tokenizes a string in the right order
	 */
	@Test
	public void testTokenizeRightOrder() {
		String st = "A&B";
		List<String> tokens = new ArrayList<>();
		tokens.add("B");
		tokens.add("&");
		tokens.add("A");
		assertNotSame(tokens, Tokenizer.tokenize(st), "Tokenizer Should return a list of tokens ignoring in order");
	}

}
