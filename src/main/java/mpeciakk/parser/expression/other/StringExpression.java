package mpeciakk.parser.expression.other;

import mpeciakk.object.DracoString;
import mpeciakk.parser.expression.Expression;
import mpeciakk.runtime.DracoInterpreter;

public class StringExpression extends Expression {

    private final String value;

    public StringExpression(String value) {
        this.value = value;
    }

    @Override
    public DracoString evaluate(DracoInterpreter interpreter) {
        return new DracoString(value);
    }

    @Override
    public String toString() {
        return "LiteralExpression{" +
                "value=" + value +
                '}';
    }
}
