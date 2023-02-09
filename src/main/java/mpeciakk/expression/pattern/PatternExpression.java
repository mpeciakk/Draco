package mpeciakk.expression.pattern;

import mpeciakk.parser.syntax.SyntaxEnvironment;
import mpeciakk.runtime.DracoInterpreter;
import mpeciakk.expression.Expression;
import mpeciakk.parser.syntax.DracoSyntaxExpression;

public class PatternExpression extends Expression {

    private final DracoSyntaxExpression syntax;
    private final SyntaxEnvironment environment;

    public PatternExpression(DracoSyntaxExpression syntax, SyntaxEnvironment environment) {
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
