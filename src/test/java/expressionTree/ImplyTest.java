package expressionTree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ImplyTest {

	@Test
	public void testingImplyFalseTrue() {
		Store s = new Store();
		Variable a = Variable.of("A");
		Variable b = Variable.of("B");
		s.addVariable(a, Value.getFalse());
		s.addVariable(b, Value.getTrue());

		Imply imply =  new Imply(a, b);
		assertTrue(imply.evaluate(s),
				"Testing imply expression when"
				+ "a == false & b ==true"
		);
	}
	
	@Test
	public void testingImplyTrueTrue() {
		Store s = new Store();
		Variable a = Variable.of("A");
		Variable b = Variable.of("B");
		s.addVariable(a, Value.getTrue());
		s.addVariable(b, Value.getTrue());

		Imply imply =  new Imply(a, b);
		assertTrue(imply.evaluate(s),
				"Testing imply expression when "
				+ "a == true & b ==true"
		);
	}
	
	@Test
	public void testingImplyFalseFalse() {
		Store s = new Store();
		Variable a = Variable.of("A");
		Variable b = Variable.of("B");
		s.addVariable(a, Value.getFalse());
		s.addVariable(b, Value.getFalse());

		Imply imply =  new Imply(a, b);
		assertTrue(imply.evaluate(s),
				"Testing imply expression when "
				+ "a == false & b ==false"
		);
	}
	
	@Test
	public void testingImplyTrueFalse() {
		Store s = new Store();
		Variable a = Variable.of("A");
		Variable b = Variable.of("B");
		s.addVariable(a, Value.getTrue());
		s.addVariable(b, Value.getFalse());

		Imply imply =  new Imply(a, b);
		assertFalse(imply.evaluate(s),
				"Testing imply expression when "
				+ "a == true & b ==false"
		);
	}

}
