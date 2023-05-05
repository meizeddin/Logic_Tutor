package expressionTree.rules;

import expressionTree.Expression;
import expressionTree.Parser;
import expressionTree.ShuntingYardAlgorithm;
import expressionTree.Tokenizer;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class AndEliminationRVisitorTest {
    @Test
    void testAndEliminationR() {
        List<String> tokens = Tokenizer.tokenize("A&(A|B)");
        List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
        Expression exp = Parser.Evaluator(Objects.requireNonNull(shunting));
        AndEliminationRVisitor visitor = new AndEliminationRVisitor();

        assertEquals("A",
                exp.accept(visitor).toString(),
                "Testing AndEliminationL, should pass");
    }
    @Test
    void testAndEliminationR1() {
        List<String> tokens = Tokenizer.tokenize("A&B");
        List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
        Expression exp = Parser.Evaluator(Objects.requireNonNull(shunting));
        AndEliminationRVisitor visitor = new AndEliminationRVisitor();

        assertNotEquals("B",
                exp.accept(visitor).toString(),
                "Testing AndEliminationL, should fail");
    }
    @Test
    void canApply() {
        List<String> tokens = Tokenizer.tokenize("(A&B)&(A)&(B)");
        List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
        Expression exp = Parser.Evaluator(Objects.requireNonNull(shunting));
        AndEliminationRVisitor visitor = new AndEliminationRVisitor();

        assertTrue(visitor.canApply(exp),
                "Testing AndEliminationL, should pass");
    }
}