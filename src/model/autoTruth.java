package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import calcParser.EvaluationException;
import calcParser.ExpressionNode;
import calcParser.Parser;
import calcParser.ParserException;
import calcParser.SetVariable;


public class AutoTruth {
	public static void main(String[] args) {

		//crashes at 15

		//System.out.print(truthTable("abd",3));
		System.out.println(booleanTable(3));
		System.out.println(valuesTable("abd"));
		System.out.println(countVariables("(A&~A)=>B"));
		System.out.println(valuesTable("ABR").size());
		System.out.println(truthTable(valuesTable("AB"), 2));
		System.out.println(resultTable(valuesTable("AB"), booleanTable(2), "(A&~A)=>B"));

	}

	//produces a truth table
	public static String truthTable(List<Character> arrC, int n) {

		StringBuilder str = new StringBuilder();
		for (char s: arrC) {
			str.append("  ").append(s).append("\t\t");
		}
		str.append("\n");
		str.append("---------------------------------------------\n");
		int rows = (int) Math.pow(2, n);
		for (int i = rows -1; i >= 0 ;i--) {
			List<Boolean> arr = new ArrayList<>();
			for (int j = 0; j < leftPadding(Integer.toBinaryString(i), n).length(); j++) {
				if (leftPadding(Integer.toBinaryString(i), n).charAt(j) == '0') {
					arr.add(j, false);
					str.append(arr.get(j)).append("\t");
				}else {
					arr.add(j, true);
					str.append(arr.get(j)).append("\t");
				}
			}
			str.append("\n");
		}
		return str.toString();
	}

	public static List<Boolean> booleanTable(int n) {
		List<Boolean> arr = new ArrayList<>();
		int rows = (int) Math.pow(2, n);
		for (int i = 0; i < rows ; i++) {
			for (int j = 0; j < leftPadding(Integer.toBinaryString(i), n).length(); j++) {
				if (leftPadding(Integer.toBinaryString(i), n).charAt(j) == '0') {
					arr.add(j, false);
				}else {
					arr.add(j, true);
				}
			}
		}
		return arr;
	}

	public static List<Character> valuesTable(String str) {
		List<Character> arr = new ArrayList<>();
		int var = str.length();
		for (int i = 0; i < var ; i++) {
			arr.add(str.toUpperCase().charAt(i));
		}
		return arr;
	}


	public static String resultTable(List<Character> arrC, List<Boolean> arrB, String expression) {
		int variables = arrC.size();
		StringBuilder table = new StringBuilder();
		String result = "";
		String type= "";
		List<Boolean> arrBB = new ArrayList<>();
		Parser parser = new Parser();
		ExpressionNode expr = parser.parse(expression);
		for (int i = 0; i < (Math.pow(2, variables) * variables) ; i+=variables) {
			for (int j = 0; j < variables; j++) {
				try
				{
					expr.accept(new SetVariable(String.valueOf(arrC.get(j)), arrB.get(i+j)));
				}
				catch (ParserException | EvaluationException e)
				{
					System.out.println(e.getMessage());
				}
			}
			table.append(expr.getValue()).append("\n");
			arrBB.add(expr.getValue());
		}
		if ((arrBB.get(0)) && (arrBB.stream().distinct().count() <=1)) {
			type = "Type: Tautology";
		}else if ((!arrBB.get(0)) && (arrBB.stream().distinct().count() <=1)) {
			type = "Type: Contradiction";
		} else {
			type = "Type: Contingency";
		}
		result += expression.toUpperCase()+"\t"+type+"\n";
		result += "-------------------\n"; 
		result += table;
		return result;
	}

	public static String countVariables(String str)
	{
		// Converting the given string
		// into a character array
		char[] charArray = str.toLowerCase().toCharArray();
		StringBuilder result = new StringBuilder();

		// Traverse the character array
		for (char c : charArray) {

			// Check if the specified character is not digit
			// then add this character into result variable
			if (Character.isLowerCase(c)) {
				result.append(c);
			}
		}
		char[] result1 = result.toString().toCharArray();
		HashSet<Character> charArray1 = new LinkedHashSet<>(str.length()-1);
		for (char x: result1) {
			charArray1.add(x);
		}
		// Return result
		StringBuilder fin = new StringBuilder();
		for (char s: charArray1) {
			fin.append(s);
		}
		return fin.toString().toUpperCase();
	}

	public static String leftPadding(String inputStr, int length) {
		if (inputStr.length()>= length) {
			return inputStr;
		} else {
			StringBuilder sb = new StringBuilder();
			while(sb.length() < length - inputStr.length()) {
				sb.append('0');
			}
			sb.append(inputStr);
			return sb.toString();
		}
	}

}
