package mpeciakk.parser.expression.math;

import mpeciakk.object.DracoNumber;
import mpeciakk.object.DracoObject;
import mpeciakk.runtime.DracoInterpreter;
import mpeciakk.parser.expression.Expression;

public class SubtractExpression extends Expression {

    private final Expression left;
    private final Expression right;

    public SubtractExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public DracoObject evaluate(DracoInterpreter interpreter) {
        DracoNumber leftValue = (DracoNumber) left.evaluate(interpreter);
        DracoNumber rightValue = (DracoNumber) right.evaluate(interpreter);

        return new DracoNumber(leftValue.getValue() - rightValue.getValue());
    }

    @Override
    public String toString() {
        return "SubtractExpression{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
