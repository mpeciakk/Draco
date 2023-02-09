package mpeciakk.parser.expression.logical;

import mpeciakk.parser.expression.Expression;
import mpeciakk.runtime.DracoInterpreter;

public class GreaterThanExpression extends Expression {

    private final Expression left;
    private final Expression right;

    public GreaterThanExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Object evaluate(DracoInterpreter interpreter) {
        Number leftValue = (Number) left.evaluate(interpreter);
        Number rightValue = (Number) right.evaluate(interpreter);

        return leftValue.doubleValue() > rightValue.doubleValue();
    }

    @Override
    public String toString() {
        return "GreaterThanExpression{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
