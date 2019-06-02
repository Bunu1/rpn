package rpn;

import org.junit.Rule;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;
import org.junit.rules.ExpectedException;

import java.util.Stack;

import static org.assertj.core.api.Assertions.assertThat;
import static rpn.CLI.evaluate;


public class CLITest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void should_evaluate_single_digit_constant() {
        Stack stack = evaluate("5");

        assertThat(stack.pop()).isEqualTo("5");
    }

    @Test
    public void should_evaluate_multiple_digits_constant() {
        Stack stack = evaluate("17");
        assertThat(stack.pop()).isEqualTo("17");
    }

    @Test
    public void should_evaluate_simple_addition() {
        Stack stack = evaluate("17 5 +");
        assertThat(stack.pop()).isEqualTo("22.0");
    }

    @Test
    public void should_evaluate_more_complex_addition() {
        Stack stack = evaluate("2 3 5 + +");
        assertThat(stack.pop()).isEqualTo("10.0");
    }

    @Test
    public void should_evaluate_simple_subtraction() {
        Stack stack = evaluate("17 5 -");
        assertThat(stack.pop()).isEqualTo("12.0");
    }

    @Test
    public void should_evaluate_more_complex_subtraction() {
        Stack stack = evaluate("2 3 5 - -");
        assertThat(stack.pop()).isEqualTo("4.0");
    }

    @Test
    public void should_evaluate_simple_time() {
        Stack stack = evaluate("17 5 *");
        assertThat(stack.pop()).isEqualTo("85.0");
    }

    @Test
    public void should_evaluate_more_complex_time() {
        Stack stack = evaluate("2 3 5 * *");
        assertThat(stack.pop()).isEqualTo("30.0");
    }

    @Test
    public void should_evaluate_simple_division() {
        Stack stack = evaluate("17 5 /");
        assertThat(stack.pop()).isEqualTo("3.4000000000000004");
    }

    @Test
    public void should_evaluate_more_complex_division() {
        Stack stack = evaluate("2 20 5 / /");
        assertThat(stack.pop()).isEqualTo("0.5");
    }

    @Test
    public void should_evaluate_simple_negative() {
        Stack stack = evaluate("17 -5 /");
        assertThat(stack.pop()).isEqualTo("-3.4000000000000004");
    }

    @Test
    public void should_evaluate_more_complex_negative() {
        Stack stack = evaluate("-2 20 -5 / /");
        assertThat(stack.pop()).isEqualTo("0.5");
    }

    @Test
    public void should_evaluate_simple_decimal() {
        Stack stack = evaluate("17.5 5 /");
        assertThat(stack.pop()).isEqualTo("3.5");
    }

    @Test
    public void should_evaluate_more_complex_decimal() {
        Stack stack = evaluate("2 20.6 5 + *");
        assertThat(stack.pop()).isEqualTo("51.2");
    }

    @Test
    public void should_evaluate_varied_calculation() {
        Stack stack = evaluate("20 5 /");
        assertThat(stack.pop()).isEqualTo("4.0");
        stack = evaluate("5 2 3 + -");
        assertThat(stack.pop()).isEqualTo("0.0");
        stack = evaluate("4 2 + 3 -");
        assertThat(stack.pop()).isEqualTo("3.0");
        stack = evaluate("3 5 8 * 7 + *");
        assertThat(stack.pop()).isEqualTo("141.0");
    }

    @Test
    public void should_throw_error_division_by_zero (){
        Stack stack = evaluate("7 2 - 3 4");
        assertThat(stack.pop()).isEqualTo("4");
        assertThat(stack.pop()).isEqualTo("3");
        assertThat(stack.pop()).isEqualTo("5.0");
    }
}