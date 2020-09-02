package dglad.calc.model;

import dglad.calc.util.ReversePolishNotationUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Cell {

	private Map<String, Cell> spreadsheet;
	private List<String> expression;
	private Double result;

	public Cell(Map<String, Cell> spreadsheet, List<String> expression) {
		this.spreadsheet = spreadsheet;
		this.expression = expression;
		this.result = null;
	}

	/** recursive result calculation
	 * important: we calculate result once and then cache it for better perfomance
	 * @return calculated value in Double
	 */
	public Double getResult() {
		return (result != null) ? result : ReversePolishNotationUtil.calculate(prepareExpression());
	}

	private List<String> prepareExpression() {
		return expression
				.stream()
				.map(item -> {
					if (spreadsheet.containsKey(item)) {
						item = String.valueOf(spreadsheet.get(item).getResult());
					}
					return item;
				})
				.collect(Collectors.toList());
	}

}
