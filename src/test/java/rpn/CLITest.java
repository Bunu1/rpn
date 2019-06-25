package rpn;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;
import static rpn.CLI.evaluate;


public class CLITest {

    final double EPSILON = 0.0000000001;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void should_evaluate_single_digit_constant() {
        Double dbl = evaluate("5");
        boolean bool = Math.abs(dbl - 5.0) < EPSILON;
        assertThat(bool).isEqualTo(true);
    }

    @Test
    public void should_evaluate_multiple_digits_constant() {
        Double dbl = evaluate("17");
        assertThat(Math.abs(dbl - 17) < EPSILON).isEqualTo(true);
    }

    @Test
    public void should_evaluate_simple_addition() {
        Double dbl = evaluate("17 5 +");
        assertThat(Math.abs(dbl - 22) < EPSILON).isEqualTo(true);
    }

    @Test
    public void should_evaluate_more_complex_addition() {
        Double dbl = evaluate("2 3 5 + +");
        assertThat(Math.abs(dbl - 10.0) < EPSILON).isEqualTo(true);
    }

    @Test
    public void should_evaluate_simple_subtraction() {
        Double dbl = evaluate("17 5 -");
        assertThat(Math.abs(dbl - 12.0) < EPSILON).isEqualTo(true);
    }

    @Test
    public void should_evaluate_more_complex_subtraction() {
        Double dbl = evaluate("2 3 5 - -");
        assertThat(Math.abs(dbl - 4.0) < EPSILON).isEqualTo(true);
    }

    @Test
    public void should_evaluate_simple_time() {
        Double dbl = evaluate("17 5 *");
        assertThat(Math.abs(dbl - 85.0) < EPSILON).isEqualTo(true);
    }

    @Test
    public void should_evaluate_more_complex_time() {
        Double dbl = evaluate("2 3 5 * *");
        assertThat(Math.abs(dbl - 30.0) < EPSILON).isEqualTo(true);
    }

    @Test
    public void should_evaluate_simple_division() {
        Double dbl = evaluate("17 5 /");
        assertThat(Math.abs(dbl - 3.4) <= EPSILON).isEqualTo(true);
    }

    @Test
    public void should_evaluate_more_complex_division() {
        Double dbl = evaluate("2 20 5 / /");
        assertThat(Math.abs(dbl - 0.5) < EPSILON).isEqualTo(true);
    }

    @Test
    public void should_evaluate_simple_negative() {
        Double dbl = evaluate("17 -5 /");
        assertThat(Math.abs(dbl + 3.4) < EPSILON).isEqualTo(true);
    }

    @Test
    public void should_evaluate_more_complex_negative() {
        Double dbl = evaluate("-2 20 -5 / /");
        assertThat(Math.abs(dbl - 0.5) < EPSILON).isEqualTo(true);
    }

    @Test
    public void should_evaluate_simple_decimal() {
        Double dbl = evaluate("17.5 5 /");
        assertThat(Math.abs(dbl - 3.5) < EPSILON).isEqualTo(true);
    }

    @Test
    public void should_evaluate_more_complex_decimal() {
        Double dbl = evaluate("2 20.6 5 + *");
        assertThat(Math.abs(dbl - 51.2) < EPSILON).isEqualTo(true);
    }

    @Test
    public void should_evaluate_varied_calculation() {
        Double dbl = evaluate("20 5 /");
        assertThat(Math.abs(dbl - 4.0) < EPSILON).isEqualTo(true);
        dbl = evaluate("5 2 3 + -");
        assertThat(Math.abs(dbl - 0.0) < EPSILON).isEqualTo(true);
        dbl = evaluate("4 2 + 3 -");
        assertThat(Math.abs(dbl - 3.0) < EPSILON).isEqualTo(true);
        dbl = evaluate("3 5 8 * 7 + *");
        assertThat(Math.abs(dbl - 141.0) < EPSILON).isEqualTo(true);
    }

    //@Test
    public void should_throw_error_division_by_zero (){
        Double dbl = evaluate("7 2 - 3 4");
        assertThat(dbl).isEqualTo("4");
        assertThat(dbl).isEqualTo("3");
        assertThat(dbl).isEqualTo("5.0");
    }
}