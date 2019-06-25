package rpn;

class Thread1 extends Thread {

    private Bus bus;
    private ExpressionMessage expressionMessage;

    public Thread1(Bus bus, ExpressionMessage expressionMessage) {
        this.bus = bus;
        this.expressionMessage = expressionMessage;
    }

    public void run() {
        bus.publish(expressionMessage);
    }
}