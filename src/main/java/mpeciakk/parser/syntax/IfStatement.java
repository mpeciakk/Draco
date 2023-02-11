package mpeciakk.parser.syntax;

import mpeciakk.lexer.TokenType;
import mpeciakk.object.DracoBoolean;
import mpeciakk.object.DracoObject;
import mpeciakk.parser.DracoParser;
import mpeciakk.parser.expression.Expression;
import mpeciakk.parser.expression.Statement;
import mpeciakk.runtime.DracoInterpreter;

public class IfStatement extends DracoSyntax<IfStatement> implements Statement {

    private Expression condition;
    private Statement statement;
    private Statement elseStatement;

    @Override
    public boolean match(DracoParser parser) {
        return parser.match(TokenType.IF);
    }

    @Override
    public void parse(DracoParser parser) {
        condition = parser.expression();
        statement = parser.statement();

        elseStatement = null;

        if (parser.match(TokenType.ELSE)) {
            elseStatement = parser.statement();
        }
    }

    @Override
    public void evaluate(DracoInterpreter interpreter) {
        if (isTrue(condition.evaluate(interpreter))) {
            statement.evaluate(interpreter);
        } else {
            if (elseStatement != null) {
                elseStatement.evaluate(interpreter);
            }
        }
    }

    public static boolean isTrue(DracoObject object) {
        if (object == null) {
            return false;
        }

        if (object instanceof DracoBoolean bool) {
            return bool.getValue();
        }

        return true;
    }
}
