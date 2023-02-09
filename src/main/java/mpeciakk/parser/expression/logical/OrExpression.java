package mpeciakk.parser.expression.logical;

import mpeciakk.parser.expression.Expression;
import mpeciakk.runtime.DracoInterpreter;

import static mpeciakk.parser.syntax.builtin.IfStatement.isTrue;

public class OrExpression extends Expression {

    private final Expression left;
    private final Expression right;

    public OrExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Object evaluate(DracoInterpreter interpreter) {
        Object leftValue = left.evaluate(interpreter);

        if (isTrue(leftValue)) {
            return leftValue;
        } else {
            return right.evaluate(interpreter);
        }
    }

    @Override
    public String toString() {
        return "OrExpression{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
