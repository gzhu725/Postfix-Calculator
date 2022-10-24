package Gloria_HW2;
import java.util.*;
public class Tester extends ParserHelper {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Type your infix expression:");
		String infix = scanner.nextLine();
		Converter converter = new Converter(infix);
		String post = converter.toPostFix();
		PostfixCalculator p = new PostfixCalculator(converter);
		System.out.print("converted to postfix: " + post);
		System.out.println();
		System.out.println("answer is " + p.calculate(post));
		
	}
}
