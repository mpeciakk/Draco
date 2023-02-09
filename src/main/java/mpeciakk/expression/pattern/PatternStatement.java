package mpeciakk.expression.pattern;

import mpeciakk.parser.syntax.SyntaxEnvironment;
import mpeciakk.runtime.DracoInterpreter;
import mpeciakk.expression.statement.Statement;
import mpeciakk.parser.syntax.DracoSyntaxStatement;

public class PatternStatement extends Statement {

    private final DracoSyntaxStatement syntax;
    private final SyntaxEnvironment environment;

    public PatternStatement(DracoSyntaxStatement syntax, SyntaxEnvironment environment) {
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
