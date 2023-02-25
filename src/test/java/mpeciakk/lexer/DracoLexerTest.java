package mpeciakk.lexer;

import mpeciakk.TestEnvironment;
import mpeciakk.Tests;
import mpeciakk.parser.DracoParseError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DracoLexerTest {

    private final TestEnvironment testEnvironment = new TestEnvironment();

    @Test
    public void lexer_test_variable_declaration() {
        String input = """
                b=22.2
                a = 11
                """;

        Tests.assertTokensMatch(testEnvironment.lex(input), TokenType.IDENTIFIER, TokenType.EQUAL, TokenType.FLOAT, TokenType.IDENTIFIER, TokenType.EQUAL, TokenType.INT, TokenType.EOF);
    }

    @Test
    public void lexer_test_print() {
        String input = """
                print(b)
                """;

        Tests.assertTokensMatch(testEnvironment.lex(input), TokenType.IDENTIFIER, TokenType.LEFT_PARENTHESIS, TokenType.IDENTIFIER, TokenType.RIGHT_PARENTHESIS, TokenType.EOF);
    }

    @Test
    public void lexer_test_arithmetic() {
        String input = """
                1+2- 3 *4 / 5;
                """;

        Tests.assertTokensMatch(testEnvironment.lex(input), TokenType.INT, TokenType.PLUS, TokenType.INT, TokenType.MINUS, TokenType.INT, TokenType.STAR, TokenType.INT, TokenType.SLASH, TokenType.INT, TokenType.SEMICOLON, TokenType.EOF);
    }

    @Test
    public void lexer_test_arithmetic2() {
        String input = """
                3.0/4
                """;

        Tests.assertTokensMatch(testEnvironment.lex(input), TokenType.FLOAT, TokenType.SLASH, TokenType.INT, TokenType.EOF);
    }

    @Test
    public void lexer_test_boolean() {
        String input = """
                true && false || a == "str"
                """;

        Tests.assertTokensMatch(testEnvironment.lex(input), TokenType.TRUE, TokenType.AND, TokenType.FALSE, TokenType.OR, TokenType.IDENTIFIER, TokenType.EQUAL_EQUAL, TokenType.STRING, TokenType.EOF);
    }

    @Test
    public void lexer_test_boolean2() {
        String input = """
                a > b || b < a && (a >= c) && a <= c || !a || a != b
                """;

        Tests.assertTokensMatch(testEnvironment.lex(input), TokenType.IDENTIFIER, TokenType.GREATER, TokenType.IDENTIFIER, TokenType.OR, TokenType.IDENTIFIER, TokenType.LESS, TokenType.IDENTIFIER, TokenType.AND,
                TokenType.LEFT_PARENTHESIS, TokenType.IDENTIFIER, TokenType.GREATER_EQUAL, TokenType.IDENTIFIER, TokenType.RIGHT_PARENTHESIS, TokenType.AND,
                TokenType.IDENTIFIER, TokenType.LESS_EQUAL, TokenType.IDENTIFIER, TokenType.OR, TokenType.NOT, TokenType.IDENTIFIER, TokenType.OR, TokenType.IDENTIFIER,
                TokenType.NOT_EQUAL, TokenType.IDENTIFIER, TokenType.EOF);
    }

    @Test
    public void lexer_test_if() {
        String input = """
                if (true) { a[1] } else {}
                """;

        Tests.assertTokensMatch(testEnvironment.lex(input), TokenType.IF, TokenType.LEFT_PARENTHESIS, TokenType.TRUE, TokenType.RIGHT_PARENTHESIS, TokenType.OPEN_SCOPE,
                TokenType.IDENTIFIER, TokenType.LEFT_SQUARE, TokenType.INT, TokenType.RIGHT_SQUARE, TokenType.CLOSE_SCOPE, TokenType.ELSE, TokenType.OPEN_SCOPE, TokenType.CLOSE_SCOPE, TokenType.EOF);
    }

    @Test
    public void lexer_test_null() {
        String input = """
                null
                """;

        Tests.assertTokensMatch(testEnvironment.lex(input), TokenType.NULL, TokenType.EOF);
    }

    @Test
    public void lexer_test_comment() {
        String input = """
                // some
                // random
                // comments
                """;

        Tests.assertTokensMatch(testEnvironment.lex(input), TokenType.EOF);
    }

    @Test
    public void lexer_test_string_error() {
        String input = """
                "string
                """;

        assertThrows(DracoTokenError.class, () -> testEnvironment.lex(input));
    }

    @Test
    public void lexer_test_error() {
        String input = """
                #
                """;

        assertThrows(DracoTokenError.class, () -> testEnvironment.lex(input));
    }

    @Test
    public void lexer_test_float_error() {
        String input = """
                1..2
                """;

        assertThrows(DracoTokenError.class, () -> testEnvironment.lex(input));
    }

    @Test
    public void lexer_test() {
        String input = """
                1 !
                """.trim();

        Tests.assertTokensMatch(testEnvironment.lex(input), TokenType.INT, TokenType.NOT, TokenType.EOF);
    }
}
