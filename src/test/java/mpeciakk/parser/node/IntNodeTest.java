package mpeciakk.parser.node;

import mpeciakk.TestEnvironment;
import mpeciakk.Tests;
import mpeciakk.parser.statement.ExpressionStatement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntNodeTest {

    private final TestEnvironment testEnvironment = new TestEnvironment();

    @Test
    public void parser_test_int() {
        String input = """
                11
                """;

        Tests.assertStatementsMatch(testEnvironment.parse(input), new ExpressionStatement(new IntNode(11)));
    }

    @Test
    public void test_int_node_equal() {
        IntNode node = new IntNode(2137);
        IntNode node2 = new IntNode(2137);

        assertEquals(node, node2);
    }

    @Test
    public void test_int_node_hash() {
        IntNode node = new IntNode(2137);

        int hash = node.hashCode();

        for (int i = 0; i < 3; ++i) {
            assertEquals(hash, node.hashCode());
        }
    }
}
