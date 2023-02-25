package mpeciakk.lexer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DracoTokenErrorTest {

    @Test
    public void token_error_test() {
        DracoTokenError error = new DracoTokenError("Token error message");

        assertEquals(error.getMessage(), "Token error message");
    }

    @Test
    public void token_error_test_long() {
        DracoTokenError error = new DracoTokenError("Token error message", "if (true) {} else #", 11, 18);

        assertEquals(error.getMessage(), """
                Token error message:
                    |
                 12 |   if (true) {} else #
                    |                     ^
                    |
                """.trim());
    }
}
