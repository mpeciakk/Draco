package mpeciakk;

import mpeciakk.object.DracoNumber;
import mpeciakk.object.DracoString;
import mpeciakk.parser.expression.math.*;
import mpeciakk.parser.expression.other.NumberExpression;
import mpeciakk.parser.expression.other.StringExpression;
import mpeciakk.runtime.DracoInterpreter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathExpressionTest {

    @Test
    public void addition() {
        AddExpression expression = new AddExpression(new NumberExpression(5), new NumberExpression(4));
        assertEquals(new DracoNumber(9.0d), expression.evaluate(new DracoInterpreter()));
    }

    @Test
    public void additionStrings() {
        AddExpression expression = new AddExpression(new StringExpression("it "), new StringExpression("works"));
        assertEquals(new DracoString("it works"), expression.evaluate(new DracoInterpreter()));
    }

    @Test
    public void additionStringAndNumber() {
        AddExpression expression = new AddExpression(new StringExpression("works"), new NumberExpression(4));
        assertEquals(new DracoString("works" + 4.0d), expression.evaluate(new DracoInterpreter()));
    }

    @Test
    public void additionNumberAndString() {
        AddExpression expression = new AddExpression(new NumberExpression(4), new StringExpression("works"));
        assertEquals(new DracoString(4.0d + "works"), expression.evaluate(new DracoInterpreter()));
    }

    @Test
    public void division() {
        DivideExpression expression = new DivideExpression(new NumberExpression(5), new NumberExpression(4));
        assertEquals(new DracoNumber(5.0d / 4.0d), expression.evaluate(new DracoInterpreter()));
    }

    @Test
    public void subtraction() {
        SubtractExpression expression = new SubtractExpression(new NumberExpression(5), new NumberExpression(4));
        assertEquals(new DracoNumber(5.0d - 4.0d), expression.evaluate(new DracoInterpreter()));
    }

    @Test
    public void multiplication() {
        MultiplyExpression expression = new MultiplyExpression(new NumberExpression(5), new NumberExpression(4));
        assertEquals(new DracoNumber(5.0d * 4.0d), expression.evaluate(new DracoInterpreter()));
    }

    @Test
    public void minus() {
        MinusExpression expression = new MinusExpression(new NumberExpression(5));
        assertEquals(new DracoNumber(-5.0d), expression.evaluate(new DracoInterpreter()));
    }
}
