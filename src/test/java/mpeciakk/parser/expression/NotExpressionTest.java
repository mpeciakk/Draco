package mpeciakk.parser.expression;

import mpeciakk.TestEnvironment;
import mpeciakk.Tests;
import mpeciakk.parser.node.BooleanNode;
import mpeciakk.parser.node.FloatNode;
import mpeciakk.parser.node.IntNode;
import mpeciakk.parser.statement.ExpressionStatement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotExpressionTest {

    private final TestEnvironment testEnvironment = new TestEnvironment();

    @Test
    public void parser_not_expression() {
        String input = """
                !false
                """;

        Tests.assertStatementsMatch(testEnvironment.parse(input), new ExpressionStatement(new NotExpression(new BooleanNode(false))));
    }

    @Test
    public void test_not_expression_equal() {
        NotExpression node = new NotExpression(new BooleanNode(false));
        NotExpression node2 = new NotExpression(new BooleanNode(false));

        assertEquals(node, node2);
    }

    @Test
    public void test_not_expression_hash() {
        NotExpression node = new NotExpression(new BooleanNode(true));

        int hash = node.hashCode();

        for (int i = 0; i < 3; ++i) {
            assertEquals(hash, node.hashCode());
        }
    }
}
