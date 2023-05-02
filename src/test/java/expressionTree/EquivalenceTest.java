package expressionTree;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class EquivalenceTest {

	@Test
	public void testEquivalenceFalseFalse() {
		Store s = new Store();
		Variable a = Variable.of("A");
		Variable b = Variable.of("B");
		s.addVariable(a, Value.getFalse());
		s.addVariable(b, Value.getFalse());

		Equivalence equivalence =  new Equivalence(a, b);
		assertTrue(equivalence.evaluate(s),
				"Testing equivalence expression"
				+ " when a & b == false"
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
		assertFalse(equivalence.evaluate(s),
				"Testing equivalence expression"
				+ " when a == false & b == true"
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
		assertTrue(equivalence.evaluate(s),
				"Testing equivalence expression"
				+ " when a == true & b == true"
		);
	}

}
