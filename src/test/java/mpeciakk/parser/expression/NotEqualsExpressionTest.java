package mpeciakk.parser.expression;

import mpeciakk.TestEnvironment;
import mpeciakk.Tests;
import mpeciakk.parser.node.FloatNode;
import mpeciakk.parser.node.IntNode;
import mpeciakk.parser.statement.ExpressionStatement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotEqualsExpressionTest {

    private final TestEnvironment testEnvironment = new TestEnvironment();

    @Test
    public void parser_not_equals_expression() {
        String input = """
                1!=2.0
                """;

        Tests.assertStatementsMatch(testEnvironment.parse(input), new ExpressionStatement(new NotEqualsExpression(new IntNode(1), new FloatNode(2))));
    }

    @Test
    public void test_not_equals_expression_equal() {
        NotEqualsExpression node = new NotEqualsExpression(new IntNode(1), new FloatNode(2));
        NotEqualsExpression node2 = new NotEqualsExpression(new IntNode(1), new FloatNode(2));

        assertEquals(node, node2);
    }

    @Test
    public void test_not_equals_expression_hash() {
        NotEqualsExpression node = new NotEqualsExpression(new IntNode(1), new FloatNode(2));

        int hash = node.hashCode();

        for (int i = 0; i < 3; ++i) {
            assertEquals(hash, node.hashCode());
        }
    }
}
