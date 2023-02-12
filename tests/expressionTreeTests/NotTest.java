package expressionTreeTests;

import static org.junit.Assert.*;

import expressionTree.*;
import org.junit.Test;

public class NotTest {

	@Test
	public void testNotTrue() {
		Store s = new Store();
		Variable a = new Variable("A");
		s.addVariable(a, Value.getTrue());
		Not not =  new Not(a);
		assertFalse("Testing not expression when"
				+ "a == true", 
				not.evaluate(s));
	}
	
	@Test
	public void testNotFalse() {
		Store s = new Store();
		Variable a = new Variable("A");
		s.addVariable(a, Value.getFalse());
		Not not =  new Not(a);
		assertTrue("Testing not expression when"
				+ "a == false", 
				not.evaluate(s));
	}
	
	@Test
	public void testDoubleNot() {
		Store s = new Store();
		Variable a = new Variable("A");
		s.addVariable(a, Value.getTrue());
		Not not =  new Not(a);
		Not not1 =  new Not(not);

		assertTrue("Testing a double not expression",
				not1.evaluate(s));
	}

}
