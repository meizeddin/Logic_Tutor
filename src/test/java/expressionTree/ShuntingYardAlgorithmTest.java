package expressionTree;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

		assertEquals(correctList,
				ShuntingYardAlgorithm.infixToPostfix(tokens),
				"converts infix to postfix correctly");
	}
	
	@Test
	public void testInfixToPostfixWithParenthesis() {
		List<String> tokens = Tokenizer.tokenize("!(A&B)|R");
		List<String> correctList = new ArrayList<>();
		correctList.add("A");
		correctList.add("B");
		correctList.add("&");
		correctList.add("!");
		correctList.add("R");
		correctList.add("|");

		assertEquals(correctList,
				ShuntingYardAlgorithm.infixToPostfix(tokens),
				"converts infix to postfix with parenthesis correctly");
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

		assertEquals(correctList,
				ShuntingYardAlgorithm.infixToPostfix(tokens),
				"converts complex infix to postfix correctly");
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

		assertEquals(correctList,
				ShuntingYardAlgorithm.infixToPostfix(tokens),
				"converts infix to postfix with double parenthesis correctly");
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

		assertEquals(correctList,
				ShuntingYardAlgorithm.infixToPostfix(tokens),
				"converts infix to postfix with correctly");
	}
}
