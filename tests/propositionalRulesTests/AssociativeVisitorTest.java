package propositionalRulesTests;

import static org.junit.Assert.*;
import expressionTree.*;
import expressionTree.rules.AbsorptionVisitor;
import expressionTree.rules.AssociativeVisitor;
import org.junit.Test;
import java.util.List;
import java.util.Objects;
public class AssociativeVisitorTest {
    @Test
    public void testAssociativeR() {
        List<String> tokens = Tokenizer.tokenize("(B<=>C)<=>A");
        List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
        Expression exp = Parser.Evaluator(Objects.requireNonNull(shunting));
        AssociativeVisitor visitor = new AssociativeVisitor();

        assertEquals("Testing Associative Law when"
                + " a == true & b == false", "(B<=>(C<=>A))", exp.accept(visitor).toString());
    }

    @Test
    public void testAssociativeL() {
        List<String> tokens = Tokenizer.tokenize("A<=>(B<=>C)");
        List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
        Expression exp = Parser.Evaluator(Objects.requireNonNull(shunting));
        AssociativeVisitor visitor = new AssociativeVisitor();

        assertEquals("Testing Associative Law when"
                + " a == true & b == false", "((A<=>B)<=>C)", exp.accept(visitor).toString());
    }
}
