package mpeciakk.expression.other;

import mpeciakk.runtime.DracoInterpreter;
import mpeciakk.lexer.Token;
import mpeciakk.expression.Expression;

public class PropertyExpression extends Expression {

    private final Expression expression;
    private final Token name;

    public PropertyExpression(Expression expression, Token name) {
        this.expression = expression;
        this.name = name;
    }

    @Override
    public Object evaluate(DracoInterpreter interpreter) {
        return null;
    }

    @Override
    public String toString() {
        return "PropertyExpression{" +
                "expression=" + expression +
                ", name=" + name +
                '}';
    }
}
