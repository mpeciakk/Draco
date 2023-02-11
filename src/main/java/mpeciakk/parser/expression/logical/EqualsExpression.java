package mpeciakk.parser.expression.logical;

import mpeciakk.object.DracoBoolean;
import mpeciakk.object.DracoObject;
import mpeciakk.parser.expression.Expression;
import mpeciakk.runtime.DracoInterpreter;

public class EqualsExpression implements Expression {

    private final Expression left;
    private final Expression right;

    public EqualsExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public DracoObject evaluate(DracoInterpreter interpreter) {
        DracoObject leftValue = left.evaluate(interpreter);
        DracoObject rightValue = right.evaluate(interpreter);

        return new DracoBoolean(leftValue.equals(rightValue));
    }

    @Override
    public String toString() {
        return "EqualsExpression{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
