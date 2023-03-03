package mpeciakk.parser;

import mpeciakk.TestEnvironment;
import mpeciakk.Tests;
import mpeciakk.parser.expression.*;
import mpeciakk.parser.node.BooleanNode;
import mpeciakk.parser.node.IntNode;
import mpeciakk.parser.statement.ExpressionStatement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class DracoParserTest {

    private final TestEnvironment testEnvironment = new TestEnvironment();

    @Test
    public void parser_expression_test() {
        String input = """
                (1+1)
                """.trim();

        Tests.assertStatementsMatch(testEnvironment.parse(input), new ExpressionStatement(new AddExpression(new IntNode(1), new IntNode(1))));
    }

    @Test
    public void parser_math_test1() {
        String input = """
                2 + 2 * 2
                """.trim();

        Tests.assertStatementsMatch(testEnvironment.parse(input), new ExpressionStatement(
                new AddExpression(new IntNode(2), new MultiplyExpression(new IntNode(2), new IntNode(2))))
        );
    }

    @Test
    public void parser_math_test2() {
        String input = """
                2 + 2 / 2
                """.trim();

        Tests.assertStatementsMatch(testEnvironment.parse(input), new ExpressionStatement(
                new AddExpression(new IntNode(2), new DivideExpression(new IntNode(2), new IntNode(2))))
        );
    }

    @Test
    public void parser_math_test3() {
        String input = """
                2 / 2 * 2
                """.trim();

        Tests.assertStatementsMatch(testEnvironment.parse(input), new ExpressionStatement(
                new DivideExpression(new IntNode(2), new MultiplyExpression(new IntNode(2), new IntNode(2))))
        );
    }

    @Test
    public void parser_math_test4() {
        String input = """
                2 * (2 + 1) / (4-3)
                """.trim();

        Tests.assertStatementsMatch(testEnvironment.parse(input), new ExpressionStatement(
                new MultiplyExpression(new IntNode(2), new DivideExpression(new AddExpression(new IntNode(2), new IntNode(1)), new SubtractExpression(new IntNode(4), new IntNode(3)))))
        );
    }

    @Test
    public void parser_boolean_test1() {
        String input = """
                true && false
                """.trim();

        Tests.assertStatementsMatch(testEnvironment.parse(input), new ExpressionStatement(
                new AndExpression(new BooleanNode(true), new BooleanNode(false))
        ));
    }

    @Test
    public void parser_boolean_test2() {
        String input = """
                true && (false || true)
                """.trim();

        Tests.assertStatementsMatch(testEnvironment.parse(input), new ExpressionStatement(
                new AndExpression(new BooleanNode(true), new OrExpression(new BooleanNode(false), new BooleanNode(true)))
        ));
    }

    @Test
    public void parser_boolean_test3() {
        String input = """
                (1 == 2 || 2 == 2) && (1 < 2 || 2 >= 1) || 1 != 3
                """.trim();

        Tests.assertStatementsMatch(testEnvironment.parse(input), new ExpressionStatement(
                new AndExpression(
                        new OrExpression(
                                new EqualsExpression(new IntNode(1), new IntNode(2)),
                                new EqualsExpression(new IntNode(2), new IntNode(2))
                        ),
                        new OrExpression(
                                new OrExpression(
                                        new LessThanExpression(new IntNode(1), new IntNode(2)),
                                        new GreaterEqualThanExpression(new IntNode(2), new IntNode(1))
                                ),
                                new NotEqualsExpression(new IntNode(1), new IntNode(3)))
                )));
    }

    @Test
    public void parser_expression_error() {
        String input = """
                (
                """.trim();

        assertThrows(DracoParseError.class, () -> testEnvironment.parse(input));
    }

    @Test
    public void parser_expression_error_consume() {
        String input = """
                (1+1
                """.trim();

        assertThrows(DracoParseError.class, () -> testEnvironment.parse(input));
    }
}
