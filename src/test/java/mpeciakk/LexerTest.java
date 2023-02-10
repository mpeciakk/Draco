package mpeciakk;

import mpeciakk.lexer.DracoLexer;
import org.junit.jupiter.api.Test;

import static mpeciakk.lexer.TokenType.*;

public class LexerTest {

    @Test
    public void lexer_test1() {
        String input = """
                a = 1
                """;

        DracoLexer lexer = new DracoLexer(input);

        Tests.assertTokensMatch(lexer.parse(), IDENTIFIER, EQUAL, NUMBER, EOF);
    }

    @Test
    public void lexer_test2() {
        String input = """
                a = 1
                                
                if (a) {
                    print("it works!")
                } else if (true) {
                                
                } else {
                                
                }
                """;

        DracoLexer lexer = new DracoLexer(input);

        Tests.assertTokensMatch(lexer.parse(), IDENTIFIER, EQUAL, NUMBER,
                IF, LEFT_PARENTHESIS, IDENTIFIER, RIGHT_PARENTHESIS, OPEN_SCOPE,
                IDENTIFIER, LEFT_PARENTHESIS, STRING, RIGHT_PARENTHESIS,
                CLOSE_SCOPE, ELSE, IF, LEFT_PARENTHESIS, TRUE, RIGHT_PARENTHESIS, OPEN_SCOPE,
                CLOSE_SCOPE, ELSE, OPEN_SCOPE,
                CLOSE_SCOPE,
                EOF);
    }

    @Test
    public void lexer_test3() {
        String input = """
                function a(b, c) {
                    if (b == 11 || b > 12) c()
                }
                                
                a(11, function() {
                    print("it works!")
                })
                """;

        DracoLexer lexer = new DracoLexer(input);

        Tests.assertTokensMatch(lexer.parse(), FUNCTION, IDENTIFIER, LEFT_PARENTHESIS, IDENTIFIER, COMMA, IDENTIFIER, RIGHT_PARENTHESIS, OPEN_SCOPE,
                IF, LEFT_PARENTHESIS, IDENTIFIER, EQUAL_EQUAL, NUMBER, OR, IDENTIFIER, GREATER, NUMBER, RIGHT_PARENTHESIS, IDENTIFIER, LEFT_PARENTHESIS, RIGHT_PARENTHESIS,
                CLOSE_SCOPE,
                IDENTIFIER, LEFT_PARENTHESIS, NUMBER, COMMA, FUNCTION, LEFT_PARENTHESIS, RIGHT_PARENTHESIS, OPEN_SCOPE,
                IDENTIFIER, LEFT_PARENTHESIS, STRING, RIGHT_PARENTHESIS,
                CLOSE_SCOPE, RIGHT_PARENTHESIS,
                EOF);
    }
}
