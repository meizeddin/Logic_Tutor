package model;


import calcParser.EvaluationException;
import calcParser.ParserException;
import expressionTree.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TruthTableHelperFun {
    public static void main(String[] args) {
        // create expression
        String expression = "(A|B)=>R";
        List<String> tokens = Tokenizer.tokenize(expression);
        List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
        Expression expr = Parser.Evaluator(Objects.requireNonNull(shunting));


        //System.out.print(truthTable("abd",3));
        System.out.println(booleanTable(Parser.list));
        System.out.println(truthTable(Parser.list));
        System.out.println(resultTable(Parser.list, booleanTable(Parser.list), expr, expression));

    }

    //produces a truth table
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
                catch (ParserException e)
                {
                    System.out.println(e.getMessage());
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
        if ((arrBB.get(0)) && (arrBB.stream().distinct().count() <=1)) {
            type = "Type: Tautology";
        }else if ((!arrBB.get(0)) && (arrBB.stream().distinct().count() <=1)) {
            type = "Type: Contradiction";
        } else {
            type = "Type: Contingency";
        }
        result += "#"+"\t"+expression.toUpperCase()+"\t  "+type+"\n";
        result += "-------------------\n";
        result += table;
        return result;
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
