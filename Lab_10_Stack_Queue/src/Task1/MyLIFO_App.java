package Task1;

import java.util.Stack;

public class MyLIFO_App {
	// This method reserves the given array
	public static <E> void reserve(E[] array) {
		Stack<E> stack = new Stack<>();
		for (int i = 0; i < array.length; i++) {
			stack.push(array[i]);
		}
		for (int j = 0; j < array.length; j++) {
			array[j] = stack.pop();
		}
		for (E e : array) {
			System.out.print(e + " ");
		}
	}

	// This method checks the correctness of the
	// given input
	// i.e. ()(())[]{(())} ==> true, ){[]}() ==>
	// false
	public static boolean isCorrect(String input) {

		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c == '(' || c == '[' || c == '{') {
				stack.push(c);
			} else {
				if (stack.isEmpty()) {
					return false;
				}
				char openPeek = stack.peek();
				if ((c == ')' && openPeek == '(') || (c == ']' && openPeek == '[') || (c == '}' && openPeek == '{')) {
					stack.pop();
				}
			}
		}
		return stack.isEmpty() ? true : false;
	}

	// This method evaluates the value of an
	// expression
	// i.e. 51 + (54 *(3+2)) = 321
	public static String insertBlanks(String expression) {
		String result = "";
		for (int i = 0; i < expression.length(); i++) {
			char c = expression.charAt(i);
			if (c == '(' || c == ')' || c == '+'
			|| c == '-' || c == '*' || c == '/') {
				result+=" "+ c + " ";
			}else {
				result += c;
			}
		}
		return result;
	}
	public static void processAnOperator(Stack<Integer> operandStack, Stack<Character> operatorStack) {
		char op = operatorStack.pop();
		int op1 = operandStack.pop();
		int op2 = operandStack.pop();
		if(op == '+') {
			operandStack.push(op2 + op1);
		}else if(op == '-') {
			operandStack.push(op2 - op1);
		}else if(op == '/') {
			operandStack.push(op2 / op1);
		}else if(op == '*') {
			operandStack.push(op2 * op1);
		}
	}

	public static int evluateExpression(String expression) {
		Stack<Integer> operandStack = new Stack<>();
		Stack<Character> operatorStack = new Stack<>();
		
		expression = insertBlanks(expression);
		
		String[] tokens = expression.split(" ");
		 
		//quet tokens
		for(String token: tokens) {
			if(token.length()==0) {
				continue;
			}else if(token.charAt(0)=='+'|| token.charAt(0)=='-'){
				while(!operatorStack.isEmpty()&&
						 (operatorStack.peek() == '+' || 
				          operatorStack.peek() == '-' ||
				          operatorStack.peek() == '*' ||
				          operatorStack.peek() == '/')) {
					processAnOperator(operandStack, operatorStack);
					
				}
				operatorStack.push(token.charAt(0));
			}
			else if(token.charAt(0)=='*'||token.charAt(0)=='/') {
				
				while(!operatorStack.isEmpty()&&
						(operatorStack.peek() == '*' ||
				          operatorStack.peek() == '/')) {
					processAnOperator(operandStack, operatorStack);
				}
				operatorStack.push(token.charAt(0));
			}
			else if(token.trim().charAt(0)=='(') {
				operatorStack.push('(');
			}
			 else if (token.trim().charAt(0) == ')') {
			        while (operatorStack.peek() != '(') {
			          processAnOperator(operandStack, operatorStack);
			        }
			        operatorStack.pop();
		     }
			 else {
			        operandStack.push(Integer.valueOf(token));
			      }
			    }
			 while (!operatorStack.isEmpty()) {
			   processAnOperator(operandStack, operatorStack);
			 }
    return operandStack.pop();
	}

	public static void main(String[] args) {
		Integer[] array = { 1, 2, 3, 4 };
		// reserve(array);
		//System.out.println(isCorrect("[(][]"));
		//System.out.println(insertBlanks("(hieu)+dinh"));
		System.out.println(evluateExpression("2*(2+3)"));
		
	}

}
