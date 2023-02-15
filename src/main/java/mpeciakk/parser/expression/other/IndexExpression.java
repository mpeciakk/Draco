package mpeciakk.parser.expression.other;

import mpeciakk.object.DracoArray;
import mpeciakk.object.DracoJsonObject;
import mpeciakk.object.DracoObject;
import mpeciakk.parser.expression.Expression;
import mpeciakk.runtime.DracoInterpreter;
import mpeciakk.lexer.Token;

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
        String propertyName = (String) name.literal();

        if (object instanceof DracoArray dracoArray) {
            return dracoArray.getElements().get(Integer.parseInt(propertyName));
        } else if (object instanceof DracoJsonObject jsonObject) {
            return jsonObject.getChildren().get(propertyName);
        }

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
