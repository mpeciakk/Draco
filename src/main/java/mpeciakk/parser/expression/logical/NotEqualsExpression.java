package mpeciakk.parser.expression.logical;

import mpeciakk.object.DracoBoolean;
import mpeciakk.object.DracoObject;
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
    public DracoObject evaluate(DracoInterpreter interpreter) {
        Object leftValue = left.evaluate(interpreter);
        Object rightValue = right.evaluate(interpreter);

        return new DracoBoolean(!leftValue.equals(rightValue));
    }

    @Override
    public String toString() {
        return "NotEqualsExpression{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
