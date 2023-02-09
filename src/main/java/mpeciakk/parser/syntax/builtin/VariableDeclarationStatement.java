package mpeciakk.parser.syntax.builtin;

import mpeciakk.parser.expression.Expression;
import mpeciakk.lexer.Token;
import mpeciakk.lexer.TokenType;
import mpeciakk.parser.DracoParser;
import mpeciakk.parser.syntax.DracoStatement;
import mpeciakk.parser.syntax.SyntaxEnvironment;
import mpeciakk.parser.syntax.SyntaxKey;
import mpeciakk.runtime.DracoInterpreter;

public class VariableDeclarationStatement extends DracoStatement {

    private static final SyntaxKey<Token> VARIABLE = new SyntaxKey<>();
    private static final SyntaxKey<Expression> VALUE = new SyntaxKey<>();

    @Override
    public boolean match(DracoParser parser) {
        return parser.check(TokenType.IDENTIFIER) && parser.checkNext(TokenType.EQUAL);
    }

    @Override
    public void parse(DracoParser parser, SyntaxEnvironment environment) {
        Token variableName = parser.consume(TokenType.IDENTIFIER, "Expected a variable name!");

        Expression value = null;
        if (parser.match(TokenType.EQUAL)) {
            value = parser.expression();
        }

        environment.set(VARIABLE, variableName);
        environment.set(VALUE, value);
    }

    @Override
    public void apply(DracoInterpreter interpreter, SyntaxEnvironment environment) {
        Token variableName = environment.get(VARIABLE);
        Expression value = environment.get(VALUE);

        interpreter.getEnvironment().define((String) variableName.literal(), value.evaluate(interpreter));
    }
}
