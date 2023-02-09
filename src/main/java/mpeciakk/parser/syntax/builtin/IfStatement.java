package mpeciakk.parser.syntax.builtin;

import mpeciakk.lexer.TokenType;
import mpeciakk.parser.DracoParser;
import mpeciakk.parser.syntax.DracoSyntaxStatement;
import mpeciakk.parser.syntax.Prematchable;
import mpeciakk.parser.syntax.SyntaxEnvironment;
import mpeciakk.parser.syntax.DracoSyntaxBuilder;
import mpeciakk.parser.syntax.fragment.*;
import mpeciakk.runtime.DracoInterpreter;

public class IfStatement extends DracoSyntaxStatement implements Prematchable {

    private static final ExpressionFragment CONDITION = ExpressionFragment.of();
    private static final StatementFragment STATEMENT = StatementFragment.of();
    private static final StatementFragment ELSE_STATEMENT = StatementFragment.of();

    private static final DracoSyntaxBuilder SYNTAX = new DracoSyntaxBuilder(
            true,
            LiteralFragment.of("if"),
            CONDITION,
            STATEMENT,
            BlockFragment.optional(
                    LiteralFragment.of("else"),
                    ELSE_STATEMENT
            )
    );

    @Override
    public boolean prematch(DracoParser parser) {
        return parser.getCurrent().type() == TokenType.IDENTIFIER && parser.getCurrent().literal().equals("if");
    }

    @Override
    public boolean match(DracoParser parser, SyntaxEnvironment environment) {
        return SYNTAX.match(parser, environment);
    }

    @Override
    public void apply(DracoInterpreter interpreter, SyntaxEnvironment environment) {
        if (isTrue(environment.get(CONDITION).evaluate(interpreter))) {
            environment.get(STATEMENT).evaluate(interpreter);
        } else if (environment.get(ELSE_STATEMENT) != null) {
            environment.get(ELSE_STATEMENT).evaluate(interpreter);
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
