package mpeciakk.parser.node;

import mpeciakk.TestEnvironment;
import mpeciakk.Tests;
import mpeciakk.parser.statement.ExpressionStatement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NullNodeTest {

    private final TestEnvironment testEnvironment = new TestEnvironment();

    @Test
    public void parser_test_null() {
        String input = """
                null
                """;

        Tests.assertStatementsMatch(testEnvironment.parse(input), new ExpressionStatement(new NullNode()));
    }

    @Test
    public void test_null_node_equal() {
        NullNode node = new NullNode();
        NullNode node2 = new NullNode();

        assertEquals(node, node2);
    }

    @Test
    public void test_null_node_hash() {
        NullNode node = new NullNode();

        int hash = node.hashCode();

        for (int i = 0; i < 3; ++i) {
            assertEquals(hash, node.hashCode());
        }
    }
}
