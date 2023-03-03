package mpeciakk.parser.node;

import mpeciakk.parser.Expression;

import java.util.Objects;

public class IntNode implements Expression {

    private final int value;

    public IntNode(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntNode intNode)) return false;
        return value == intNode.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
