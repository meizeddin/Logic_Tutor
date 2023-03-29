package expressionTreeTests;

import static org.junit.Assert.*;

import expressionTree.*;
import org.junit.Test;

public class AndTest {
	@Test
	public void testAndTrueFalse() {
		Store s = new Store();
		Variable a = Variable.of("A");
		Variable b = Variable.of("B");
		s.addVariable(a, Value.getTrue());
		s.addVariable(b, Value.getFalse());

		And and =  new And(a, b);
		assertFalse("Testing and expression when"
				+ " a == true & b == false",
				and.evaluate(s));
	}
	
	@Test
	public void testAndFalseFalse() {
		Store s = new Store();
		Variable a = Variable.of("A");
		Variable b = Variable.of("B");
		s.addVariable(a, Value.getFalse());
		s.addVariable(b, Value.getFalse());

		And and =  new And(a, b);
		assertFalse("Testing and expression when"
				+ " a == false & b == false",
				and.evaluate(s));
	}
	
	@Test
	public void testAndTrueTrue() {
		Store s = new Store();
		Variable a = Variable.of("A");
		Variable b = Variable.of("B");
		s.addVariable(a, Value.getTrue());
		s.addVariable(b, Value.getTrue());


		And and =  new And(a, b);
		assertTrue("Testing and expression when"
				+ " a == true & b == true",
				and.evaluate(s));
	}

}
