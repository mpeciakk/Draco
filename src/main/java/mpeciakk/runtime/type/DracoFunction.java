package mpeciakk.runtime.type;

import mpeciakk.expression.other.CallExpression;

import java.util.List;

public interface DracoFunction {

    Object call(CallExpression parent, List<Object> arguments);

    int arity();

    String name();
}
