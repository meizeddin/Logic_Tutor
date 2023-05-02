package expressionTree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class OrTest {

	@Test
	public void testOrTrueFalse() {
		Store s = new Store();
		Variable a = Variable.of("A");
		Variable b = Variable.of("B");
		s.addVariable(a, Value.getTrue());
		s.addVariable(b, Value.getFalse());

		Or or =  new Or(a, b);
		assertTrue(or.evaluate(s),
				"Testing or expression when "
				+ "a == true & b == false"
		);
	}
	
	@Test
	public void testOrFalseFalse() {
		Store s = new Store();
		Variable a = Variable.of("A");
		Variable b = Variable.of("B");
		s.addVariable(a, Value.getFalse());
		s.addVariable(b, Value.getFalse());

		Or or =  new Or(a, b);
		assertFalse(or.evaluate(s),
				"Testing or expression when "
				+ "a == false & b == false"
		);
	}
	
	@Test
	public void testOrTrueTrue() {
		Store s = new Store();
		Variable a = Variable.of("A");
		Variable b = Variable.of("B");
		s.addVariable(a, Value.getTrue());
		s.addVariable(b, Value.getTrue());

		Or or =  new Or(a, b);
		assertTrue(or.evaluate(s), "Testing or expression when "
				+ "a == true & b == true"
		);
	}

}
