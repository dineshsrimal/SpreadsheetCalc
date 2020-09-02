package dglad.calc;

import dglad.calc.model.Cell;
import dglad.calc.util.SpreadsheetUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class App {

	public App() {
		super();
	}

	public static void main(String[] args) {
		args = getArgumentsFromFile();
		System.out.println(args[0]);
		try {
			calcSpreadsheet(args).forEach(
					result -> System.out.println(String.format("%.5f", result))
			);
		} catch (Exception e) {
			System.err.println("Wrong data input (possibly cyclic reference)");
			System.exit(1);
		}
	}

	protected static List<Double> calcSpreadsheet(String[] args) {
		Map<String, Cell> spreadsheet = SpreadsheetUtil.createSpreadsheet(args);
		return spreadsheet.values()
				.stream()
				.map(item -> item.getResult())
				.collect(Collectors.toList());
	}

	// you will run my code as "cat input.txt | java remart.calc.App"
	// so I need to read input with input stream reader
	private static String[] getArgumentsFromFile() {
		List<String> argumentsFromFile = new ArrayList<>();
		/*try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(System.in, Charset.defaultCharset()))) {
			String line;
			while ((line = reader.readLine()) != null) {
				argumentsFromFile.add(line);
			}
		} catch (Exception e) {
			System.err.println("Error while reading data from file");
			System.exit(1);
		}
		return argumentsFromFile.toArray(new String[argumentsFromFile.size()]);*/

		argumentsFromFile.add("3 2");
		argumentsFromFile.add("A2");
		argumentsFromFile.add("4 5 *");
		argumentsFromFile.add("A1");
		argumentsFromFile.add("A1 B2 / 2 +");
		argumentsFromFile.add("3");
		argumentsFromFile.add("39 B1 B2 * /");

		return argumentsFromFile.toArray(new String[argumentsFromFile.size()]);
	}

}
