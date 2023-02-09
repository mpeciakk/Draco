package mpeciakk.expression;

import mpeciakk.runtime.DracoInterpreter;

public abstract class Expression {
    public abstract Object evaluate(DracoInterpreter interpreter);
}
