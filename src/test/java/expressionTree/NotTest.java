package expressionTree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NotTest {

	@Test
	public void testNotTrue() {
		Store s = new Store();
		Variable a = Variable.of("A");
		s.addVariable(a, Value.getTrue());
		Not not =  new Not(a);
		assertFalse(not.evaluate(s),
				"Testing not expression when"
				+ "a == true"
		);
	}
	
	@Test
	public void testNotFalse() {
		Store s = new Store();
		Variable a = Variable.of("A");
		s.addVariable(a, Value.getFalse());
		Not not =  new Not(a);
		assertTrue(not.evaluate(s),
				"Testing not expression when"
				+ "a == false"
		);
	}
	
	@Test
	public void testDoubleNot() {
		Store s = new Store();
		Variable a = Variable.of("A");
		s.addVariable(a, Value.getTrue());
		Not not =  new Not(a);
		Not not1 =  new Not(not);

		assertTrue(not1.evaluate(s),
				"Testing a double not expression"
		);
	}

}
