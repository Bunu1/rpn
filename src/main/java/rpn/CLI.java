package rpn;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CLI {
    public static void main(String[] args) {
        String expression = Stream.of(args).collect(Collectors.joining(" "));
        evaluate(expression);
    }

    public static double evaluate(String expression) {

        InMemoryBus bus = new InMemoryBus();
        bus.subscribe("expression", new TokenizerConsumer(bus));
        Orchestrator orchestrator = new Orchestrator(bus);
        bus.subscribe("token", orchestrator);
        bus.subscribe("result", orchestrator);
        bus.subscribe("+", new PlusOperator(bus));
        bus.subscribe("-", new MinusOperator(bus));
        bus.subscribe("*", new TimeOperator(bus));
        bus.subscribe("/", new DivisionOperator(bus));
        Final fnl = new Final(bus);
        bus.subscribe("final", fnl);

        bus.publish(new ExpressionMessage(expression + " eoe"));
        //bus.publish(new ExpressionMessage("4 2 + eoe"));
        //bus.publish(new ExpressionMessage("17 -5 / eoe"));

        // Use this instead if you want to use thread, be careful timeout is not implemented !
        /*Thread1 t1 = new Thread1(bus, new ExpressionMessage("4 2 + 3 - eoe"));
        Thread1 t2 = new Thread1(bus, new ExpressionMessage("3 5 8 * 7 + * eoe"));

        t1.start();
        t2.start();*/

        return fnl.getResult();
    }
}
