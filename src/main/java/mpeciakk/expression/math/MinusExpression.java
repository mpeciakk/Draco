package mpeciakk.expression.math;

import mpeciakk.runtime.DracoInterpreter;
import mpeciakk.expression.Expression;

public class MinusExpression extends Expression {

    private final Expression expression;

    public MinusExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public Object evaluate(DracoInterpreter interpreter) {
        Number value = (Number) expression.evaluate(interpreter);

        return -value.doubleValue();
    }

    @Override
    public String toString() {
        return "MinusExpression{" +
                "expression=" + expression +
                '}';
    }
}
