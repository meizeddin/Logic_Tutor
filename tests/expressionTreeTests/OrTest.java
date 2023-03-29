package expressionTreeTests;

import static org.junit.Assert.*;

import expressionTree.*;
import org.junit.Test;

public class OrTest {

	@Test
	public void testOrTrueFalse() {
		Store s = new Store();
		Variable a = Variable.of("A");
		Variable b = Variable.of("B");
		s.addVariable(a, Value.getTrue());
		s.addVariable(b, Value.getFalse());

		Or or =  new Or(a, b);
		assertTrue("Testing or expression when "
				+ "a == true & b == false",
				or.evaluate(s)
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
		assertFalse("Testing or expression when "
				+ "a == false & b == false",
				or.evaluate(s)
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
		assertTrue("Testing or expression when "
				+ "a == true & b == true",
				or.evaluate(s)
				);
	}

}
