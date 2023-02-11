package mpeciakk.parser.syntax;

import mpeciakk.object.DracoObject;
import mpeciakk.parser.expression.other.CallExpression;
import mpeciakk.parser.expression.Statement;
import mpeciakk.lexer.Token;
import mpeciakk.lexer.TokenType;
import mpeciakk.parser.DracoParser;
import mpeciakk.runtime.DracoInterpreter;
import mpeciakk.runtime.DracoRuntimeError;
import mpeciakk.runtime.Environment;
import mpeciakk.object.DracoFunction;

import java.util.ArrayList;
import java.util.List;

public class FunctionStatement extends DracoSyntax<FunctionStatement> implements Statement {

    private Token name;
    private final List<String> arguments = new ArrayList<>();
    private Statement statement;

    @Override
    public boolean match(DracoParser parser) {
        return parser.match(TokenType.FUNCTION);
    }

    @Override
    public void parse(DracoParser parser) {
        name = parser.consume(TokenType.IDENTIFIER, "Expected to find function name after 'function' keyword!");
        parser.consume(TokenType.LEFT_PARENTHESIS, "Expected to find opening parenthesis after function name!");

        while (parser.check(TokenType.IDENTIFIER)) {
            arguments.add((String) parser.getCurrent().literal());
            parser.advance();
            parser.match(TokenType.COMMA);
        }

        parser.consume(TokenType.RIGHT_PARENTHESIS, "Expected to find closing parenthesis after function name!");
        statement = parser.statement();
    }

    @Override
    public void evaluate(DracoInterpreter interpreter) {
        String functionName = (String) name.literal();

        DracoFunction function = new DracoFunction() {
            @Override
            public DracoObject call(CallExpression parent, List<DracoObject> args) {
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
                return functionName;
            }
        };

        interpreter.getEnvironment().define(functionName, function);
    }
}
