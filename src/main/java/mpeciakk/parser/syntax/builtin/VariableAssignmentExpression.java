package mpeciakk.parser.syntax.builtin;

import mpeciakk.parser.syntax.SyntaxEnvironment;
import mpeciakk.runtime.DracoInterpreter;
import mpeciakk.parser.DracoParser;
import mpeciakk.lexer.TokenType;
import mpeciakk.parser.syntax.DracoSyntaxExpression;
import mpeciakk.parser.syntax.DracoSyntaxBuilder;
import mpeciakk.parser.syntax.fragment.ExpressionFragment;
import mpeciakk.parser.syntax.fragment.IdentifierFragment;
import mpeciakk.parser.syntax.fragment.TokenFragment;

public class VariableAssignmentExpression extends DracoSyntaxExpression {

    private static final IdentifierFragment IDENTIFIER = IdentifierFragment.of();
    private static final ExpressionFragment VALUE = ExpressionFragment.of();
    private static final DracoSyntaxBuilder SYNTAX = new DracoSyntaxBuilder(
            false,
            IDENTIFIER,
            TokenFragment.of(TokenType.EQUAL),
            VALUE
    );

    @Override
    public boolean match(DracoParser parser, SyntaxEnvironment environment) {
        return SYNTAX.match(parser, environment);
    }

    @Override
    public Object apply(DracoInterpreter interpreter, SyntaxEnvironment environment) {
        String name = (String) environment.get(IDENTIFIER).literal();
        Object value = environment.get(VALUE).evaluate(interpreter);

        interpreter.getEnvironment().define(name, value);

        return value;
    }
}
