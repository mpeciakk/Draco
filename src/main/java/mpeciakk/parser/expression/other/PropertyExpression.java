package mpeciakk.parser.expression.other;

import mpeciakk.object.DracoObject;
import mpeciakk.parser.expression.Expression;
import mpeciakk.runtime.DracoInterpreter;
import mpeciakk.lexer.Token;
import mpeciakk.runtime.DracoRuntimeError;

public class PropertyExpression implements Expression {

    private final Expression expression;
    private final Token name;

    public PropertyExpression(Expression expression, Token name) {
        this.expression = expression;
        this.name = name;
    }

    @Override
    public DracoObject evaluate(DracoInterpreter interpreter) {
        Object object = expression.evaluate(interpreter);
        String propertyName = (String) name.literal();

        if (object instanceof DracoObject dracoObject) {
            return dracoObject.getProperties().get(propertyName);
        }

        throw new DracoRuntimeError(String.format("""
                        Object %s has no field named %s.
                        """, expression, propertyName));
    }

    @Override
    public String toString() {
        return "PropertyExpression{" +
                "expression=" + expression +
                ", name=" + name +
                '}';
    }
}
