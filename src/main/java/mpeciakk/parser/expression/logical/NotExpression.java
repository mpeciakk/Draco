package mpeciakk.parser.expression.logical;

import mpeciakk.parser.expression.Expression;
import mpeciakk.runtime.DracoInterpreter;

import static mpeciakk.parser.syntax.builtin.IfStatement.isTrue;

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
