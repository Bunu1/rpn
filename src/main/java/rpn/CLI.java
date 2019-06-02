package rpn;

import java.util.Deque;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CLI {
    public static void main(String []args) {
        String expression = Stream.of(args).collect(Collectors.joining(" "));
        Stack<String> stack = evaluate(expression);
        formatStack(stack);
    }

    public static Stack<String> evaluate(String expression) {
        Stack<String> stack = new Stack<>();
        Tokenizer tokenizer = new Tokenizer();

        StringTokenizer stringTokenizer = tokenizer.tokenize(expression);
        Operator operator;
        Operators op = new Operators();

        try {
            while(stringTokenizer.hasMoreTokens()) {
                String token = stringTokenizer.nextToken();
                operator = op.findOperator(token);
                if(operator != null)
                    stack = operator.calculate(stack);
                else
                    stack.push(token);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stack;
    }

    public static String formatStack(Stack<String> stack) {
        Stack<String> invertedStack = new Stack<>();

        while(stack.size() != 0) {
            invertedStack.push(stack.pop());
        }

        String result = "";
        while(invertedStack.size() != 0) {
            result += invertedStack.pop() + " ";
        }

        return result.trim();
    }
}
