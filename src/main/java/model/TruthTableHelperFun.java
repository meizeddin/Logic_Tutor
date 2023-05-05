package model;

import expressionTree.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The TruthTableHelperFun class provides helper functions to create and evaluate
 * truth tables based on logical expressions.
 */
public class TruthTableHelperFun {
    /**
     * The main method of the TruthTableHelperFun class which executes the program.
     * It creates an expression, converts it to postfix notation, and generates
     * a truth table for the expression.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // create expression
        String expression = "(A|A)";
        List<String> tokens = Tokenizer.tokenize(expression);
        List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
        Expression expr = Parser.Evaluator(Objects.requireNonNull(shunting));


        // generate truth table
        System.out.println(booleanTable(Parser.list));
        System.out.println(truthTable(Parser.list));
        System.out.println(resultTable(Parser.list, booleanTable(Parser.list), expr, expression));

    }

    /**
     * The truthTable method produces a truth table as a string based on a given list
     * of variables. It generates all possible combinations of values for the variables,
     * and evaluates the expression for each combination to determine the truth value.
     *
     * @param list the list of variables for which to generate the truth table
     * @return a string representing the truth table
     */
    public static String truthTable(ArrayList<Variable> list) {
        int n = list.size();
        StringBuilder str = new StringBuilder();
        str.append("#").append("\t");
        for (Variable x: list) {
            str.append("   ").append(x.getName()).append("\t\t");
        }
        str.append("\n");
        str.append("---------------------------------------------\n");
        int rows = (int) Math.pow(2, n);
        for (int i = rows -1; i >= 0 ;i--) {
            str.append(rows - i).append("\t");
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

    /**
     * The booleanTable method generates a list of all possible combinations of
     * boolean values for a given list of variables.
     *
     * @param list the list of variables for which to generate the boolean table
     * @return a list of boolean values representing all possible combinations
     */
    public static List<Value> booleanTable(ArrayList<Variable> list) {
        int n = list.size();
        List<Value> arr = new ArrayList<>();
        int rows = (int) Math.pow(2, n);
        for (int i = 0; i < rows ; i++) {
            for (int j = 0; j < leftPadding(Integer.toBinaryString(i), n).length(); j++) {
                if (leftPadding(Integer.toBinaryString(i), n).charAt(j) == '0') {
                    arr.add(j, Value.getFalse());
                }else {
                    arr.add(j, Value.getTrue());
                }
            }
        }
        return arr;
    }


    /**
     * Generates a truth result table for a given expression and list of variables
     * @param list list of variables used in the expression
     * @param arrB list of boolean values representing the input combinations
     * @param expr the expression to evaluate
     * @param expression the original expression as a string
     * @return a string containing the reult table and type of the expression (tautology, contradiction or contingency)
     */
    public static String resultTable(ArrayList<Variable> list, List<Value> arrB, Expression expr, String expression) {
        int variables = list.size();
        StringBuilder table = new StringBuilder();
        String result = "";
        String type= "";
        int counter= 1;
        Store store = new Store();
        List<Boolean> arrBB = new ArrayList<>();
        for (int i = 0; i < (Math.pow(2, variables) * variables) ; i+=variables) {
            for (int j = 0; j < variables; j++) {
                try
                {
                    store.addVariable(list.get(j), arrB.get(i+j));
                }
                catch (EvaluationException e)
                {
                    System.out.println(e.getMessage());
                }
            }
            table.append(counter++).append("\t").append(expr.evaluate(store)).append("\n");
            arrBB.add(expr.evaluate(store));
            store.clear();
        }
        if(!arrBB.isEmpty()) {
            if ((arrBB.get(0)) && (arrBB.stream().distinct().count() <= 1)) {
                type = "Type: Tautology";
            } else if ((!arrBB.get(0)) && (arrBB.stream().distinct().count() <= 1)) {
                type = "Type: Contradiction";
            } else {
                type = "Type: Contingency";
            }
        }
        result += "#"+"\t"+expression.toUpperCase()+"\t  "+type+"\n";
        result += "-------------------\n";
        result += table;
        return result;
    }

    /**
     * Adds left padding to a string
     * @param inputStr the input string
     * @param length the desired length of the output string
     * @return a string with the specified length, padded with leading zeros if necessary
     */
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
