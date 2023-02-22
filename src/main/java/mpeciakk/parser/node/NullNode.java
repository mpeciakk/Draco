package mpeciakk.parser.node;

public class NullNode extends Node {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o instanceof NullNode;
    }
}
