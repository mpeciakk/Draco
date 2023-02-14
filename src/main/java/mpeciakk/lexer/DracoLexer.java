package mpeciakk.lexer;

import mpeciakk.parser.DracoParseError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static mpeciakk.lexer.TokenType.*;

public class DracoLexer {

    private static final Map<String, TokenType> RESERVED_KEYWORDS = new HashMap<>() {{
        put("and", AND);
        put("false", FALSE);
        put("null", NULL);
        put("or", OR);
        put("true", TRUE);
        put("if", IF);
        put("else", ELSE);
        put("function", FUNCTION);
    }};

    private final List<Token> tokens = new ArrayList<>();

    private final String source;
    private int start = 0;
    private int index = -1;
    private int line = 0;
    private int lineStart = 0;
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

        tokens.add(new Token(TokenType.EOF, null, line, 0, index));
        return tokens;
    }

    private void scanCurrent() {
        switch (current) {
            case '"' -> string();

            case '(' -> {
                addToken(LEFT_PARENTHESIS);
                advance();
            }
            case ')' -> {
                addToken(RIGHT_PARENTHESIS);
                advance();
            }
            case '[' -> {
                addToken(SQUARE_LEFT);
                advance();
            }
            case ']' -> {
                addToken(SQUARE_RIGHT);
                advance();
            }
            case ',' -> {
                addToken(COMMA);
                advance();
            }
            case '.' -> {
                addToken(DOT);
                advance();
            }
            case '-' -> {
                addToken(MINUS);
                advance();
            }
            case '+' -> {
                addToken(PLUS);
                advance();
            }
            case ';' -> {
                addToken(SEMICOLON);
                advance();
            }
            case '*' -> {
                addToken(STAR);
                advance();
            }
            case '{' -> {
                addToken(OPEN_SCOPE);
                advance();
            }
            case '}' -> {
                addToken(CLOSE_SCOPE);
                advance();
            }

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

                throw new DracoParseError("Encountered an unexpected character", lines[line], index, null);
            }
        }
    }

    private void identifier() {
        String identifier = "";

        while (!isAtEnd() && (Character.isAlphabetic(current) || Character.isDigit(current) || current == '_')) {
            identifier += current;
            advance();
        }

        TokenType type = IDENTIFIER;
        TokenType keyword = RESERVED_KEYWORDS.get(identifier);
        if (keyword != null) {
            type = keyword;
        }

        addToken(type, identifier);
    }

    private void number() {
        String number = "";
        boolean hadDot = false;

        while (!isAtEnd() && (Character.isDigit(current) || current == '.')) {
            number += current;

            if (current == '.') {
                if (hadDot) {
                    throw new Error(String.format("Unexpected character '.' on line %d:%d!", line, index));
                }

                hadDot = true;
            }

            advance();
        }

        addToken(NUMBER, Double.parseDouble(number));
    }

    private void string() {
        String string = "";

        advance();

        while (!isAtEnd() && current != '"') {
            string += current;
            advance();
        }

        if (current != '"') {
            throw new Error(String.format("Encountered unterminated string on line %d!", line));
        } else {
            advance();
        }

        addToken(STRING, string);
    }

    private boolean isAtEnd() {
        return index >= source.length();
    }

    private void advance() {
        index++;

        if (!isAtEnd()) {
            current = source.charAt(index);
        }
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

    private void addToken(TokenType type) {
        addToken(type, null);
    }

    private void addToken(TokenType type, Object value) {
        tokens.add(new Token(type, value, line, lineStart, start));
    }
}
