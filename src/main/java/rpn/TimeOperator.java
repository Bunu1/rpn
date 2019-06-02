package rpn;

import java.util.Stack;

public class TimeOperator implements Operator {
    @Override
    public Stack<String> calculate(Stack<String> stack) throws Exception {
        Double res = Double.parseDouble(stack.pop()) * Double.parseDouble(stack.pop());
        stack.push(Double.toString(res));
        return stack;
    }
}
