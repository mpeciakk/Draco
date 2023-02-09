package mpeciakk.parser.expression.math;

import mpeciakk.runtime.DracoInterpreter;
import mpeciakk.parser.expression.Expression;

public class AddExpression extends Expression {

    private final Expression left;
    private final Expression right;

    public AddExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Object evaluate(DracoInterpreter interpreter) {
        Object leftValue = left.evaluate(interpreter);
        Object rightValue = right.evaluate(interpreter);

        if (leftValue instanceof Number a && rightValue instanceof Number b) {
            return a.doubleValue() + b.doubleValue();
        }

        if (leftValue instanceof String a && rightValue instanceof String b) {
            return a + b;
        }

        if (leftValue instanceof Number a && rightValue instanceof String b) {
            return a + b;
        }

        if (leftValue instanceof String a && rightValue instanceof Number b) {
            return a + b;
        }

        throw new RuntimeException("Operands must be strings or numbers!");
    }

    @Override
    public String toString() {
        return "AddExpression{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }
}
