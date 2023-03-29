package expressionTreeTests;

import static org.junit.Assert.*;

import expressionTree.*;
import org.junit.Test;

public class EquivalenceTest {

	@Test
	public void testEquivalenceFalseFalse() {
		Store s = new Store();
		Variable a = Variable.of("A");
		Variable b = Variable.of("B");
		s.addVariable(a, Value.getFalse());
		s.addVariable(b, Value.getFalse());

		Equivalence equivalence =  new Equivalence(a, b);
		assertTrue("Testing equivalence expression"
				+ " when a & b == false",
				equivalence.evaluate(s)
				);
	}
	
	@Test
	public void testEquivalenceFalseTrue() {
		Store s = new Store();
		Variable a = Variable.of("A");
		Variable b = Variable.of("B");
		s.addVariable(a, Value.getFalse());
		s.addVariable(b, Value.getTrue());

		Equivalence equivalence =  new Equivalence(a, b);
		assertFalse(
				"Testing equivalence expression"
				+ " when a == false & b == true", 
				equivalence.evaluate(s)
				);
	}
	
	@Test
	public void testEquivalenceTrueTrue() {
		Store s = new Store();
		Variable a = Variable.of("A");
		Variable b = Variable.of("B");
		s.addVariable(a, Value.getTrue());
		s.addVariable(b, Value.getTrue());

		Equivalence equivalence =  new Equivalence(a, b);
		assertTrue(
				"Testing equivalence expression"
				+ " when a == true & b == true", 
				equivalence.evaluate(s)
				);
	}

}
