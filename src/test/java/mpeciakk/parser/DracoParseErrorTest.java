package mpeciakk.parser;

import mpeciakk.lexer.DracoTokenError;
import mpeciakk.lexer.Token;
import mpeciakk.lexer.TokenType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DracoParseErrorTest {

    @Test
    public void token_error_test() {
        DracoParseError error = new DracoParseError("Token error message");

        assertEquals(error.getMessage(), "Token error message");
    }

    @Test
    public void token_error_test_long() {
        DracoParseError error = new DracoParseError("Token error message", "(2+2", 3, new Token(TokenType.RIGHT_PARENTHESIS, null, null, 3, 0, 3));

        assertEquals(error.getMessage(), """
                Token error message:
                   |
                 4 |   (2+2
                   |      ^
                   |
                """.trim());
    }
}
