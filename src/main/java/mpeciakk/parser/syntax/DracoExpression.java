package mpeciakk.parser.syntax;

import mpeciakk.object.DracoObject;
import mpeciakk.runtime.DracoInterpreter;

public abstract class DracoExpression extends DracoSyntax {
    public abstract DracoObject apply(DracoInterpreter interpreter, SyntaxEnvironment environment);
}
