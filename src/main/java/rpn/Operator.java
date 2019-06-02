package rpn;

import java.util.Stack;

public interface Operator {

    Stack<String> calculate(Stack<String> stack) throws Exception;
}
