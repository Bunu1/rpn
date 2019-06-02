package rpn;

import java.util.Stack;

public class DivisionOperator implements Operator {
    @Override
    public Stack<String> calculate(Stack<String> stack) throws Exception {
        Double leftOperand = Double.parseDouble(stack.pop());
        Double RightOperand = Double.parseDouble(stack.pop());
        if(leftOperand == 0) throw new ArithmeticException("Division by 0");

        Double res = (1 / leftOperand) * RightOperand;
        stack.push(Double.toString(res));
        return stack;
    }
}
