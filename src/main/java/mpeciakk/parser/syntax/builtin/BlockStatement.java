package mpeciakk.parser.syntax.builtin;

import mpeciakk.parser.expression.statement.Statement;
import mpeciakk.lexer.TokenType;
import mpeciakk.parser.DracoParser;
import mpeciakk.parser.syntax.DracoStatement;
import mpeciakk.parser.syntax.SyntaxEnvironment;
import mpeciakk.parser.syntax.SyntaxKey;
import mpeciakk.runtime.DracoInterpreter;
import mpeciakk.runtime.Environment;

import java.util.ArrayList;
import java.util.List;

public class BlockStatement extends DracoStatement {

    private static final SyntaxKey<List<Statement>> STATEMENTS = new SyntaxKey<>();

    @Override
    public boolean match(DracoParser parser) {
        return parser.match(TokenType.OPEN_SCOPE);
    }

    @Override
    public void parse(DracoParser parser, SyntaxEnvironment environment) {
        while (!parser.check(TokenType.CLOSE_SCOPE)) {
            environment.getOrDefault(STATEMENTS, new ArrayList<>()).add(parser.statement());
        }

        parser.advance();
    }

    @Override
    public void apply(DracoInterpreter interpreter, SyntaxEnvironment environment) {
        Environment scoped = new Environment(interpreter.getEnvironment());
        Environment previous = interpreter.getEnvironment();

        try {
            interpreter.setEnvironment(scoped);
            for (Statement statement : environment.getOrDefault(STATEMENTS, new ArrayList<>())) {
                statement.evaluate(interpreter);
            }
        } catch (Throwable any) {
            any.printStackTrace();
        } finally {
            interpreter.setEnvironment(previous);
        }
    }
}
