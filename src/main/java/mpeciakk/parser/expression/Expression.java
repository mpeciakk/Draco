package mpeciakk.parser.expression;

import mpeciakk.object.DracoObject;
import mpeciakk.runtime.DracoInterpreter;

public abstract class Expression {
    public abstract DracoObject evaluate(DracoInterpreter interpreter);
}
