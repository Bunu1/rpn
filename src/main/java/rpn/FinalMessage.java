package rpn;

public class FinalMessage implements Message {
    private String expressionId;
    private Double result;

    public FinalMessage(String expressionId, Double result) {
        this.expressionId = expressionId;
        this.result = result;
    }

    @Override
    public String eventType() { return "final"; }

    public Double result() {
        return result;
    }

    public String expressionId() {
        return expressionId;
    }

}
