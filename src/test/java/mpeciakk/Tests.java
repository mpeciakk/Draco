package mpeciakk;

import mpeciakk.lexer.Token;
import mpeciakk.lexer.TokenType;

import java.util.List;

public class Tests {

    public static void assertTokensMatch(List<Token> tokens, TokenType... types) {
        for (int i = 0; i < types.length; i++) {
            if (tokens.get(i).type() != types[i]) {
                throw new IllegalStateException(String.format("Token at index %d did not match expected type %s (found %s)!\nFull token stack: %s", i, types[i], tokens.get(i).type(), String.join(", ", tokens.stream().map(Token::type).map(Enum::name).toList())));
            }
        }
    }
}
