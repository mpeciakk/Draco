package mpeciakk.parser.expression.math;

import mpeciakk.object.DracoNumber;
import mpeciakk.object.DracoObject;
import mpeciakk.object.DracoString;
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
    public DracoObject evaluate(DracoInterpreter interpreter) {
        DracoObject leftValue = left.evaluate(interpreter);
        DracoObject rightValue = right.evaluate(interpreter);

        if (leftValue instanceof DracoNumber a && rightValue instanceof DracoNumber b) {
            return new DracoNumber(a.getValue() + b.getValue());
        }

        if (leftValue instanceof DracoString a && rightValue instanceof DracoString b) {
            return new DracoString(a.getValue() + b.getValue());
        }

        if (leftValue instanceof DracoNumber a && rightValue instanceof DracoString b) {
            return new DracoString(a.getValue() + b.getValue());
        }

        if (leftValue instanceof DracoString a && rightValue instanceof DracoNumber b) {
            return new DracoString(a.getValue() + b.getValue());
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
