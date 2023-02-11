package mpeciakk;

import mpeciakk.lexer.Token;
import mpeciakk.lexer.TokenType;
import mpeciakk.parser.expression.Statement;
import mpeciakk.lexer.DracoLexer;
import mpeciakk.parser.DracoParser;
import mpeciakk.runtime.DracoInterpreter;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {



    public static void assertTokensMatch(List<Token> tokens, TokenType... types) {
        for (int i = 0; i < types.length; i++) {
            if (tokens.get(i).type() != types[i]) {
                throw new IllegalStateException(String.format("Token at index %d did not match expected type %s (found %s)!\nFull token stack: %s", i, types[i], tokens.get(i).type(), String.join(", ", tokens.stream().map(Token::type).map(Enum::name).toList())));
            }
        }
    }

    @SafeVarargs
    public static void assertStatementsMatch(List<Statement> tokens, Class<? extends Statement>... statements) {
        for (int i = 0; i < tokens.size(); i++) {
            if (statements.length <= i) {
                throw new IllegalStateException(String.format("Statement at index %d is null!", i));
            } else if (tokens.get(i).getClass() != statements[i]) {
                throw new IllegalStateException(String.format("Statement at index %d did not match expected type %s (found %s!)!", i, statements[i], tokens.get(i).getClass()));
            }
        }
    }
}
