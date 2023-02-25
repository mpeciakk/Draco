package mpeciakk.parser.expression;

import mpeciakk.parser.Expression;

import java.util.Objects;

public class MinusExpression implements Expression {

    private final Expression expression;

    public MinusExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MinusExpression that)) return false;
        return Objects.equals(expression, that.expression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expression);
    }
}
