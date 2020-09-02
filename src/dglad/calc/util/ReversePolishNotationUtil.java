package dglad.calc.util;

import java.util.List;
import java.util.Stack;
import java.util.function.BiFunction;

public class ReversePolishNotationUtil {

	/** Simple RPN calculate method for processing numeric expressions
	 * @param expression: list of arguments in reverse polish notation (stack sorted)
	 * @return calculated value in Double
	 */
	public static Double calculate(List<String> expression) {
		Stack<Double> numbers = new Stack<>();
		expression.stream().forEach(number -> {
			switch (number) {
				case "+":
					evaluate(numbers, (n1, n2) -> n2 + n1);
					break;
				case "-":
					evaluate(numbers, (n1, n2) -> n2 - n1);
					break;
				case "*":
					evaluate(numbers, (n1, n2) -> n2 * n1);
					break;
				case "/":
					evaluate(numbers, (n1, n2) -> n2 / n1);
					break;
				default:
					numbers.push(Double.parseDouble(number));
			}
		});
		return numbers.pop();
	}

	protected static Stack<Double> evaluate(Stack<Double> numbers, BiFunction<Double, Double, Double> operation) {
		numbers.push(operation.apply(numbers.pop(), numbers.pop()));
		return numbers;
	}

}