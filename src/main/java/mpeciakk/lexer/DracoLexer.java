package mpeciakk.lexer;

import mpeciakk.DracoTokenError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static mpeciakk.lexer.TokenType.*;

public class DracoLexer {

    private static final Map<String, TokenType> KEYWORDS = new HashMap<>() {{
        put("and", AND);
        put("false", FALSE);
        put("null", NULL);
        put("or", OR);
        put("true", TRUE);
        put("if", IF);
        put("else", ELSE);
        put("function", FUNCTION);
    }};

    private static final Map<Character, TokenType> SIMPLE_TOKENS = new HashMap<>() {{
        put('(', LEFT_PARENTHESIS);
        put(')', RIGHT_PARENTHESIS);
        put('[', LEFT_SQUARE);
        put(']', RIGHT_SQUARE);
        put(',', COMMA);
        put('.', DOT);
        put('-', MINUS);
        put('+', PLUS);
        put(';', SEMICOLON);
        put(':', COLON);
        put('*', STAR);
        put('{', OPEN_SCOPE);
        put('}', CLOSE_SCOPE);
    }};

    private final List<Token> tokens = new ArrayList<>();

    private final String source;
    private int start = 0;
    private int index = -1;
    private int line = 0;
    private int lineStart = 0;
    private int indexInLine = 0;
    private char current = '\0';
    private final String[] lines;

    public DracoLexer(String source) {
        this.source = source;
        this.lines = source.trim().split("\n");
    }

    public List<Token> parse() {
        advance();

        while (!isAtEnd()) {
            start = index;
            scanCurrent();
        }

        lineStart = 0;
        addToken(TokenType.EOF);
        return tokens;
    }

    private void scanCurrent() {
        if (SIMPLE_TOKENS.containsKey(current)) {
            addToken(SIMPLE_TOKENS.get(current));
            advance();
            return;
        }

        switch (current) {
            case '"' -> string();

            case '!' -> {
                addToken(next('=') ? NOT_EQUAL : NOT);
                advance();
            }
            case '>' -> {
                addToken(next('=') ? GREATER_EQUAL : GREATER);
                advance();
            }
            case '<' -> {
                addToken(next('=') ? LESS_EQUAL : LESS);
                advance();
            }
            case '=' -> {
                addToken(next('=') ? EQUAL_EQUAL : EQUAL);
                advance();
            }

            case '&' -> {
                if (next('&')) {
                    addToken(AND);
                    advance();
                }
            }

            case '|' -> {
                if (next('|')) {
                    addToken(OR);
                    advance();
                }
            }

            case '/' -> {
                if (next('/')) {
                    while (current != '\n' && !isAtEnd()) {
                        advance();
                    }
                } else {
                    addToken(TokenType.SLASH);
                }
            }

            case ' ', '\t', '\r' -> {
                advance();
            }

            case '\n' -> {
                line++;
                lineStart = index;
                indexInLine = 0;
                advance();
            }

            default -> {
                if (Character.isDigit(current)) {
                    number();
                    break;
                }

                if (Character.isAlphabetic(current)) {
                    identifier();
                    break;
                }

                throw new DracoTokenError("Encountered an unexpected character '" + current + "'", lines[line], line, indexInLine);
            }
        }
    }

    private void string() {
        StringBuilder string = new StringBuilder();

        advance();

        while (!isAtEnd() && current != '"') {
            string.append(current);
            advance();
        }

        if (current != '"') {
            throw new DracoTokenError("Expected '\"'", lines[line], line, indexInLine);
        } else {
            advance();
        }

        addToken(STRING, string.toString(), string.toString());
    }

    private void number() {
        StringBuilder number = new StringBuilder();
        boolean hadDot = false;

        while (!isAtEnd() && (Character.isDigit(current) || current == '.')) {
            number.append(current);

            if (current == '.') {
                if (hadDot) {
                    throw new DracoTokenError("Unexpected character '.'", lines[line], line, indexInLine);
                }

                hadDot = true;
            }

            advance();
        }

        if (hadDot) {
            addToken(FLOAT, Float.parseFloat(number.toString()), number.toString());
        } else {
            addToken(INT, Integer.parseInt(number.toString()), number.toString());
        }
    }

    private void identifier() {
        StringBuilder identifier = new StringBuilder();

        while (!isAtEnd() && (Character.isAlphabetic(current) || Character.isDigit(current) || current == '_')) {
            identifier.append(current);
            advance();
        }

        TokenType type = IDENTIFIER;
        TokenType keyword = KEYWORDS.get(identifier.toString());
        if (keyword != null) {
            type = keyword;
        }

        addToken(type, identifier.toString(), identifier.toString());
    }

    private boolean next(char next) {
        advance();

        if (isAtEnd()) {
            return false;
        }

        if (current == next) {
            return true;
        }

        index--;
        return false;
    }

    private boolean isAtEnd() {
        return index >= source.length();
    }

    private void advance() {
        index++;
        indexInLine++;

        if (!isAtEnd()) {
            current = source.charAt(index);
        }
    }

    private void addToken(TokenType type) {
        addToken(type, null, null);
    }

    private void addToken(TokenType type, Object value, String literal) {
        tokens.add(new Token(type, value, literal, line, lineStart, start));
    }
}
