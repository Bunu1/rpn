package rpn;

import java.sql.Time;

public class Operators {
    public Operator findOperator(String token) {
        switch(token){
            case "*":
                return new TimeOperator();
            case "/":
                return new DivisionOperator();
            case "+":
                return new PlusOperator();
            case "-":
                return new MinusOperator();
        }

        return null;
    }
}
