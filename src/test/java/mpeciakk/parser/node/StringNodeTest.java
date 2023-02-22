package mpeciakk.parser.node;

import mpeciakk.TestEnvironment;
import mpeciakk.Tests;
import mpeciakk.parser.statement.ExpressionStatement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringNodeTest {

    private final TestEnvironment testEnvironment = new TestEnvironment();

    @Test
    public void parser_test_string() {
        String input = """
                "string"
                """;

        Tests.assertStatementsMatch(testEnvironment.parse(input), new ExpressionStatement(new StringNode("string")));
    }

    @Test
    public void test_string_node_equal() {
        StringNode node = new StringNode("some random string");
        StringNode node2 = new StringNode("some random string");

        assertEquals(node, node2);
    }

    @Test
    public void test_string_node_hash() {
        StringNode node = new StringNode("some random string");

        int hash = node.hashCode();

        for (int i = 0; i < 3; ++i) {
            assertEquals(hash, node.hashCode());
        }
    }
}
