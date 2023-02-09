package mpeciakk.parser.syntax;

import mpeciakk.runtime.DracoInterpreter;

public abstract class DracoExpression extends DracoSyntax {
    public abstract Object apply(DracoInterpreter interpreter, SyntaxEnvironment environment);
}
