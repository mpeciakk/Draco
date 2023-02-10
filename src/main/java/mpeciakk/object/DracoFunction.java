package mpeciakk.object;

import mpeciakk.parser.expression.other.CallExpression;

import java.util.List;

public abstract class DracoFunction extends DracoObject {

    public abstract DracoObject call(CallExpression parent, List<DracoObject> arguments);

    public abstract int arity();

    public abstract String name();
}
