package mpeciakk.parser.expression.pattern;

import mpeciakk.parser.syntax.SyntaxEnvironment;
import mpeciakk.runtime.DracoInterpreter;
import mpeciakk.parser.expression.statement.Statement;
import mpeciakk.parser.syntax.DracoStatement;

public class PatternStatement extends Statement {

    private final DracoStatement syntax;
    private final SyntaxEnvironment environment;

    public PatternStatement(DracoStatement syntax, SyntaxEnvironment environment) {
        this.syntax = syntax;
        this.environment = environment;
    }

    @Override
    public void evaluate(DracoInterpreter interpreter) {
        syntax.apply(interpreter, environment);
    }

    @Override
    public String toString() {
        return "PatternStatement{" +
                "syntax=" + syntax +
                '}';
    }
}
