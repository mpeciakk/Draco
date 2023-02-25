package mpeciakk.parser.expression;

import mpeciakk.TestEnvironment;
import mpeciakk.Tests;
import mpeciakk.parser.node.FloatNode;
import mpeciakk.parser.node.IntNode;
import mpeciakk.parser.statement.ExpressionStatement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinusExpressionTest {

    private final TestEnvironment testEnvironment = new TestEnvironment();

    @Test
    public void parser_minus_expression() {
        String input = """
                -3.0
                """;

        Tests.assertStatementsMatch(testEnvironment.parse(input), new ExpressionStatement(new MinusExpression(new FloatNode(3))));
    }

    @Test
    public void test_minus_expression_equal() {
        MinusExpression node = new MinusExpression(new IntNode(1));
        MinusExpression node2 = new MinusExpression(new IntNode(1));

        assertEquals(node, node2);
    }

    @Test
    public void test_add_expression_hash() {
        MinusExpression node = new MinusExpression(new IntNode(1));

        int hash = node.hashCode();

        for (int i = 0; i < 3; ++i) {
            assertEquals(hash, node.hashCode());
        }
    }
}
