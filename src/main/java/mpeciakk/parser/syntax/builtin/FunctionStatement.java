package mpeciakk.parser.syntax.builtin;

import mpeciakk.parser.expression.other.CallExpression;
import mpeciakk.parser.expression.statement.Statement;
import mpeciakk.lexer.Token;
import mpeciakk.lexer.TokenType;
import mpeciakk.parser.DracoParser;
import mpeciakk.parser.syntax.DracoStatement;
import mpeciakk.parser.syntax.SyntaxEnvironment;
import mpeciakk.parser.syntax.SyntaxKey;
import mpeciakk.runtime.DracoInterpreter;
import mpeciakk.runtime.DracoRuntimeError;
import mpeciakk.runtime.Environment;
import mpeciakk.runtime.type.DracoFunction;

import java.util.ArrayList;
import java.util.List;

public class FunctionStatement extends DracoStatement {

    private static final SyntaxKey<Token> NAME = new SyntaxKey<>();
    private static final SyntaxKey<List<String>> ARGUMENTS = new SyntaxKey<>();
    private static final SyntaxKey<Statement> STATEMENT = new SyntaxKey<>();

    @Override
    public boolean match(DracoParser parser) {
        return parser.match(TokenType.FUNCTION);
    }

    @Override
    public void parse(DracoParser parser, SyntaxEnvironment environment) {
        Token name = parser.consume(TokenType.IDENTIFIER, "Expected to find function name after 'function' keyword!");
        parser.consume(TokenType.LEFT_PARENTHESIS, "Expected to find opening parenthesis after function name!");

        List<String> arguments = new ArrayList<>();
        while (parser.check(TokenType.IDENTIFIER)) {
            arguments.add((String) parser.getCurrent().literal());
            parser.advance();
            parser.match(TokenType.COMMA);
        }

        parser.consume(TokenType.RIGHT_PARENTHESIS, "Expected to find closing parenthesis after function name!");
        Statement statement = parser.statement();

        environment.set(NAME, name);
        environment.set(ARGUMENTS, arguments);
        environment.set(STATEMENT, statement);
    }

    @Override
    public void apply(DracoInterpreter interpreter, SyntaxEnvironment environment) {
        String name = (String) environment.get(NAME).literal();
        List<String> arguments = environment.get(ARGUMENTS);
        Statement statement = environment.get(STATEMENT);

        DracoFunction function = new DracoFunction() {
            @Override
            public Object call(CallExpression parent, List<Object> args) {
                if (arguments.size() != args.size()) {
                    throw new DracoRuntimeError(String.format("""
                        Function %s was called, which expects %d arguments, but %d were provided instead.
                        """, name, arity(), args.size()));
                }

                Environment env = interpreter.pushEnvironment();

                for (int i = 0; i < arguments.size(); i++) {
                    env.define(arguments.get(i), args.get(i));
                }

                statement.evaluate(interpreter);

                interpreter.popEnvironment();

                return null;
            }

            @Override
            public int arity() {
                return arguments.size();
            }

            @Override
            public String name() {
                return name;
            }
        };

        interpreter.getEnvironment().define(name, function);
    }
}
