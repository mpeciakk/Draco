package mpeciakk.parser.expression;

import mpeciakk.TestEnvironment;
import mpeciakk.Tests;
import mpeciakk.parser.node.FloatNode;
import mpeciakk.parser.node.IntNode;
import mpeciakk.parser.statement.ExpressionStatement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultiplyExpressionTest {

    private final TestEnvironment testEnvironment = new TestEnvironment();

    @Test
    public void parser_multiply_expression() {
        String input = """
                3.0*4
                """;

        Tests.assertStatementsMatch(testEnvironment.parse(input), new ExpressionStatement(new MultiplyExpression(new FloatNode(3.0f), new IntNode(4))));
    }

    @Test
    public void test_multiply_expression_equal() {
        MultiplyExpression node = new MultiplyExpression(new IntNode(3), new FloatNode(4));
        MultiplyExpression node2 = new MultiplyExpression(new IntNode(3), new FloatNode(4));

        assertEquals(node, node2);
    }

    @Test
    public void test_multiply_expression_hash() {
        MultiplyExpression node = new MultiplyExpression(new IntNode(3), new FloatNode(4));

        int hash = node.hashCode();

        for (int i = 0; i < 3; ++i) {
            assertEquals(hash, node.hashCode());
        }
    }
}
