package expressionTree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AndTest {
	@Test
	public void testAndTrueFalse() {
		Store s = new Store();
		Variable a = Variable.of("A");
		Variable b = Variable.of("B");
		s.addVariable(a, Value.getTrue());
		s.addVariable(b, Value.getFalse());

		And and =  new And(a, b);
		assertFalse(and.evaluate(s), "Testing and expression when"
				+ " a == true & b == false");
	}
	
	@Test
	public void testAndFalseFalse() {
		Store s = new Store();
		Variable a = Variable.of("A");
		Variable b = Variable.of("B");
		s.addVariable(a, Value.getFalse());
		s.addVariable(b, Value.getFalse());

		And and =  new And(a, b);
		assertFalse(and.evaluate(s), "Testing and expression when"
				+ " a == false & b == false");
	}
	
	@Test
	public void testAndTrueTrue() {
		Store s = new Store();
		Variable a = Variable.of("A");
		Variable b = Variable.of("B");
		s.addVariable(a, Value.getTrue());
		s.addVariable(b, Value.getTrue());


		And and =  new And(a, b);
		assertTrue(and.evaluate(s), "Testing and expression when"
				+ " a == true & b == true");
	}

}
