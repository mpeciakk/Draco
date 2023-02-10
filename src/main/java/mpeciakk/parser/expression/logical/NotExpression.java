package mpeciakk.parser.expression.logical;

import mpeciakk.object.DracoBoolean;
import mpeciakk.object.DracoObject;
import mpeciakk.parser.expression.Expression;
import mpeciakk.runtime.DracoInterpreter;

import static mpeciakk.parser.syntax.builtin.IfStatement.isTrue;

public class NotExpression extends Expression {

    private final Expression expression;

    public NotExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public DracoObject evaluate(DracoInterpreter interpreter) {
        DracoObject value = expression.evaluate(interpreter);

        return new DracoBoolean(!isTrue(value));
    }

    @Override
    public String toString() {
        return "NotExpression{" +
                "expression=" + expression +
                '}';
    }
}
