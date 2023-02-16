package mpeciakk;

import mpeciakk.lexer.DracoLexer;
import mpeciakk.lexer.TokenType;
import org.junit.jupiter.api.Test;

public class LexerTest {

    @Test
    public void lexer_test1() {
        String input = """
                a = 11
                """;

        DracoLexer lexer = new DracoLexer(input);

        Tests.assertTokensMatch(lexer.parse(), TokenType.IDENTIFIER, TokenType.EQUAL, TokenType.INT, TokenType.EOF);
    }

    @Test
    public void lexer_test2() {
        String input = """
                b = 22.2
                print(b)
                """;

        DracoLexer lexer = new DracoLexer(input);

        Tests.assertTokensMatch(lexer.parse(), TokenType.IDENTIFIER, TokenType.EQUAL, TokenType.FLOAT, TokenType.IDENTIFIER, TokenType.LEFT_PARENTHESIS, TokenType.IDENTIFIER, TokenType.RIGHT_PARENTHESIS, TokenType.EOF);
    }

    @Test
    public void lexer_test3() {
        String input = """
                1 + 2 - 3 * 4 / 5;
                """;

        DracoLexer lexer = new DracoLexer(input);

        Tests.assertTokensMatch(lexer.parse(), TokenType.INT, TokenType.PLUS, TokenType.INT, TokenType.MINUS, TokenType.INT, TokenType.STAR, TokenType.INT, TokenType.SLASH, TokenType.INT, TokenType.SEMICOLON, TokenType.EOF);
    }

    @Test
    public void lexer_test4() {
        String input = """
                true && false || a == "str"
                """;

        DracoLexer lexer = new DracoLexer(input);

        Tests.assertTokensMatch(lexer.parse(), TokenType.TRUE, TokenType.AND, TokenType.FALSE, TokenType.OR, TokenType.IDENTIFIER, TokenType.EQUAL_EQUAL, TokenType.STRING, TokenType.EOF);
    }

    @Test
    public void lexer_test5() {
        String input = """
                a > b || b < a && (a >= c) && a <= c || !a || a != b
                """;

        DracoLexer lexer = new DracoLexer(input);

        Tests.assertTokensMatch(lexer.parse(), TokenType.IDENTIFIER, TokenType.GREATER, TokenType.IDENTIFIER, TokenType.OR, TokenType.IDENTIFIER, TokenType.LESS, TokenType.IDENTIFIER, TokenType.AND,
                TokenType.LEFT_PARENTHESIS, TokenType.IDENTIFIER, TokenType.GREATER_EQUAL, TokenType.IDENTIFIER, TokenType.RIGHT_PARENTHESIS, TokenType.AND,
                TokenType.IDENTIFIER, TokenType.LESS_EQUAL, TokenType.IDENTIFIER, TokenType.OR, TokenType.NOT, TokenType.IDENTIFIER, TokenType.OR, TokenType.IDENTIFIER,
                TokenType.NOT_EQUAL, TokenType.IDENTIFIER, TokenType.EOF);
    }

    @Test
    public void lexer_test6() {
        String input = """
                if (true) { a[1] } else {}
                """;

        DracoLexer lexer = new DracoLexer(input);

        Tests.assertTokensMatch(lexer.parse(), TokenType.IF, TokenType.LEFT_PARENTHESIS, TokenType.TRUE, TokenType.RIGHT_PARENTHESIS, TokenType.OPEN_SCOPE,
                TokenType.IDENTIFIER, TokenType.LEFT_SQUARE, TokenType.INT, TokenType.RIGHT_SQUARE, TokenType.CLOSE_SCOPE, TokenType.ELSE, TokenType.OPEN_SCOPE, TokenType.CLOSE_SCOPE, TokenType.EOF);
    }
}
