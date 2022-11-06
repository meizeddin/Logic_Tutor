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


public class autoTruth {
	public static void main(String[] args) {

		//crashes at 15

		//System.out.print(truthTable("abd",3));
		System.out.println(booleanTable(3));
		System.out.println(valuesTable("abd"));
		System.out.println(valuesTable("ABR").size());
		System.out.println(resultTable(valuesTable("ABR"), booleanTable(3), "(~(A=>(B|R))<=>A)"));

	}

	public static String truthTable(List<Character> arrC, int n) {

		String str = "";
		for (char s: arrC) {
			str += "  " + s + "\t\t";
		}
		str.toUpperCase();
		str += "\n";
		str +="---------------------------------------------\n";
		int rows = (int) Math.pow(2, n);
		int maxColumn = n;
		for (int i = rows -1; i >= 0 ;i--) {
			List<Boolean> arr = new ArrayList<>();
			for (int j = 0; j < leftPadding(Integer.toBinaryString(i), maxColumn).length(); j++) {
				Boolean bool;
				if (leftPadding(Integer.toBinaryString(i), maxColumn).charAt(j) == '0') {
					bool = false;
					arr.add(j, bool);
					str += arr.get(j) + "\t";
				}else {
					bool = true;
					arr.add(j, bool);
					str += arr.get(j) + "\t";
				}
			}
			str += "\n";
		}
		return str;
	}

	public static List<Boolean> booleanTable(int n) {
		List<Boolean> arr = new ArrayList<>();
		int rows = (int) Math.pow(2, n);
		int maxColumn = n;
		for (int i = 0; i < rows ; i++) {
			for (int j = 0; j < leftPadding(Integer.toBinaryString(i), maxColumn).length(); j++) {
				Boolean bool;
				if (leftPadding(Integer.toBinaryString(i), maxColumn).charAt(j) == '0') {
					bool = false;
					arr.add(j, bool);
				}else {
					bool = true;
					arr.add(j, bool);
				}
			}
		}
		return arr;
	}

	public static List<Character> valuesTable(String str) {
		List<Character> arr = new ArrayList<>();
		int var = str.length();
		for (int i = 0; i < var ; i++) {
			arr.add(str.charAt(i));
		}
		return arr;
	}


	public static String resultTable(List<Character> arrC, List<Boolean> arrB, String expression) {
		int variables = arrC.size();
		String table = "";
		table += expression.toUpperCase() + "\n";
		table += "-------------------\n"; 
		Parser parser = new Parser();
		ExpressionNode expr = parser.parse(expression);
		for (int i = 0; i < (Math.pow(2, variables) * variables) ; i+=variables) {
			for (int j = 0; j < variables; j++) {
				try
				{
					expr.accept(new SetVariable(arrC.get(j) + "", arrB.get(i+j)));
				}
				catch (ParserException e)
				{
					System.out.println(e.getMessage());
				}
				catch (EvaluationException e)
				{
					System.out.println(e.getMessage());
				}	
			}
			table += expr.getValue() + "\n";
		}
		return table;
	}

	public static String countVariables(String str)
	{
		// Converting the given string
		// into a character array
		char[] charArray = str.toLowerCase().toCharArray();
		String result = "";

		// Traverse the character array
		for (int i = 0; i < charArray.length; i++) {

			// Check if the specified character is not digit
			// then add this character into result variable
			if (Character.isLowerCase(charArray[i])) {
				result = result + charArray[i];
			}
		}
		char result1[] = result.toCharArray();
		HashSet<Character> charArray1 = new LinkedHashSet<>(str.length()-1);
		for (char x: result1) {
			charArray1.add(x);
		}
		// Return result
		String fin = "";
		for (char s: charArray1) {
			fin += s;
		}
		return fin.toUpperCase();
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
