package expressionTreeTests;

import static org.junit.Assert.*;

import expressionTree.*;
import org.junit.Test;

public class ImplyTest {

	@Test
	public void testingImplyFalseTrue() {
		Store s = new Store();
		Variable a = Variable.of("A");
		Variable b = Variable.of("B");
		s.addVariable(a, Value.getFalse());
		s.addVariable(b, Value.getTrue());

		Imply imply =  new Imply(a, b);
		assertTrue("Testing imply expression when"
				+ "a == false & b ==true",
				imply.evaluate(s));
	}
	
	@Test
	public void testingImplyTrueTrue() {
		Store s = new Store();
		Variable a = Variable.of("A");
		Variable b = Variable.of("B");
		s.addVariable(a, Value.getTrue());
		s.addVariable(b, Value.getTrue());

		Imply imply =  new Imply(a, b);
		assertTrue("Testing imply expression when "
				+ "a == true & b ==true",
				imply.evaluate(s));
	}
	
	@Test
	public void testingImplyFalseFalse() {
		Store s = new Store();
		Variable a = Variable.of("A");
		Variable b = Variable.of("B");
		s.addVariable(a, Value.getFalse());
		s.addVariable(b, Value.getFalse());

		Imply imply =  new Imply(a, b);
		assertTrue("Testing imply expression when "
				+ "a == false & b ==false",
				imply.evaluate(s));
	}
	
	@Test
	public void testingImplyTrueFalse() {
		Store s = new Store();
		Variable a = Variable.of("A");
		Variable b = Variable.of("B");
		s.addVariable(a, Value.getTrue());
		s.addVariable(b, Value.getFalse());

		Imply imply =  new Imply(a, b);
		assertFalse("Testing imply expression when "
				+ "a == true & b ==false",
				imply.evaluate(s));
	}

}
