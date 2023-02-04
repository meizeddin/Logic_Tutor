/**
 * 
 */
package expressionTreeTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import expressionTree.Tokenizer;

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
		assertEquals("Tokenizer Should return a list of tokens",tokens, Tokenizer.tokenize(st));
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
		assertEquals("Tokenizer Should return a list of tokens ignoring 3",tokens, Tokenizer.tokenize(st));
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
		assertEquals("Tokenizer Should return a list of tokens ignoring 3",tokens, Tokenizer.tokenize(st));
	}
	
	/**
	 * Tests That the tokenizer tokeizes a parenthesis
	 */
	@Test
	public void testTokenizeParentheses() {
		String st = "())(";
		List<String> tokens = new ArrayList<>();
		tokens.add("(");
		tokens.add(")");
		tokens.add(")");
		tokens.add("(");
		assertEquals("Tokenizer Should return a list of tokens ignoring 3",tokens, Tokenizer.tokenize(st));
	}
	
	/**
	 * Tests That the tokenizer tokeizes a string in the right order
	 */
	@Test
	public void testTokenizeRightOrder() {
		String st = "A&B";
		List<String> tokens = new ArrayList<>();
		tokens.add("B");
		tokens.add("&");
		tokens.add("A");
		assertFalse("Tokenizer Should return a list of tokens ignoring in orde",tokens == Tokenizer.tokenize(st));
	}

}
