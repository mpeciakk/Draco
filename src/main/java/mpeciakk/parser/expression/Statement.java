package mpeciakk.parser.expression;

import mpeciakk.runtime.DracoInterpreter;

public interface Statement {
    void evaluate(DracoInterpreter interpreter);
}
