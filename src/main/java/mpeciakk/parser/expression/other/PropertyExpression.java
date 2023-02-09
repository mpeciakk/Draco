package mpeciakk.parser.expression.other;

import mpeciakk.parser.expression.Expression;
import mpeciakk.runtime.DracoInterpreter;
import mpeciakk.lexer.Token;

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
