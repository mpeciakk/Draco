package mpeciakk.parser.node;

import mpeciakk.parser.Expression;

public abstract class Node implements Expression {

    public Node add(Node other) {
        throw new RuntimeException();
    }

    public Node subtract(Node other) {
        throw new RuntimeException();
    }

    public Node multiply(Node other) {
        throw new RuntimeException();
    }

    public Node divide(Node other) {
        throw new RuntimeException();
    }

    public Node not(Node other) {
        throw new RuntimeException();
    }

    public Node minus(Node other) {
        throw new RuntimeException();
    }

    public Node greaterThan(Node other) {
        throw new RuntimeException();
    }

    public Node greaterEqualsThan(Node other) {
        throw new RuntimeException();
    }

    public Node lessThan(Node other) {
        throw new RuntimeException();
    }

    public Node lessEqualsThan(Node other) {
        throw new RuntimeException();
    }

    public Node equals(Node other) {
        throw new RuntimeException();
    }
}
