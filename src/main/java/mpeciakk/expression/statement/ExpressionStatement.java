package mpeciakk.expression.statement;

import mpeciakk.runtime.DracoInterpreter;
import mpeciakk.expression.Expression;

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
