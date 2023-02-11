package mpeciakk.parser.syntax;

import mpeciakk.parser.expression.Statement;
import mpeciakk.lexer.TokenType;
import mpeciakk.parser.DracoParser;
import mpeciakk.runtime.DracoInterpreter;
import mpeciakk.runtime.Environment;

import java.util.ArrayList;
import java.util.List;

public class BlockStatement extends DracoSyntax<BlockStatement> implements Statement {

    private final List<Statement> statements = new ArrayList<>();

    @Override
    public boolean match(DracoParser parser) {
        return parser.match(TokenType.OPEN_SCOPE);
    }

    @Override
    public void parse(DracoParser parser) {
        while (!parser.check(TokenType.CLOSE_SCOPE)) {
            statements.add(parser.statement());
        }

        parser.advance();
    }

    @Override
    public void evaluate(DracoInterpreter interpreter) {
        Environment scoped = new Environment(interpreter.getEnvironment());
        Environment previous = interpreter.getEnvironment();

        try {
            interpreter.setEnvironment(scoped);
            for (Statement statement : statements) {
                statement.evaluate(interpreter);
            }
        } catch (Throwable any) {
            any.printStackTrace();
        } finally {
            interpreter.setEnvironment(previous);
        }
    }
}
