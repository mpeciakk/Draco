package mpeciakk.parser.node;

import mpeciakk.TestEnvironment;
import mpeciakk.Tests;
import mpeciakk.parser.statement.ExpressionStatement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BooleanNodeTest {

    private final TestEnvironment testEnvironment = new TestEnvironment();

    @Test
    public void parser_test_boolean_false() {
        String input = """
                false
                """;

        Tests.assertStatementsMatch(testEnvironment.parse(input), new ExpressionStatement(new BooleanNode(false)));
    }

    @Test
    public void parser_test_boolean_true() {
        String input = """
                true
                """;

        Tests.assertStatementsMatch(testEnvironment.parse(input), new ExpressionStatement(new BooleanNode(true)));
    }

    @Test
    public void test_boolean_node_equal() {
        BooleanNode node = new BooleanNode(true);
        BooleanNode node2 = new BooleanNode(true);

        assertEquals(node, node2);
    }

    @Test
    public void test_boolean_node_hash() {
        BooleanNode node = new BooleanNode(true);

        int hash = node.hashCode();

        for (int i = 0; i < 3; ++i) {
            assertEquals(hash, node.hashCode());
        }
    }
}
