package mpeciakk.expression.other;

import mpeciakk.runtime.type.DracoFunction;
import mpeciakk.runtime.DracoInterpreter;
import mpeciakk.expression.Expression;

import java.util.List;

public class CallExpression extends Expression {

    private final Expression expression;
    private final List<Expression> arguments;

    public CallExpression(Expression expression, List<Expression> arguments) {
        this.expression = expression;
        this.arguments = arguments;
    }

    @Override
    public Object evaluate(DracoInterpreter interpreter) {
        Object caller = expression.evaluate(interpreter);
        List<Object> evaluatedArgs = arguments.stream().map(it -> it.evaluate(interpreter)).toList();

        if(caller instanceof DracoFunction function) {
            if(arguments.size() != function.arity()) {
                throw new Error(String.format("""
                        Function %s was called, which expects %d arguments, but %d were provided instead.
                        """, function.name(), function.arity(), arguments.size()));
            }

            return function.call(this, evaluatedArgs);
        }

        throw new Error(String.format("Tried to call function on invalid caller %s!", caller));
    }

    @Override
    public String toString() {
        return "CallExpression{" +
                "expression=" + expression +
                ", arguments=" + arguments +
                '}';
    }
}
