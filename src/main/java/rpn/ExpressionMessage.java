package rpn;

import java.util.UUID;

public class ExpressionMessage implements Message {
    private final String expression;
    private final String expressionId;

    public ExpressionMessage(String expression) {
        this(UUID.randomUUID().toString(),  expression);
    }

    public ExpressionMessage(String expressionId, String expression) {
        this.expressionId = expressionId;
        this.expression = expression;
    }

    @Override
    public String eventType() {
        return "expression";
    }

    public String expression() {
        return expression;
    }

    public String expressionId() {
        return expressionId;
    }
}
