package expressionTree.rules;

import expressionTree.Expression;
import expressionTree.Parser;
import expressionTree.ShuntingYardAlgorithm;
import expressionTree.Tokenizer;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class AndEliminationLVisitorTest {

    @Test
    void testAndEliminationL() {
        List<String> tokens = Tokenizer.tokenize("A&(A|B)");
        List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
        Expression exp = Parser.Evaluator(Objects.requireNonNull(shunting));
        AndEliminationLVisitor visitor = new AndEliminationLVisitor();

        assertEquals("(A|B)",
                exp.accept(visitor).toString(),
                "Testing AndEliminationL, should pass");
    }
    @Test
    void testAndEliminationL1() {
        List<String> tokens = Tokenizer.tokenize("A&B");
        List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
        Expression exp = Parser.Evaluator(Objects.requireNonNull(shunting));
        AndEliminationLVisitor visitor = new AndEliminationLVisitor();

        assertNotEquals("A",
                exp.accept(visitor).toString(),
                "Testing AndEliminationL, should fail");
    }
    @Test
    void canApply() {
        List<String> tokens = Tokenizer.tokenize("(A&B)&(A)&(B)");
        List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
        Expression exp = Parser.Evaluator(Objects.requireNonNull(shunting));
        AndEliminationLVisitor visitor = new AndEliminationLVisitor();

        assertTrue(visitor.canApply(exp),
                "Testing AndEliminationL, should pass");
    }
}