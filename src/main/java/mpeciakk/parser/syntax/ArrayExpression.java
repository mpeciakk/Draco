package mpeciakk.parser.syntax;

import mpeciakk.lexer.TokenType;
import mpeciakk.object.DracoArray;
import mpeciakk.object.DracoObject;
import mpeciakk.parser.DracoParser;
import mpeciakk.parser.expression.Expression;
import mpeciakk.runtime.DracoInterpreter;

import java.util.ArrayList;
import java.util.List;

public class ArrayExpression extends DracoSyntax<ArrayExpression> implements Expression {

    private final List<Expression> elements = new ArrayList<>();

    @Override
    public boolean match(DracoParser parser) {
        return parser.match(TokenType.SQUARE_LEFT);
    }

    @Override
    public void parse(DracoParser parser) {
        while (!parser.check(TokenType.SQUARE_RIGHT)) {
            elements.add(parser.expression());
            parser.match(TokenType.COMMA);
        }

        parser.consume(TokenType.SQUARE_RIGHT, "Expected ']'!");
    }

    @Override
    public DracoObject evaluate(DracoInterpreter interpreter) {
        List<DracoObject> evaluatedElements = elements.stream().map(it -> it.evaluate(interpreter)).toList();

        return new DracoArray(evaluatedElements);
    }
}
