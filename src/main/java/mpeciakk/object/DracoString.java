package mpeciakk.object;

import mpeciakk.parser.expression.other.CallExpression;

import java.util.List;
import java.util.Objects;

public class DracoString extends DracoObject {

    private final String value;

    public DracoString(String value) {
        this.value = value;

        getProperties().put("length", new DracoNumber(value.length()));
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DracoString that)) return false;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
