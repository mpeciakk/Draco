package mpeciakk.parser.expression.other;

import mpeciakk.lexer.Token;
import mpeciakk.object.DracoObject;
import mpeciakk.parser.expression.Expression;
import mpeciakk.runtime.DracoRuntimeError;
import mpeciakk.object.DracoFunction;
import mpeciakk.runtime.DracoInterpreter;

import java.util.List;

public class CallExpression implements Expression {

    private final Expression expression;
    private final Token paren;
    private final List<Expression> arguments;

    public CallExpression(Expression expression, Token paren, List<Expression> arguments) {
        this.expression = expression;
        this.paren = paren;
        this.arguments = arguments;
    }

    @Override
    public DracoObject evaluate(DracoInterpreter interpreter) {
        DracoObject caller = expression.evaluate(interpreter);
        List<DracoObject> evaluatedArgs = arguments.stream().map(it -> it.evaluate(interpreter)).toList();

        if (caller instanceof DracoFunction function) {
            if (arguments.size() != function.arity()) {
                throw new DracoRuntimeError(String.format("""
                        Function %s was called, which expects %d arguments, but %d were provided instead.
                        """, function.name(), function.arity(), arguments.size()));
            }

            return function.call(this, evaluatedArgs);
        }

        throw new Error(String.format("Tried to call function on invalid caller %s!", caller));
    }

    public Token getParen() {
        return paren;
    }

    @Override
    public String toString() {
        return "CallExpression{" +
                "expression=" + expression +
                ", arguments=" + arguments +
                '}';
    }
}
