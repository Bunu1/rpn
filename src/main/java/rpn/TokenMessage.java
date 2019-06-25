package rpn;

public class TokenMessage extends ExpressionMessage {

    public TokenMessage(String expressionId, String expression) {
        super(expressionId, expression);
    }

    @Override
    public String eventType() {
        return "token";
    }
}
