package mpeciakk.parser.syntax;

import mpeciakk.lexer.Token;
import mpeciakk.lexer.TokenType;
import mpeciakk.object.DracoJsonObject;
import mpeciakk.object.DracoObject;
import mpeciakk.parser.DracoParser;
import mpeciakk.parser.expression.Expression;
import mpeciakk.runtime.DracoInterpreter;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static mpeciakk.lexer.TokenType.*;

public class ObjectExpression extends DracoSyntax<ObjectExpression> implements Expression {

    private final Map<String, Expression> children = new HashMap<>();

    @Override
    public boolean match(DracoParser parser) {
        return parser.match(TokenType.OPEN_SCOPE);
    }

    @Override
    public void parse(DracoParser parser) {
        if (!parser.check(TokenType.CLOSE_SCOPE)) {
            do {
                Token key = parser.consume(TokenType.IDENTIFIER, "Expected key");
                parser.consume(TokenType.COLON, "Expected ':'");
                Expression value = parser.expression();

                children.put((String) key.literal(), value);
            } while (parser.match(TokenType.COMMA));
        }

        parser.consume(CLOSE_SCOPE, "Expected '}'");
    }

    @Override
    public DracoObject evaluate(DracoInterpreter interpreter) {
        return new DracoJsonObject(children.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().evaluate(interpreter))));
    }
}
