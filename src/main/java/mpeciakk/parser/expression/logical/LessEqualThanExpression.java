package mpeciakk.parser.expression.logical;

import mpeciakk.object.DracoBoolean;
import mpeciakk.object.DracoNumber;
import mpeciakk.object.DracoObject;
import mpeciakk.runtime.DracoInterpreter;
import mpeciakk.parser.expression.Expression;

public class LessEqualThanExpression implements Expression {

    private final Expression left;
    private final Expression right;

    public LessEqualThanExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public DracoObject evaluate(DracoInterpreter interpreter) {
        DracoNumber leftValue = (DracoNumber) left.evaluate(interpreter);
        DracoNumber rightValue = (DracoNumber) right.evaluate(interpreter);

        return new DracoBoolean(leftValue.getValue() <= rightValue.getValue());
    }

    @Override
    public String toString() {
        return "LessEqualThanExpression{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
