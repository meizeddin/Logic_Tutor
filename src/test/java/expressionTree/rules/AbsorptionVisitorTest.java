package expressionTree.rules;

import org.junit.jupiter.api.Test;
import expressionTree.*;
import expressionTree.rules.AbsorptionVisitor;
import java.util.List;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AbsorptionVisitorTest {
    @Test
    public void testAbsorptionAnd() {
        List<String> tokens = Tokenizer.tokenize("A&(A|B)");
        List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
        Expression exp = Parser.Evaluator(Objects.requireNonNull(shunting));
        AbsorptionVisitor visitor = new AbsorptionVisitor();

        assertEquals("A",
                exp.accept(visitor).toString(),
                "Testing Absorption Law when"
                        + " a == true & b == false");
    }
    @Test
    public void testAbsorptionAndR() {
        List<String> tokens = Tokenizer.tokenize("(A|B)&A");
        List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
        Expression exp = Parser.Evaluator(Objects.requireNonNull(shunting));
        AbsorptionVisitor visitor = new AbsorptionVisitor();

        assertEquals("A",
                exp.accept(visitor).toString(),
                "Testing Absorption Law when"
                + " a == true & b == false");
    }
    @Test
    public void testAbsorptionOr() {
        List<String> tokens = Tokenizer.tokenize("A|(A&B)");
        List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
        Expression exp = Parser.Evaluator(Objects.requireNonNull(shunting));
        AbsorptionVisitor visitor = new AbsorptionVisitor();

        assertEquals("A",
                exp.accept(visitor).toString(),
                "Testing Absorption Law when"
                + " a == true & b == false");
    }

    @Test
    public void testAbsorptionOrR() {
        List<String> tokens = Tokenizer.tokenize("(A&B)|A");
        List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
        Expression exp = Parser.Evaluator(Objects.requireNonNull(shunting));
        AbsorptionVisitor visitor = new AbsorptionVisitor();

        assertEquals("A",
                exp.accept(visitor).toString(),
                "Testing Absorption Law when"
                + " a == true & b == false");
    }

}
