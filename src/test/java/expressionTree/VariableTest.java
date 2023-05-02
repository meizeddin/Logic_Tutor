package expressionTree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VariableTest {

	@Test
    public void testVariableCreation() {
        Store s = new Store();
        // create a new Variable with the name "x" and value true
        Variable x = Variable.of("x");
        s.addVariable(x, Value.getTrue());
        // Assert that the name of the variable is "x"
        assertEquals("x", x.getName());
        // Assert that the value of the variable is true
        assertTrue(x.evaluate(s));
    }

}
