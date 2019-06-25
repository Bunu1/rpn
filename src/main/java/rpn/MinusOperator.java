package rpn;

import java.util.Stack;

public class MinusOperator implements Consumer {

    private final Bus bus;

    public MinusOperator(Bus bus) {
        this.bus = bus;
    }

    @Override
    public void consume(Message message) {
        StackMessage stackMessage = (StackMessage) message;
        Stack<Double> stack = stackMessage.stack();
        String id = stackMessage.expressionId();

        Double res = -stack.pop() + stack.pop();
        stack.push(res);

        bus.publish(new StackMessage("result", id, stack));
    }
}
