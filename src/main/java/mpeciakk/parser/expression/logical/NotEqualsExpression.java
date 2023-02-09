package mpeciakk.parser.expression.logical;

import mpeciakk.parser.expression.Expression;
import mpeciakk.runtime.DracoInterpreter;

public class NotEqualsExpression extends Expression {

    private final Expression left;
    private final Expression right;

    public NotEqualsExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Object evaluate(DracoInterpreter interpreter) {
        Object leftValue = left.evaluate(interpreter);
        Object rightValue = right.evaluate(interpreter);

        return !leftValue.equals(rightValue);
    }

    @Override
    public String toString() {
        return "NotEqualsExpression{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
