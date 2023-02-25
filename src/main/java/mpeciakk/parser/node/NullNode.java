package mpeciakk.parser.node;

import mpeciakk.parser.Expression;

public class NullNode implements Expression {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o instanceof NullNode;
    }
}
