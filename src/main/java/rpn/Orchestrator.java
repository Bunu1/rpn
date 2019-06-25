package rpn;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Orchestrator implements Consumer {
    private Map<String, Stack<Double>> map = new HashMap<>();
    private Map<String, Boolean> waiter = new HashMap<>();
    private final Bus bus;

    private boolean isWaiting = true;

    public Orchestrator(Bus bus) {
        this.bus = bus;
    }

    @Override
    public void consume(Message message) {
        if(message.eventType().equals("result")) {
            StackMessage stackMessage = (StackMessage) message;
            String id = stackMessage.expressionId();

            map.put(id, stackMessage.stack());
            waiter.put(id, false);

        } else if(message.eventType().equals("token")) {
            TokenMessage tokenMessage = (TokenMessage) message;
            String expression = tokenMessage.expression();
            String id = tokenMessage.expressionId();
            Stack<Double> s = map.getOrDefault(id, null);
            if(s != null) {
                if(expression.matches("-?[0-9]+(.[0-9]+)?")) {
                    s.push(Double.parseDouble(expression));
                    map.put(id, s);
                    waiter.put(id, true);
                } else {
                    if("eoe".equals(expression)) {
                        bus.publish(new FinalMessage(id, s.pop()));
                    } else {
                        //new Waiter().start();
                        bus.publish(new StackMessage(expression, id, s));
                    }
                }
            } else {
                s = new Stack<>();
                s.push(Double.parseDouble(expression));
                map.put(id, s);
                waiter.put(id, true);
            }
        }
    }
}


class Waiter extends Thread {
    private Bus bus;
    private StackMessage stackMessage;

    public Waiter(Bus bus, StackMessage stackMessage) {
        this.bus = bus;
        this.stackMessage = stackMessage;
    }

    public void run() {
        synchronized (this) {
            try {
                this.wait(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}