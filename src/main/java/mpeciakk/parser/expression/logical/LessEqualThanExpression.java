package mpeciakk.parser.expression.logical;

import mpeciakk.runtime.DracoInterpreter;
import mpeciakk.parser.expression.Expression;

public class LessEqualThanExpression extends Expression {

    private final Expression left;
    private final Expression right;

    public LessEqualThanExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Object evaluate(DracoInterpreter interpreter) {
        Number leftValue = (Number) left.evaluate(interpreter);
        Number rightValue = (Number) right.evaluate(interpreter);

        return leftValue.doubleValue() <= rightValue.doubleValue();
    }

    @Override
    public String toString() {
        return "LessEqualThanExpression{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
