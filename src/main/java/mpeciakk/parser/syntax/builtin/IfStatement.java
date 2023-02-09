package mpeciakk.parser.syntax.builtin;

import mpeciakk.parser.expression.Expression;
import mpeciakk.parser.expression.statement.Statement;
import mpeciakk.lexer.TokenType;
import mpeciakk.parser.DracoParser;
import mpeciakk.parser.syntax.DracoStatement;
import mpeciakk.parser.syntax.SyntaxEnvironment;
import mpeciakk.parser.syntax.SyntaxKey;
import mpeciakk.runtime.DracoInterpreter;

public class IfStatement extends DracoStatement {

    private static final SyntaxKey<Expression> CONDITION = new SyntaxKey<>();
    private static final SyntaxKey<Statement> STATEMENT = new SyntaxKey<>();
    private static final SyntaxKey<Statement> ELSE_STATEMENT = new SyntaxKey<>();

    @Override
    public boolean match(DracoParser parser) {
        return parser.match(TokenType.IF);
    }

    @Override
    public void parse(DracoParser parser, SyntaxEnvironment environment) {
        Expression condition = parser.expression();
        Statement statement = parser.statement();

        Statement elseStatement = null;

        if (parser.match(TokenType.ELSE)) {
            elseStatement = parser.statement();
        }

        environment.set(CONDITION, condition);
        environment.set(STATEMENT, statement);
        environment.set(ELSE_STATEMENT, elseStatement);
    }

    @Override
    public void apply(DracoInterpreter interpreter, SyntaxEnvironment environment) {
        if (isTrue(environment.get(CONDITION).evaluate(interpreter))) {
            environment.get(STATEMENT).evaluate(interpreter);
        } else {
            if (environment.get(ELSE_STATEMENT) != null) {
                environment.get(ELSE_STATEMENT).evaluate(interpreter);
            }
        }
    }

    public static boolean isTrue(Object object) {
        if (object == null) {
            return false;
        }

        if (object instanceof Boolean bool) {
            return bool;
        }

        return true;
    }
}
