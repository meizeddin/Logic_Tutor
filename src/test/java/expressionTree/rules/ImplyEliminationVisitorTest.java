package expressionTree.rules;

import expressionTree.Expression;
import expressionTree.Parser;
import expressionTree.ShuntingYardAlgorithm;
import expressionTree.Tokenizer;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ImplyEliminationVisitorTest {
    @Test
    void testImplyElimination() {
        List<String> tokens = Tokenizer.tokenize("A=>B");
        List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
        Expression exp = Parser.Evaluator(Objects.requireNonNull(shunting));
        ImplyEliminationVisitor visitor = new ImplyEliminationVisitor();

        assertNotEquals("A",
                exp.accept(visitor).toString(),
                "Testing ImplyElimination, should fail");
    }

    @Test
    void testImplyElimination1() {
        List<String> tokens = Tokenizer.tokenize("A=>B");
        List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
        Expression exp = Parser.Evaluator(Objects.requireNonNull(shunting));
        ImplyEliminationVisitor visitor = new ImplyEliminationVisitor();

        assertEquals("B",
                exp.accept(visitor).toString(),
                "Testing ImplyElimination, should pass");
    }
    @Test
    void testImplyElimination2() {
        List<String> tokens = Tokenizer.tokenize("((A=>B)=>(A))=>C");
        List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
        Expression exp = Parser.Evaluator(Objects.requireNonNull(shunting));
        ImplyEliminationVisitor visitor = new ImplyEliminationVisitor();

        assertEquals("C",
                exp.accept(visitor).toString(),
                "Testing ImplyElimination, should pass");
    }
    @Test
    void canApply() {
        List<String> tokens = Tokenizer.tokenize("A&B=>B");
        List<String> shunting = ShuntingYardAlgorithm.infixToPostfix(tokens);
        Expression exp = Parser.Evaluator(Objects.requireNonNull(shunting));
        ImplyEliminationVisitor visitor = new ImplyEliminationVisitor();

        assertTrue(visitor.canApply(exp),
                "Testing AndEliminationL, should pass");
    }
}