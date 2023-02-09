package mpeciakk.expression.logical;

import mpeciakk.runtime.DracoInterpreter;
import mpeciakk.expression.Expression;

import static mpeciakk.old.IfStatement.isTrue;

public class NotExpression extends Expression {

    private final Expression expression;

    public NotExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public Object evaluate(DracoInterpreter interpreter) {
        Object value = expression.evaluate(interpreter);

        return !isTrue(value);
    }

    @Override
    public String toString() {
        return "NotExpression{" +
                "expression=" + expression +
                '}';
    }
}
