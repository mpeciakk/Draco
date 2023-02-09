package mpeciakk.expression.math;

import mpeciakk.runtime.DracoInterpreter;
import mpeciakk.expression.Expression;

public class MultiplyExpression extends Expression {

    private final Expression left;
    private final Expression right;

    public MultiplyExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Object evaluate(DracoInterpreter interpreter) {
        Number leftValue = (Number) left.evaluate(interpreter);
        Number rightValue = (Number) right.evaluate(interpreter);

        return leftValue.doubleValue() * rightValue.doubleValue();
    }

    @Override
    public String toString() {
        return "MultiplyExpression{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
