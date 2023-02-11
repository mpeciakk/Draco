package mpeciakk.parser.syntax;

import mpeciakk.lexer.TokenType;
import mpeciakk.object.DracoObject;
import mpeciakk.parser.DracoParser;
import mpeciakk.parser.expression.Expression;
import mpeciakk.runtime.DracoInterpreter;

import static mpeciakk.parser.syntax.IfStatement.isTrue;

public class InlineIfExpression extends DracoSyntax<InlineIfExpression> implements Expression {

    private Expression condition;
    private Expression expression;
    private Expression elseExpression;


    @Override
    public boolean match(DracoParser parser) {
        return parser.match(TokenType.IF);
    }

    @Override
    public void parse(DracoParser parser) {
        condition = parser.expression();
        expression = parser.expression();

        elseExpression = null;

        if (parser.match(TokenType.ELSE)) {
            elseExpression = parser.expression();
        }
    }

    @Override
    public DracoObject evaluate(DracoInterpreter interpreter) {
        if (isTrue(condition.evaluate(interpreter))) {
            return expression.evaluate(interpreter);
        } else {
            if (elseExpression != null) {
                return elseExpression.evaluate(interpreter);
            }
        }

        return null;
    }
}
