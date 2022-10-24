package Gloria_HW2;
import java.util.*;
//import used for using lists only
public class PostfixCalculator extends ParserHelper {
	private String expression;
	public PostfixCalculator(Converter c) {
		c = new Converter(expression);
		
	}
	
	public double calculate(String postfix) {
		char[] postfixCharacters = postfix.toCharArray();
		List<String> postExpression = parse(postfixCharacters);
		//parsing just like infix
		LinkedStack<Double> numbers = new LinkedStack<Double>();
		//where the numbers go in the stack for calculation
		while(!postExpression.isEmpty()) {
			String firstValue = postExpression.get(0);
			if(Character.isDigit(firstValue.charAt(0))) {
				//if its a number just push
				numbers.push((double)Double.parseDouble(firstValue));
				postExpression.remove(firstValue);
				//looking at the next value
			}
			else {
				double valuePopped = numbers.pop();
				//we take the first two numbers and do the appropriate operations depending on the operator
				//after operating, we push
				if(firstValue.equals("+")) {
					double result = numbers.pop() + valuePopped;
					numbers.push(result);
				}
				else if(firstValue.equals("-")) {
					double result = numbers.pop() - valuePopped;
					numbers.push(result);
				}
				else if(firstValue.equals("*")) {
					double result = numbers.pop() * valuePopped;
					numbers.push(result);
				}
				else if(firstValue.equals("/")) {
					double result = numbers.pop() / valuePopped;
					numbers.push(result);
				}
				else if(firstValue.equals("^")) {
					double result = Math.pow(numbers.pop(), valuePopped);
					numbers.push(result);
				}
				postExpression.remove(postExpression.get(0));
			}
			
			
		}
		return numbers.pop();
	
	
	}
	
	
}
