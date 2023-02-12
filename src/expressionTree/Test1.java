package expressionTree;

/* Java implementation to convert
infix expression to postfix*/
//Note that here we use ArrayDeque class for Stack
//operations

import java.util.ArrayDeque;
import java.util.Deque;


class Test1 {

   // A utility function to return
   // precedence of a given operator
   // Higher returned value means
   // higher precedence
   static int Prec(char ch)
   {
       return switch (ch) {
           case '+', '-' -> 1;
           case '*', '/' -> 2;
           case '^' -> 3;
           default -> -1;
       };
   }

   // The main method that converts
   // given infix expression
   // to postfix expression.
   static String infixToPostfix(String exp)
   {
       // initializing empty String for result
       StringBuilder result = new StringBuilder();

       // initializing empty stack
       Deque<Character> stack
           = new ArrayDeque<>();

       for (int i = 0; i < exp.length(); ++i) {
           char c = exp.charAt(i);

           // If the scanned character is an
           // operand, add it to output.
           if (Character.isLetterOrDigit(c))
               result.append(c);

           // If the scanned character is an '(',
           // push it to the stack.
           else if (c == '(')
               stack.push(c);

           //  If the scanned character is an ')',
           // pop and output from the stack
           // until an '(' is encountered.
           else if (c == ')') {
               while (!stack.isEmpty()
                      && stack.peek() != '(') {
                   result.append(stack.peek());
                   stack.pop();
               }

               stack.pop();
           }
           else // an operator is encountered
           {
               while (!stack.isEmpty()
                      && Prec(c) <= Prec(stack.peek())) {

                   result.append(stack.peek());
                   stack.pop();
               }
               stack.push(c);
           }
       }

       // pop all the operators from the stack
       while (!stack.isEmpty()) {
           if (stack.peek() == '(')
               return "Invalid Expression";
           result.append(stack.peek());
           stack.pop();
       }
      
       return result.toString();
   }

   // Driver's code
   public static void main(String[] args)
   {
       String exp = "(a+b)*(c^d-e)^(f+g*h)-i";
      
         // Function call
       System.out.println(infixToPostfix(exp));
   }
}