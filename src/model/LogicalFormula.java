package model;

import java.io.Serializable;
/**
 * A model class that sets and stores logical formula/expression 
 * @author meize
 *
 */
public class LogicalFormula implements Serializable{

	//fields
	private static final long serialVersionUID = 1L;
	private String formula, result, truthTable;

	//Constructor method
	/**
	 * A constructor method instantiating an empty formula field 
	 */
	public LogicalFormula() {
		formula = "";
		result = "";
		truthTable = "";
	}
	//methods
	/**
	 * @return returns a formula 
	 */
	public String getFormula() {
		return formula;
	}
	public String getResult() {
		return result;
	}
	public String getTruthTable() {
		return truthTable;
	}
	/**
	 * @param accepts a logical expression as a String
	 */
	public void setFormula(String formula) {
		this.formula = formula;
	}
	public void setResult(String result) {
		this.result = result.toUpperCase();
	}
	public void setTruthTable(String truthTable) {
		this.truthTable = truthTable.toUpperCase();
	}
	
	/**
	 * @return returns a String of class LogicalFormula 
	 */
	@Override
	public String toString() {
		return "Logic calculator:[Formula=" + formula + "]";
	}

}
