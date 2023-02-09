package mpeciakk.parser.expression.statement;

import mpeciakk.parser.expression.Expression;
import mpeciakk.runtime.DracoInterpreter;

public class ExpressionStatement extends Statement {

    private final Expression expression;

    public ExpressionStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void evaluate(DracoInterpreter interpreter) {
        expression.evaluate(interpreter);
    }

    @Override
    public String toString() {
        return "ExpressionStatement{" +
                "expression=" + expression +
                '}';
    }
}
