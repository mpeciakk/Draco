package mpeciakk.parser.expression.math;

import mpeciakk.object.DracoNumber;
import mpeciakk.object.DracoObject;
import mpeciakk.parser.expression.Expression;
import mpeciakk.runtime.DracoInterpreter;

public class MinusExpression implements Expression {

    private final Expression expression;

    public MinusExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public DracoObject evaluate(DracoInterpreter interpreter) {
        DracoNumber value = (DracoNumber) expression.evaluate(interpreter);

        return new DracoNumber(-value.getValue());
    }

    @Override
    public String toString() {
        return "MinusExpression{" +
                "expression=" + expression +
                '}';
    }
}
