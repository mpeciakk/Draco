package mpeciakk.parser.expression;

import mpeciakk.parser.Expression;

import java.util.Objects;

public class GreaterEqualThanExpression implements Expression {

    private final Expression left;
    private final Expression right;

    public GreaterEqualThanExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GreaterEqualThanExpression that)) return false;
        return Objects.equals(left, that.left) && Objects.equals(right, that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}
