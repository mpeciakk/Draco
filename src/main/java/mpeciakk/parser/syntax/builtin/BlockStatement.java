package mpeciakk.parser.syntax.builtin;

import mpeciakk.expression.statement.Statement;
import mpeciakk.lexer.TokenType;
import mpeciakk.parser.DracoParser;
import mpeciakk.parser.syntax.DracoSyntaxStatement;
import mpeciakk.parser.syntax.SyntaxEnvironment;
import mpeciakk.parser.syntax.DracoSyntaxBuilder;
import mpeciakk.parser.syntax.fragment.RepeatingFragment;
import mpeciakk.parser.syntax.fragment.StatementFragment;
import mpeciakk.parser.syntax.fragment.TokenFragment;
import mpeciakk.runtime.DracoInterpreter;
import mpeciakk.runtime.Environment;

public class BlockStatement extends DracoSyntaxStatement {

    private static final StatementFragment STATEMENT = StatementFragment.of();
    private static final RepeatingFragment STATEMENTS = RepeatingFragment.of(
            STATEMENT
    );

    private static final DracoSyntaxBuilder SYNTAX = new DracoSyntaxBuilder(
            false,
            TokenFragment.of(TokenType.OPEN_SCOPE),
            STATEMENTS,
            TokenFragment.of(TokenType.CLOSE_SCOPE)
    );

    @Override
    public boolean match(DracoParser parser, SyntaxEnvironment environment) {
        return SYNTAX.match(parser, environment);
    }

    @Override
    public void apply(DracoInterpreter interpreter, SyntaxEnvironment environment) {
        Environment scoped = new Environment(interpreter.getEnvironment());
        Environment previous = interpreter.getEnvironment();

        try {
            interpreter.setEnvironment(scoped);
            for (Statement statement : environment.get(STATEMENTS).stream().map((env) -> env.get(STATEMENT)).toList()) {
                statement.evaluate(interpreter);
            }
        } catch (Throwable any) {
            any.printStackTrace();
        } finally {
            interpreter.setEnvironment(previous);
        }
    }
}
