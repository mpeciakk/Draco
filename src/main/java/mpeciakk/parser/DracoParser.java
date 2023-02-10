package mpeciakk.parser;

import mpeciakk.parser.expression.Expression;
import mpeciakk.parser.expression.logical.*;
import mpeciakk.parser.expression.math.*;
import mpeciakk.parser.expression.other.*;
import mpeciakk.parser.expression.pattern.PatternExpression;
import mpeciakk.parser.expression.pattern.PatternStatement;
import mpeciakk.parser.expression.statement.ExpressionStatement;
import mpeciakk.parser.expression.statement.Statement;
import mpeciakk.lexer.Token;
import mpeciakk.lexer.TokenType;
import mpeciakk.parser.syntax.DracoExpression;
import mpeciakk.parser.syntax.DracoSyntax;
import mpeciakk.parser.syntax.DracoStatement;
import mpeciakk.parser.syntax.SyntaxEnvironment;
import mpeciakk.parser.syntax.builtin.*;

import java.util.ArrayList;
import java.util.List;

import static mpeciakk.lexer.TokenType.*;

public class DracoParser {

    private static final DracoSyntax[] SYNTAX_EXTENSIONS = new DracoSyntax[]{new IfStatement(), new BlockStatement(), new FunctionStatement(), new VariableDeclarationStatement(), new InlineIfExpression(), new FunctionExpression()};

    private final List<Token> tokens;
    private int index = 0;
    private Token current;

    private final String[] lines;

    private final SyntaxEnvironment defaultSyntaxEnvironment = new SyntaxEnvironment();

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

    public Statement statement() {
        int restore = index;
        for (DracoSyntax syntax : SYNTAX_EXTENSIONS) {
            if (syntax instanceof DracoStatement) {
                if (syntax.match(this)) {
                    SyntaxEnvironment syntaxEnvironment = new SyntaxEnvironment();

                    syntax.parse(this, syntaxEnvironment);
                    return new PatternStatement((DracoStatement) syntax, syntaxEnvironment);
                } else {
                    index = restore;
                    current = tokens.get(index);
                }
            }
        }

        return new ExpressionStatement(expression());
    }

    public Expression expression() {
        int restore = index;

        for (DracoSyntax syntax : SYNTAX_EXTENSIONS) {
            if (syntax instanceof DracoExpression) {
                if (syntax.match(this)) {
                    SyntaxEnvironment syntaxEnvironment = new SyntaxEnvironment();

                    syntax.parse(this, syntaxEnvironment);
                    return new PatternExpression((DracoExpression) syntax, syntaxEnvironment);
                } else {
                    index = restore;
                    current = tokens.get(index);
                }
            }
        }

        return binary();
    }

    private Expression binary() {
        Expression left = comparison();

        while (check(AND, OR)) {
            if (match(AND)) {
                left = new AndExpression(left, comparison());
            } else if (match(OR)) {
                left = new OrExpression(left, comparison());
            }
        }

        return left;
    }

    private Expression comparison() {
        if (match(NOT)) {
            return new NotExpression(comparison());
        }

        Expression left = term();

        while (check(EQUAL_EQUAL, NOT_EQUAL, GREATER, LESS, GREATER_EQUAL, LESS_EQUAL)) {
            if (match(EQUAL_EQUAL)) {
                left = new EqualsExpression(left, comparison());
            } else if (match(NOT_EQUAL)) {
                left = new NotEqualsExpression(left, comparison());
            } else if (match(GREATER)) {
                left = new GreaterThanExpression(left, comparison());
            } else if (match(LESS)) {
                left = new LessThanExpression(left, comparison());
            } else if (match(GREATER_EQUAL)) {
                left = new GreaterEqualThanExpression(left, comparison());
            } else if (match(LESS_EQUAL)) {
                left = new LessEqualThanExpression(left, comparison());
            }
        }

        return left;
    }

    private Expression term() {
        Expression left = factor();

        while (check(MINUS, PLUS)) {
            if (match(PLUS)) {
                left = new AddExpression(left, term());
            } else if (match(MINUS)) {
                left = new SubtractExpression(left, term());
            }
        }

        return left;
    }

    private Expression factor() {
        Expression left = unary();

        while (check(SLASH, STAR)) {
            if (match(SLASH)) {
                left = new DivideExpression(left, factor());
            } else if (match(STAR)) {
                left = new MultiplyExpression(left, factor());
            }
        }

        return left;
    }

    private Expression unary() {
        if (check(NOT, MINUS)) {
            if (match(NOT)) {
                return new NotExpression(unary());
            } else if (match(MINUS)) {
                return new MinusExpression(unary());
            }
        }

        return call();
    }

    private Expression call() {
        Expression expression = atom();

        while (true) {
            if (match(LEFT_PARENTHESIS)) {
                List<Expression> arguments = new ArrayList<>();

                if (!check(RIGHT_PARENTHESIS)) {
                    do {
                        arguments.add(expression());
                    } while (match(COMMA));
                }

                Token paren = consume(RIGHT_PARENTHESIS, "Expected function call to close with a ')' character!");
                expression = new CallExpression(expression, paren, arguments);
            } else if (match(DOT)) {
                Token name = consume(IDENTIFIER, "Expected to find a property name after '.'!");

                expression = new PropertyExpression(expression, name);
            } else {
                break;
            }
        }

        return expression;
    }

    private Expression atom() {
        if (match(FALSE)) {
            return new BooleanExpression(false);
        }

        if (match(TRUE)) {
            return new BooleanExpression(true);
        }

        if (match(NULL)) {
            return new StringExpression(null);
        }

        if (check(NUMBER, STRING)) {
            Token token = consume();

            if (token.literal() instanceof Number number) {
                return new NumberExpression(number.doubleValue());
            } else {
                return new StringExpression((String) token.literal());
            }
        }

        if (match(LEFT_PARENTHESIS)) {
            Expression expression = expression();
            consume(RIGHT_PARENTHESIS, "Expect ')' after expression.");
            return expression;
        }

        if (check(IDENTIFIER)) {
            Token token = consume();
            return new VariableExpression(token);
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

    private Error error(Token token, String message) {
        if (token == null) {
            return new DracoParseError(message);
        } else {
            return new DracoParseError(message, lines[token.line()], token.start(), token);
        }
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

    public Token getCurrent() {
        return current;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
        current = tokens.get(index);
    }
}
