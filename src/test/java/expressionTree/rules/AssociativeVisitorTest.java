package expressionTree.rules;

import org.junit.jupiter.api.Test;
import expressionTree.*;
import expressionTree.rules.AssociativeVisitor;
import java.util.List;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssociativeVisitorTest {
    @Test
    public void testAssociativeR() {
        List<String> tokens = Tokenizer.tokenize("(B<=>C)<=>A");
        List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
        Expression exp = Parser.Evaluator(Objects.requireNonNull(shunting));
        AssociativeVisitor visitor = new AssociativeVisitor();

        assertEquals("(B<=>(C<=>A))",
                exp.accept(visitor).toString(),
                "Testing Associative Law when"
                + " a == true & b == false");
    }

    @Test
    public void testAssociativeL() {
        List<String> tokens = Tokenizer.tokenize("A<=>(B<=>C)");
        List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
        Expression exp = Parser.Evaluator(Objects.requireNonNull(shunting));
        AssociativeVisitor visitor = new AssociativeVisitor();

        assertEquals("((A<=>B)<=>C)",
                exp.accept(visitor).toString(),
                "Testing Associative Law when"
                + " a == true & b == false");
    }
}
