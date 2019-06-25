package rpn;

import java.util.Stack;

public class DivisionOperator implements Consumer {

    private final Bus bus;

    public DivisionOperator(Bus bus) {
        this.bus = bus;
    }

    @Override
    public void consume(Message message) {
        StackMessage stackMessage = (StackMessage) message;
        Stack<Double> stack = stackMessage.stack();
        String id = stackMessage.expressionId();

        Double leftOperand = stack.pop();
        Double rightOperand = stack.pop();
        if(leftOperand == 0) throw new ArithmeticException("Division by 0");

        Double res = (1 / leftOperand) * rightOperand;
        stack.push(res);

        bus.publish(new StackMessage("result", id, stack));
    }
}
