package mpeciakk.parser.syntax;

import mpeciakk.parser.expression.Expression;
import mpeciakk.lexer.Token;
import mpeciakk.lexer.TokenType;
import mpeciakk.parser.DracoParser;
import mpeciakk.parser.expression.Statement;
import mpeciakk.runtime.DracoInterpreter;

public class VariableDeclarationStatement extends DracoSyntax<VariableDeclarationStatement> implements Statement {

    private Token name;
    private Expression value;

    @Override
    public boolean match(DracoParser parser) {
        return parser.check(TokenType.IDENTIFIER) && parser.checkNext(TokenType.EQUAL);
    }

    @Override
    public void parse(DracoParser parser) {
        name = parser.consume(TokenType.IDENTIFIER, "Expected a variable name!");

        value = null;
        if (parser.match(TokenType.EQUAL)) {
            value = parser.expression();
        }
    }

    @Override
    public void evaluate(DracoInterpreter interpreter) {
        interpreter.getEnvironment().define((String) name.literal(), value.evaluate(interpreter));
    }
}
