package expressionTreeTests;

import static org.junit.Assert.*;

import expressionTree.Variable;
import org.junit.Test;

import expressionTree.Store;
import expressionTree.Value;


public class StoreTest {

	@Test
	public void testAddVariableAndGetValue() {
		Store store = new Store();
        Variable x = new Variable("V");
		store.addVariable(x, Value.getTrue());
		assertEquals("Store should add a variable and get its value TRUE",
				Value.getTrue(), store.getValue(x));
	}
}
