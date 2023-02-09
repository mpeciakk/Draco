package mpeciakk.parser.syntax;

import mpeciakk.runtime.DracoInterpreter;

public abstract class DracoSyntaxStatement extends DracoSyntax {
    public abstract void apply(DracoInterpreter interpreter, SyntaxEnvironment environment);
}
