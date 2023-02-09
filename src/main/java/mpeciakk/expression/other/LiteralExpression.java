package mpeciakk.expression.other;

import mpeciakk.runtime.DracoInterpreter;
import mpeciakk.expression.Expression;

public class LiteralExpression extends Expression {

    private final Object value;

    public LiteralExpression(Object value) {
        this.value = value;
    }

    @Override
    public Object evaluate(DracoInterpreter interpreter) {
        return value;
    }

    @Override
    public String toString() {
        return "LiteralExpression{" +
                "value=" + value +
                '}';
    }
}
