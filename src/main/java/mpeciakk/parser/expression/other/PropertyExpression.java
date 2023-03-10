package mpeciakk.parser.expression.other;

import mpeciakk.object.DracoJsonObject;
import mpeciakk.object.DracoObject;
import mpeciakk.parser.expression.Expression;
import mpeciakk.runtime.DracoInterpreter;
import mpeciakk.lexer.Token;
import mpeciakk.runtime.DracoRuntimeError;

import java.util.Objects;

public class PropertyExpression implements Expression {

    private final Expression expression;
    private final Token name;

    public PropertyExpression(Expression expression, Token name) {
        this.expression = expression;
        this.name = name;
    }

    @Override
    public DracoObject evaluate(DracoInterpreter interpreter) {
        DracoObject object = expression.evaluate(interpreter);
        String propertyName = (String) name.literal();

        if (object.getProperties().containsKey(propertyName)) {
            return object.getProperties().get(propertyName);
        } else if (object instanceof DracoJsonObject jsonObject) {
            return jsonObject.getChildren().get(propertyName);
        } else {
            throw new DracoRuntimeError(String.format("""
                    Object %s has no field named %s.
                    """, object, propertyName));
        }
    }

    @Override
    public String toString() {
        return "PropertyExpression{" +
                "expression=" + expression +
                ", name=" + name +
                '}';
    }
}
