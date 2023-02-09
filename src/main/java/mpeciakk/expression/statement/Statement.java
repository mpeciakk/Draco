package mpeciakk.expression.statement;

import mpeciakk.runtime.DracoInterpreter;

public abstract class Statement {
    public abstract void evaluate(DracoInterpreter interpreter);
}
