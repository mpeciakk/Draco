package mpeciakk.parser.expression;

import mpeciakk.parser.Expression;

import java.util.Objects;

public class LessEqualThanExpression implements Expression {

    private final Expression left;
    private final Expression right;

    public LessEqualThanExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LessEqualThanExpression that)) return false;
        return Objects.equals(left, that.left) && Objects.equals(right, that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}
