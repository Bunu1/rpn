package rpn;

import java.util.Stack;

public class StackMessage implements Message {

    private String event;
    private Stack<Double> stack;
    private String expressionId;

    public StackMessage(String event, String expressionId, Stack<Double> stack) {
        this.event = event;
        this.expressionId = expressionId;
        this.stack = stack;
    }

    @Override
    public String eventType() {
        return event;
    }

    public Stack<Double> stack() {
        return stack;
    }

    public String expressionId() {
        return expressionId;
    }
}
