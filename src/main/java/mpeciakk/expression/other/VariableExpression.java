package mpeciakk.expression.other;

import mpeciakk.runtime.DracoInterpreter;
import mpeciakk.lexer.Token;
import mpeciakk.expression.Expression;

public class VariableExpression extends Expression {

    private final Token variable;

    public VariableExpression(Token variable) {
        this.variable = variable;
    }

    @Override
    public Object evaluate(DracoInterpreter interpreter) {
        return interpreter.getEnvironment().get(variable);
    }

    @Override
    public String toString() {
        return "VariableExpression{" +
                "variable=" + variable +
                '}';
    }
}
