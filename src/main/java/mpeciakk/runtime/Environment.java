package mpeciakk.runtime;

import mpeciakk.lexer.Token;
import mpeciakk.object.DracoObject;

import java.util.HashMap;
import java.util.Map;

public class Environment {

    private final Environment parent;
    private final Map<String, DracoObject> variables = new HashMap<>();

    public Environment() {
        this(null);
    }

    public Environment(Environment parent) {
        this.parent = parent;
    }

    public void define(String name, DracoObject value) {
        if (parent != null) {
            if (parent.unsafeGet(name) != null) {
                parent.define(name, value);
            } else {
                variables.put(name, value);
            }
        } else {
            variables.put(name, value);
        }
    }

    public DracoObject get(Token name) {
        return get((String) name.literal());
    }

    private DracoObject unsafeGet(String name) {
        if (variables.containsKey(name)) {
            return variables.get(name);
        }

        if (parent != null) {
            return parent.unsafeGet(name);
        }

        return null;
    }

    public DracoObject get(String name) {
        if (variables.containsKey(name)) {
            return variables.get(name);
        }

        if (parent != null) {
            return parent.get(name);
        }

        throw new Error(String.format("Variable %s was not defined", name));
    }

    public Environment getParent() {
        return parent;
    }

    @Override
    public String toString() {
        return "Environment{" +
                "parent=" + parent +
                ", variables=" + variables +
                '}';
    }
}
