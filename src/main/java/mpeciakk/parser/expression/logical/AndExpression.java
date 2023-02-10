package mpeciakk.parser.expression.logical;

import mpeciakk.object.DracoObject;
import mpeciakk.parser.expression.Expression;
import mpeciakk.runtime.DracoInterpreter;

import static mpeciakk.parser.syntax.builtin.IfStatement.isTrue;

public class AndExpression extends Expression {

    private final Expression left;
    private final Expression right;

    public AndExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public DracoObject evaluate(DracoInterpreter interpreter) {
        DracoObject leftValue = left.evaluate(interpreter);

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
