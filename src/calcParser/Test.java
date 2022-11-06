/*
 * This software and all files contained in it are distrubted under the MIT license.
 * 
 * Copyright (c) 2013 Cogito Learning Ltd
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package calcParser;

import model.autoTruth;

/**
 * Test the Parser
 */
public class Test
{

  /**
   * The main method to test the functionality of the parser
   */
  public static void main(String[] args)
  {
    
    String exprstr = "(~(A=>(B|R))<=>A)";
    if (args.length>0) exprstr = args[0];
    
    Parser parser = new Parser();
    try
    {
      ExpressionNode expr = parser.parse(exprstr);
      expr.accept(new SetVariable("A", true));
      expr.accept(new SetVariable("B", true));
      expr.accept(new SetVariable("R", true));
      System.out.println("The value of the expression is " + expr.getValue());
      System.out.println(autoTruth.countVariables(exprstr).charAt(0) + "");
      
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
}