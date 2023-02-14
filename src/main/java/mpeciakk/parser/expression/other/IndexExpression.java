package mpeciakk.parser.expression.other;

import mpeciakk.object.DracoArray;
import mpeciakk.object.DracoJavaClass;
import mpeciakk.object.DracoJavaField;
import mpeciakk.object.DracoObject;
import mpeciakk.parser.expression.Expression;
import mpeciakk.runtime.DracoInterpreter;
import mpeciakk.lexer.Token;
import mpeciakk.runtime.DracoRuntimeError;

import java.util.Objects;

public class IndexExpression implements Expression {

    private final Expression expression;
    private final Token name;

    public IndexExpression(Expression expression, Token name) {
        this.expression = expression;
        this.name = name;
    }

    @Override
    public DracoObject evaluate(DracoInterpreter interpreter) {
        DracoObject object = expression.evaluate(interpreter);
        Double propertyName = (Double) name.literal();

        if (object instanceof DracoArray dracoArray) {
            return dracoArray.getElements().get(propertyName.intValue());
        }

//        if (object.getProperties().containsKey(propertyName)) {
//            return object.getProperties().get(propertyName);
//        } else {
//            throw new DracoRuntimeError(String.format("""
//                    Object %s has no field named %s.
//                    """, object, propertyName));
//        }

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
