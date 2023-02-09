package mpeciakk.parser.syntax.builtin;

import mpeciakk.parser.syntax.SyntaxEnvironment;
import mpeciakk.parser.syntax.DracoSyntaxBuilder;
import mpeciakk.parser.syntax.fragment.BlockFragment;
import mpeciakk.parser.syntax.fragment.ExpressionFragment;
import mpeciakk.parser.syntax.fragment.LiteralFragment;
import mpeciakk.runtime.DracoInterpreter;
import mpeciakk.parser.DracoParser;
import mpeciakk.parser.syntax.DracoSyntaxExpression;

public class InlineIfExpression extends DracoSyntaxExpression {

    private static final ExpressionFragment CONDITION = ExpressionFragment.of();
    private static final ExpressionFragment EXPRESSION = ExpressionFragment.of();
    private static final ExpressionFragment ELSE_EXPRESSION = ExpressionFragment.of();

    private static final DracoSyntaxBuilder SYNTAX = new DracoSyntaxBuilder(
            false,
            LiteralFragment.of("if"),
            CONDITION,
            EXPRESSION,
            BlockFragment.optional(
                    LiteralFragment.of("else"),
                    ELSE_EXPRESSION
            )
    );

    @Override
    public boolean match(DracoParser parser, SyntaxEnvironment environment) {
        return SYNTAX.match(parser, environment);
    }

    @Override
    public Object apply(DracoInterpreter interpreter, SyntaxEnvironment environment) {
        if (isTrue(environment.get(CONDITION).evaluate(interpreter))) {
            return environment.get(EXPRESSION).evaluate(interpreter);
        } else if (environment.get(ELSE_EXPRESSION) != null) {
            return environment.get(ELSE_EXPRESSION).evaluate(interpreter);
        }

        return null;
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
