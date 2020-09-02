package dglad.calc.util;

import dglad.calc.model.Cell;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SpreadsheetUtil {

	/** Creates spreadsheet with no precalculated values from args
	 * @param args: application arguments,
	 * args[0] must contain dimension, others must contain cell expressions
	 * @return calculated value in Double
	 */
	public static Map<String, Cell> createSpreadsheet(String[] args) {
		String[] dimension = args[0].split(" ");
		int rows = Integer.valueOf(dimension[1]);
		int columns = Integer.valueOf(dimension[0]);
		Map<String, Cell> map = new LinkedHashMap<>(rows * columns);
		String row;
		for (int r = 1; r < rows + 1; r++) {
			row = getCharForNumber(r);
			for (int c = 1; c < columns + 1; c++) {
				map.put(row + c, createCell(map, args[columns * (r - 1) + c]));
			}
	}
		return map;
	}

	private static Cell createCell(Map<String, Cell> spreadsheet, String expression) {
		List<String> expressionAsList = Arrays.asList(expression.split(" "));
		return new Cell(spreadsheet, expressionAsList);
	}

	private static String getCharForNumber(int i) {
		return i > 0 && i < 27 ? String.valueOf((char)(i + 64)) : null;
	}
}
