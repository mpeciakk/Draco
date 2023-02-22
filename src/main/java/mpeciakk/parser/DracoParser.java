package mpeciakk.parser;

import mpeciakk.lexer.Token;
import mpeciakk.lexer.TokenType;
import mpeciakk.parser.node.*;
import mpeciakk.parser.statement.ExpressionStatement;

import java.util.ArrayList;
import java.util.List;

import static mpeciakk.lexer.TokenType.*;

public class DracoParser {

    private final List<Token> tokens;
    private int index = 0;
    private Token current;

    private final String[] lines;

    public DracoParser(List<Token> tokens, String[] lines) {
        this.tokens = tokens;
        this.lines = lines;
    }

    public List<Statement> parse() {
        current = tokens.get(index);

        List<Statement> statements = new ArrayList<>();

        while (!isAtEnd()) {
            statements.add(statement());
        }

        return statements;
    }

    private Statement statement() {
        return new ExpressionStatement(expression());
    }

    private Expression expression() {
        return binary();
    }

    private Expression binary() {
        return comparison();
    }

    private Expression comparison() {
        return term();
    }

    private Expression term() {
        return factor();
    }

    private Expression factor() {
        return unary();
    }

    private Expression unary() {
        while (check(NOT, MINUS)) {
            if (match(NOT)) {
                // return not node
            } else if (match(MINUS)) {
                // return not node
            }
        }

        return call();
    }

    private Expression call() {
        Expression expression = atom();

        // TODO: check if expression is function invoke, property or index access

        return expression;
    }

    private Expression atom() {
        if (match(FALSE)) {
            return new BooleanNode(false);
        }

        if (match(TRUE)) {
            return new BooleanNode(true);
        }

        if (match(NULL)) {
            return new NullNode();
        }

        if (check(INT)) {
            Token token = consume();

            return new IntNode((int) token.value());
        }

        if (check(FLOAT)) {
            Token token = consume();

            return new FloatNode((float) token.value());
        }

        if (check(STRING)) {
            Token token = consume();

            return new StringNode(token.literal());
        }

        if (match(LEFT_PARENTHESIS)) {
            Expression expression = expression();
            consume(RIGHT_PARENTHESIS, "Expected ')'");
            return expression;
        }

        if (check(IDENTIFIER)) {
            // variable access node
        }

        throw error(current, "Expected to find an expression");
    }

    public boolean isAtEnd() {
        return current == null || current.type() == EOF;
    }

    public void advance() {
        index++;

        if (!isAtEnd()) {
            current = tokens.get(index);
        }
    }

    public Token consume(TokenType type, String message) {
        if (check(type)) {
            Token toReturn = current;
            advance();
            return toReturn;
        }

        throw error(current, message);
    }

    public Token consume() {
        Token toReturn = current;
        advance();
        return toReturn;
    }

    public boolean check(TokenType... types) {
        if (isAtEnd()) {
            return false;
        }

        for (TokenType type : types) {
            if (current.type() == type) {
                return true;
            }
        }

        return false;
    }

    public boolean checkNext(TokenType type) {
        if (isAtEnd()) {
            return false;
        }

        Token next = tokens.get(index + 1);

        if (next.type() == EOF) {
            return false;
        }

        return next.type() == type;
    }

    public boolean match(TokenType... types) {
        for (TokenType type : types) {
            if (check(type)) {
                advance();
                return true;
            }
        }

        return false;
    }

    private Error error(Token token, String message) {
        if (token == null) {
            return new DracoParseError(message);
        } else {
            return new DracoParseError(message, lines[token.line()], token.start(), token);
        }
    }
}
