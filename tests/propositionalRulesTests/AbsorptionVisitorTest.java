package propositionalRulesTests;

import static org.junit.Assert.*;
import expressionTree.*;
import expressionTree.rules.AbsorptionVisitor;
import org.junit.Test;
import java.util.List;
import java.util.Objects;

public class AbsorptionVisitorTest {
    @Test
    public void testAbsorptionAnd() {
        List<String> tokens = Tokenizer.tokenize("A&(A|B)");
        List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
        Expression exp = Parser.Evaluator(Objects.requireNonNull(shunting));
        AbsorptionVisitor visitor = new AbsorptionVisitor();

        assertEquals("Testing Absorption Law when"
                        + " a == true & b == false", "A", exp.accept(visitor).toString());
    }
    @Test
    public void testAbsorptionAndR() {
        List<String> tokens = Tokenizer.tokenize("(A|B)&A");
        List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
        Expression exp = Parser.Evaluator(Objects.requireNonNull(shunting));
        AbsorptionVisitor visitor = new AbsorptionVisitor();

        assertEquals("Testing Absorption Law when"
                + " a == true & b == false", "A", exp.accept(visitor).toString());
    }
    @Test
    public void testAbsorptionOr() {
        List<String> tokens = Tokenizer.tokenize("A|(A&B)");
        List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
        Expression exp = Parser.Evaluator(Objects.requireNonNull(shunting));
        AbsorptionVisitor visitor = new AbsorptionVisitor();

        assertEquals("Testing Absorption Law when"
                + " a == true & b == false", "A", exp.accept(visitor).toString());
    }

    @Test
    public void testAbsorptionOrR() {
        List<String> tokens = Tokenizer.tokenize("(A&B)|A");
        List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
        Expression exp = Parser.Evaluator(Objects.requireNonNull(shunting));
        AbsorptionVisitor visitor = new AbsorptionVisitor();

        assertEquals("Testing Absorption Law when"
                + " a == true & b == false", "A", exp.accept(visitor).toString());
    }

}
