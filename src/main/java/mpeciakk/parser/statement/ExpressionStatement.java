package mpeciakk.parser.statement;

import mpeciakk.parser.Expression;
import mpeciakk.parser.Statement;

import java.util.Objects;

public class ExpressionStatement implements Statement {

    private final Expression expression;

    public ExpressionStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExpressionStatement that)) return false;
        return Objects.equals(expression, that.expression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expression);
    }
}
