package mpeciakk.parser.expression;

import mpeciakk.TestEnvironment;
import mpeciakk.Tests;
import mpeciakk.parser.node.FloatNode;
import mpeciakk.parser.node.IntNode;
import mpeciakk.parser.statement.ExpressionStatement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DivideExpressionTest {

    private final TestEnvironment testEnvironment = new TestEnvironment();

    @Test
    public void parser_divide_expression() {
        String input = """
                3.0/4
                """;

        System.out.println(testEnvironment.parse(input));

        Tests.assertStatementsMatch(testEnvironment.parse(input), new ExpressionStatement(new DivideExpression(new FloatNode(3.0f), new IntNode(4))));
    }

    @Test
    public void test_divide_expression_equal() {
        DivideExpression node = new DivideExpression(new IntNode(3), new FloatNode(4));
        DivideExpression node2 = new DivideExpression(new IntNode(3), new FloatNode(4));

        assertEquals(node, node2);
    }

    @Test
    public void test_divide_expression_hash() {
        DivideExpression node = new DivideExpression(new IntNode(3), new FloatNode(4));

        int hash = node.hashCode();

        for (int i = 0; i < 3; ++i) {
            assertEquals(hash, node.hashCode());
        }
    }
}
