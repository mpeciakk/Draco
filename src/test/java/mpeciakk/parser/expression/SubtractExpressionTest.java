package mpeciakk.parser.expression;

import mpeciakk.TestEnvironment;
import mpeciakk.Tests;
import mpeciakk.parser.node.FloatNode;
import mpeciakk.parser.node.IntNode;
import mpeciakk.parser.statement.ExpressionStatement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubtractExpressionTest {

    private final TestEnvironment testEnvironment = new TestEnvironment();

    @Test
    public void parser_subtract_expression() {
        String input = """
                3.0-4
                """;

        Tests.assertStatementsMatch(testEnvironment.parse(input), new ExpressionStatement(new SubtractExpression(new FloatNode(3.0f), new IntNode(4))));
    }

    @Test
    public void test_subtract_expression_equal() {
        SubtractExpression node = new SubtractExpression(new IntNode(3), new FloatNode(4));
        SubtractExpression node2 = new SubtractExpression(new IntNode(3), new FloatNode(4));

        assertEquals(node, node2);
    }

    @Test
    public void test_subtract_expression_hash() {
        SubtractExpression node = new SubtractExpression(new IntNode(3), new FloatNode(4));

        int hash = node.hashCode();

        for (int i = 0; i < 3; ++i) {
            assertEquals(hash, node.hashCode());
        }
    }
}
