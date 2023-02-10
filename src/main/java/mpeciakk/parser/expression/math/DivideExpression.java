package mpeciakk.parser.expression.math;

import mpeciakk.object.DracoBoolean;
import mpeciakk.object.DracoNumber;
import mpeciakk.object.DracoObject;
import mpeciakk.runtime.DracoInterpreter;
import mpeciakk.parser.expression.Expression;

public class DivideExpression extends Expression {

    private final Expression left;
    private final Expression right;

    public DivideExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public DracoObject evaluate(DracoInterpreter interpreter) {
        DracoNumber leftValue = (DracoNumber) left.evaluate(interpreter);
        DracoNumber rightValue = (DracoNumber) right.evaluate(interpreter);

        return new DracoNumber(leftValue.getValue() / rightValue.getValue());
    }

    @Override
    public String toString() {
        return "DivideExpression{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
