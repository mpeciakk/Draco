package mpeciakk.parser;

import mpeciakk.TestEnvironment;
import mpeciakk.Tests;
import mpeciakk.parser.node.FloatNode;
import mpeciakk.parser.statement.ExpressionStatement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpressionStatementTest {

    private final TestEnvironment testEnvironment = new TestEnvironment();

    @Test
    public void parser_test_expression_statement() {
        String input = """
                21.37
                """;

        Tests.assertStatementsMatch(testEnvironment.parse(input), new ExpressionStatement(new FloatNode(21.37f)));
    }

    @Test
    public void test_expression_statement_equal() {
        ExpressionStatement statement = new ExpressionStatement(new FloatNode(21.37f));
        ExpressionStatement statement2 = new ExpressionStatement(new FloatNode(21.37f));

        assertEquals(statement, statement2);
    }

    @Test
    public void test_expression_statement_hash() {
        ExpressionStatement statement = new ExpressionStatement(new FloatNode(21.37f));

        int hash = statement.hashCode();

        for (int i = 0; i < 3; ++i) {
            assertEquals(hash, statement.hashCode());
        }
    }
}
