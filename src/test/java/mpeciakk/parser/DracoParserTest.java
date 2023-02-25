package mpeciakk.parser;

import mpeciakk.TestEnvironment;
import mpeciakk.Tests;
import mpeciakk.parser.expression.AddExpression;
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
