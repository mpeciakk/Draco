package mpeciakk.parser.syntax;

import java.util.HashMap;
import java.util.Map;

public class SyntaxEnvironment {
    private final Map<SyntaxKey<?>, Object> values = new HashMap<>();

    public void set(SyntaxKey<?> key, Object value) {
        values.put(key, value);
    }

    public <T> T get(SyntaxKey<T> key) {
        return (T) values.get(key);
    }

    @Override
    public String toString() {
        return "SyntaxEnvironment{" +
                "values=" + values +
                '}';
    }
}
