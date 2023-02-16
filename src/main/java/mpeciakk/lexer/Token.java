package mpeciakk.lexer;

public record Token(TokenType type, Object value, String literal, int line, int lineStart, int start) {
}