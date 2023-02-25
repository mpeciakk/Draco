package mpeciakk.parser.node;

import mpeciakk.parser.Expression;

import java.util.Objects;

public class StringNode implements Expression {

    private final String value;

    public StringNode(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StringNode that)) return false;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
