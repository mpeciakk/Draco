package mpeciakk.expression.logical;

import mpeciakk.runtime.DracoInterpreter;
import mpeciakk.expression.Expression;

import static mpeciakk.old.IfStatement.isTrue;

public class AndExpression extends Expression {

    private final Expression left;
    private final Expression right;

    public AndExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Object evaluate(DracoInterpreter interpreter) {
        Object leftValue = left.evaluate(interpreter);

        if (!isTrue(leftValue)) {
            return leftValue;
        } else {
            return right.evaluate(interpreter);
        }
    }

    @Override
    public String toString() {
        return "AndExpression{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
