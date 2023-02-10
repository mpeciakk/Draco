package mpeciakk;

import mpeciakk.object.DracoBoolean;
import mpeciakk.parser.expression.logical.*;
import mpeciakk.parser.expression.other.BooleanExpression;
import mpeciakk.parser.expression.other.NumberExpression;
import mpeciakk.parser.expression.other.StringExpression;
import mpeciakk.runtime.DracoInterpreter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogicalExpressionTest {

    @Test
    public void and_success() {
        AndExpression expression = new AndExpression(new BooleanExpression(true), new BooleanExpression(true));
        assertEquals(new DracoBoolean(true), expression.evaluate(new DracoInterpreter()));
    }

    @Test
    public void and_failure() {
        AndExpression expression = new AndExpression(new BooleanExpression(true), new BooleanExpression(false));
        assertEquals(new DracoBoolean(false), expression.evaluate(new DracoInterpreter()));
    }

    @Test
    public void equals_success() {
        EqualsExpression expression = new EqualsExpression(new StringExpression("it works!"), new StringExpression("it works!"));
        assertEquals(new DracoBoolean(true), expression.evaluate(new DracoInterpreter()));
    }

    @Test
    public void equals_failure() {
        EqualsExpression expression = new EqualsExpression(new NumberExpression(5), new NumberExpression(4));
        assertEquals(new DracoBoolean(false), expression.evaluate(new DracoInterpreter()));
    }

    @Test
    public void greater_equal_than_success() {
        GreaterEqualThanExpression expression = new GreaterEqualThanExpression(new NumberExpression(5), new NumberExpression(5));
        assertEquals(new DracoBoolean(true), expression.evaluate(new DracoInterpreter()));
    }

    @Test
    public void greater_equal_than_failure() {
        GreaterEqualThanExpression expression = new GreaterEqualThanExpression(new NumberExpression(4), new NumberExpression(5));
        assertEquals(new DracoBoolean(false), expression.evaluate(new DracoInterpreter()));
    }

    @Test
    public void greater_than_success() {
        GreaterThanExpression expression = new GreaterThanExpression(new NumberExpression(6), new NumberExpression(5));
        assertEquals(new DracoBoolean(true), expression.evaluate(new DracoInterpreter()));
    }

    @Test
    public void greater_than_failure() {
        GreaterEqualThanExpression expression = new GreaterEqualThanExpression(new NumberExpression(4), new NumberExpression(5));
        assertEquals(new DracoBoolean(false), expression.evaluate(new DracoInterpreter()));
    }

    @Test
    public void less_equal_than_success() {
        LessEqualThanExpression expression = new LessEqualThanExpression(new NumberExpression(5), new NumberExpression(5));
        assertEquals(new DracoBoolean(true), expression.evaluate(new DracoInterpreter()));
    }

    @Test
    public void less_equal_than_failure() {
        LessEqualThanExpression expression = new LessEqualThanExpression(new NumberExpression(6), new NumberExpression(5));
        assertEquals(new DracoBoolean(false), expression.evaluate(new DracoInterpreter()));
    }

    @Test
    public void less_than_success() {
        LessThanExpression expression = new LessThanExpression(new NumberExpression(4), new NumberExpression(5));
        assertEquals(new DracoBoolean(true), expression.evaluate(new DracoInterpreter()));
    }

    @Test
    public void less_than_failure() {
        LessThanExpression expression = new LessThanExpression(new NumberExpression(6), new NumberExpression(5));
        assertEquals(new DracoBoolean(false), expression.evaluate(new DracoInterpreter()));
    }

    @Test
    public void not_equals_success() {
        NotEqualsExpression expression = new NotEqualsExpression(new NumberExpression(4), new NumberExpression(5));
        assertEquals(new DracoBoolean(true), expression.evaluate(new DracoInterpreter()));
    }

    @Test
    public void not_equals_failure() {
        NotEqualsExpression expression = new NotEqualsExpression(new StringExpression("it works!"), new StringExpression("it works!"));
        assertEquals(new DracoBoolean(false), expression.evaluate(new DracoInterpreter()));
    }

    @Test
    public void or_success() {
        OrExpression expression = new OrExpression(new BooleanExpression(false), new BooleanExpression(true));
        assertEquals(new DracoBoolean(true), expression.evaluate(new DracoInterpreter()));
    }

    @Test
    public void or_failure() {
        OrExpression expression = new OrExpression(new BooleanExpression(false), new BooleanExpression(false));
        assertEquals(new DracoBoolean(false), expression.evaluate(new DracoInterpreter()));
    }
}
