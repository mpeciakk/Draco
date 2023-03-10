package mpeciakk.parser.expression.logical;

import mpeciakk.object.DracoObject;
import mpeciakk.parser.expression.Expression;
import mpeciakk.runtime.DracoInterpreter;

import static mpeciakk.parser.syntax.IfStatement.isTrue;

public class OrExpression implements Expression {

    private final Expression left;
    private final Expression right;

    public OrExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public DracoObject evaluate(DracoInterpreter interpreter) {
        DracoObject leftValue = left.evaluate(interpreter);

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
