package mpeciakk.parser.node;

import mpeciakk.TestEnvironment;
import mpeciakk.Tests;
import mpeciakk.parser.statement.ExpressionStatement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FloatNodeTest {

    private final TestEnvironment testEnvironment = new TestEnvironment();

    @Test
    public void parser_test_float() {
        String input = """
                21.37
                """;

        Tests.assertStatementsMatch(testEnvironment.parse(input), new ExpressionStatement(new FloatNode(21.37f)));
    }

    @Test
    public void test_float_node_equal() {
        FloatNode node = new FloatNode(21.37f);
        FloatNode node2 = new FloatNode(21.37f);

        assertEquals(node, node2);
    }

    @Test
    public void test_float_node_hash() {
        FloatNode node = new FloatNode(21.37f);

        int hash = node.hashCode();

        for (int i = 0; i < 3; ++i) {
            assertEquals(hash, node.hashCode());
        }
    }
}
