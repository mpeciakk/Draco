package mpeciakk.expression.logical;

import mpeciakk.runtime.DracoInterpreter;
import mpeciakk.expression.Expression;

public class EqualsExpression extends Expression {

    private final Expression left;
    private final Expression right;

    public EqualsExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Object evaluate(DracoInterpreter interpreter) {
        Object leftValue = left.evaluate(interpreter);
        Object rightValue = right.evaluate(interpreter);

        return leftValue.equals(rightValue);
    }

    @Override
    public String toString() {
        return "EqualsExpression{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
