package mpeciakk.parser.expression.other;

import mpeciakk.object.DracoBoolean;
import mpeciakk.object.DracoString;
import mpeciakk.parser.expression.Expression;
import mpeciakk.runtime.DracoInterpreter;

public class BooleanExpression extends Expression {

    private final boolean value;

    public BooleanExpression(boolean value) {
        this.value = value;
    }

    @Override
    public DracoBoolean evaluate(DracoInterpreter interpreter) {
        return new DracoBoolean(value);
    }

    @Override
    public String toString() {
        return "LiteralExpression{" +
                "value=" + value +
                '}';
    }
}
