package rpn;

public class Final implements Consumer {
    private final Bus bus;

    private Double result;

    public Final(Bus bus) {
        this.bus = bus;
    }

    @Override
    public void consume(Message message) {
        FinalMessage finalMessage = (FinalMessage) message;
        result = finalMessage.result();

        System.out.println("RESULT - " + finalMessage.expressionId() + " - " + result);
    }

    public Double getResult() {
        return result;
    }
}
