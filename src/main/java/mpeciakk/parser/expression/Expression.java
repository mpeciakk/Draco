package mpeciakk.parser.expression;

import mpeciakk.object.DracoObject;
import mpeciakk.runtime.DracoInterpreter;

public interface Expression {
    DracoObject evaluate(DracoInterpreter interpreter);
}
