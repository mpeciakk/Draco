package mpeciakk.parser.expression.pattern;

import mpeciakk.parser.expression.Expression;
import mpeciakk.parser.syntax.SyntaxEnvironment;
import mpeciakk.runtime.DracoInterpreter;
import mpeciakk.parser.syntax.DracoExpression;

public class PatternExpression extends Expression {

    private final DracoExpression syntax;
    private final SyntaxEnvironment environment;

    public PatternExpression(DracoExpression syntax, SyntaxEnvironment environment) {
        this.syntax = syntax;
        this.environment = environment;
    }

    @Override
    public Object evaluate(DracoInterpreter interpreter) {
        return syntax.apply(interpreter, environment);
    }

    @Override
    public String toString() {
        return "PatternExpression{" +
                "syntax=" + syntax +
                '}';
    }
}
