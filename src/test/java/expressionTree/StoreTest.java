package expressionTree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class StoreTest {

	@Test
	public void testAddVariableAndGetValue() {
		Store store = new Store();
        Variable x = Variable.of("V");
		store.addVariable(x, Value.getTrue());
		assertEquals(Value.getTrue(), store.getValue(x),"Store should add a variable and get its value TRUE");
	}
}
