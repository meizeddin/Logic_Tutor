package expressionTreeTests;

import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import org.junit.Test;

import expressionTree.ShuntingYardAlgorithm;
import expressionTree.Tokenizer;

public class ShuntingYardAlgorithmTest {

	@Test
	public void testInfixToPostfix() {
		List<String> tokens = Tokenizer.tokenize("A&B|R");
		List<String> correctList = new ArrayList<>();
		correctList.add("A");
		correctList.add("B");
		correctList.add("&");
		correctList.add("R");
		correctList.add("|");

		assertEquals("converts infix to postfix correctly",
				correctList,
				ShuntingYardAlgorithm.infixToPostfix(tokens)
				);	
	}
	
	@Test
	public void testInfixToPostfixWithParanthesis() {
		List<String> tokens = Tokenizer.tokenize("!(A&B)|R");
		List<String> correctList = new ArrayList<>();
		correctList.add("A");
		correctList.add("B");
		correctList.add("&");
		correctList.add("!");
		correctList.add("R");
		correctList.add("|");

		assertEquals("converts infix to postfix with paranthesis correctly",
				correctList,
				ShuntingYardAlgorithm.infixToPostfix(tokens)
				);	
	}
	
	@Test
	public void testInfixToPostfixComplex() {
		List<String> tokens = Tokenizer.tokenize("!(A&B)|R<=>V=>!A");
		List<String> correctList = new ArrayList<>();
		correctList.add("A");
		correctList.add("B");
		correctList.add("&");
		correctList.add("!");
		correctList.add("R");
		correctList.add("|");
		correctList.add("V");
		correctList.add("A");
		correctList.add("!");
		correctList.add("=>");
		correctList.add("<=>");

		assertEquals("converts complex infix to postfix correctly",
				correctList,
				ShuntingYardAlgorithm.infixToPostfix(tokens)
				);	
	}
	
	@Test
	public void testInfixToPostfixDoubleParentheses() {
		List<String> tokens = Tokenizer.tokenize("A&((B|R))");
		List<String> correctList = new ArrayList<>();
		correctList.add("A");
		correctList.add("B");
		correctList.add("R");
		correctList.add("|");
		correctList.add("&");

		assertEquals("converts infix to postfix with double paranthesis correctly",
				correctList,
				ShuntingYardAlgorithm.infixToPostfix(tokens)
				);	
	}
	
	@Test
	public void testInfixToPostfixDuplicateVariable() {
		List<String> tokens = Tokenizer.tokenize("AA&B|R");
		List<String> correctList = new ArrayList<>();
		correctList.add("A");
		correctList.add("B");
		correctList.add("&");
		correctList.add("R");
		correctList.add("|");

		assertEquals("converts infix to postfix with correctly",
				correctList,
				ShuntingYardAlgorithm.infixToPostfix(tokens)
				);	
	}
}
