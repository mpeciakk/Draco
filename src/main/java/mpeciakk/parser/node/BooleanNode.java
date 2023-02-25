package mpeciakk.parser.node;

import mpeciakk.parser.Expression;

import java.util.Objects;

public class BooleanNode implements Expression {

    private final boolean value;

    public BooleanNode(boolean value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BooleanNode that)) return false;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
