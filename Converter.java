import java.util.*;

public class Converter extends ParserHelper {
	private String infix;
	public Converter(String infix) {
		this.infix = infix;
	}
	
	private static int operatorNumber(String number) {
		if(number == null) {
			return -1;
		}
		else {
			switch(number) {
			case "^":
				return 3;
			case "/":
				return 2;
			case "*":
				return 2;
			case "+":
				return 1;
			case "-":
				return 1;
			default:
				return -1;
				
			}
		}
		
	}
	
	
	
	public String toPostFix() {
		char[] infixCharacters = infix.toCharArray();
		List<String> parsedCharacters = parse(infixCharacters);
		String postfix = "";
		LinkedStack<String> operators = new LinkedStack<String>();
		while(!parsedCharacters.isEmpty()) {
			String current = parsedCharacters.get(0);
			if(Character.isDigit(current.charAt(0))) {
				//number just add to postfix
				postfix += current + " ";
			}
			else if(current.equals("(")) {
				operators.push(current);
				//1. open parenthesis: put on stack
			}
			else if(current.equals(")") ) {
				//ONLY POP UNTIL WE SEE (
				while(!(operators.isEmpty())) {
					if(operators.top().equals("(")) {
						operators.pop();
						break;
					}
					else {
						postfix = postfix + operators.pop() + " ";
					}
				}
				//2. closed parenthesis; pop all operators until you find the matching 
				//opening parenthesis. do not add the parenthesis because they don't exist
				//in a postfix expression
			}
			else {
				//come across an operator, this becomes more cases
					if(operators.isEmpty()) {
						//if there's nothing there, you can only push
						operators.push(current);
					}
					else if(operatorNumber(current)>operatorNumber(operators.top())) {
						//higher precedence push
						operators.push(current);
					}
					else {
						//popping in the case of lower precedence
						while (!operators.isEmpty()&& operatorNumber(current)<=operatorNumber(operators.top())){
							postfix += operators.pop()+ " ";
							}
							operators.push(current);
					}
					
				}
			parsedCharacters.remove(parsedCharacters.get(0));
				
			
			}
			
	
		while(!operators.isEmpty()) {
			if(operators.top().equals("(") || operators.top().equals(")")) {
				operators.pop();
			}
			else {
				postfix += operators.pop() + " ";
			}
		}
		//dealing with the stack if it's not empty
		
		return postfix;
	}
	
}
