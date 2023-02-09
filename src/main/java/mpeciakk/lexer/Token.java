package mpeciakk.lexer;

public record Token(TokenType type, Object literal, int line, int lineStart, int start) {

    public Token(TokenType type) {
        this(type, null, 0, 0, 0);
    }
}