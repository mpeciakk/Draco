package mpeciakk.parser.node;

import mpeciakk.parser.Expression;

import java.util.Objects;

public class FloatNode implements Expression {

    private final float value;

    public FloatNode(float value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FloatNode floatNode)) return false;
        return Float.compare(floatNode.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
