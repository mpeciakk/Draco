package mpeciakk.parser.syntax;

import mpeciakk.runtime.DracoInterpreter;

public abstract class DracoSyntaxExpression extends DracoSyntax {
    public abstract Object apply(DracoInterpreter interpreter, SyntaxEnvironment environment);
}
