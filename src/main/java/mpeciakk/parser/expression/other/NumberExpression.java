package mpeciakk.parser.expression.other;

import mpeciakk.object.DracoNumber;
import mpeciakk.object.DracoString;
import mpeciakk.parser.expression.Expression;
import mpeciakk.runtime.DracoInterpreter;

public class NumberExpression extends Expression {

    private final double value;

    public NumberExpression(double value) {
        this.value = value;
    }

    @Override
    public DracoNumber evaluate(DracoInterpreter interpreter) {
        return new DracoNumber(value);
    }

    @Override
    public String toString() {
        return "LiteralExpression{" +
                "value=" + value +
                '}';
    }
}
