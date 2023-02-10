package mpeciakk.parser.syntax.builtin;

import mpeciakk.lexer.TokenType;
import mpeciakk.object.DracoObject;
import mpeciakk.parser.DracoParser;
import mpeciakk.parser.expression.Expression;
import mpeciakk.parser.expression.statement.Statement;
import mpeciakk.parser.syntax.DracoExpression;
import mpeciakk.parser.syntax.SyntaxEnvironment;
import mpeciakk.parser.syntax.SyntaxKey;
import mpeciakk.runtime.DracoInterpreter;

import static mpeciakk.parser.syntax.builtin.IfStatement.isTrue;

public class InlineIfExpression extends DracoExpression {

    private static final SyntaxKey<Expression> CONDITION = new SyntaxKey<>();
    private static final SyntaxKey<Expression> EXPRESSION = new SyntaxKey<>();
    private static final SyntaxKey<Expression> ELSE_EXPRESSION = new SyntaxKey<>();


    @Override
    public boolean match(DracoParser parser) {
        return parser.match(TokenType.IF);
    }

    @Override
    public void parse(DracoParser parser, SyntaxEnvironment environment) {
        Expression condition = parser.expression();
        Expression expression = parser.expression();

        Expression elseExpression = null;

        if (parser.match(TokenType.ELSE)) {
            elseExpression = parser.expression();
        }

        environment.set(CONDITION, condition);
        environment.set(EXPRESSION, expression);
        environment.set(ELSE_EXPRESSION, elseExpression);
    }

    @Override
    public DracoObject apply(DracoInterpreter interpreter, SyntaxEnvironment environment) {
        if (isTrue(environment.get(CONDITION).evaluate(interpreter))) {
            return environment.get(EXPRESSION).evaluate(interpreter);
        } else {
            if (environment.get(ELSE_EXPRESSION) != null) {
                return environment.get(ELSE_EXPRESSION).evaluate(interpreter);
            }
        }

        return null;
    }
}
