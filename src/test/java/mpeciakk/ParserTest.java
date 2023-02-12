package mpeciakk;

import mpeciakk.parser.expression.other.ExpressionStatement;
import mpeciakk.parser.syntax.BlockStatement;
import mpeciakk.parser.syntax.FunctionStatement;
import mpeciakk.parser.syntax.IfStatement;
import mpeciakk.parser.syntax.VariableDeclarationStatement;
import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void parser_test1() {
        TestEnvironment environment = new TestEnvironment();

        String input = """
                a = 11
                
                if (a == 11) {
                
                } else if (true) {
                
                } else {
                
                }
                """;

        Tests.assertStatementsMatch(environment.parse(input), VariableDeclarationStatement.class, IfStatement.class);
    }

    @Test
    public void parser_test2() {
        TestEnvironment environment = new TestEnvironment();

        String input = """
                function f(x) {
                    print(2*x+2)
                }
                """;

        Tests.assertStatementsMatch(environment.parse(input), FunctionStatement.class, BlockStatement.class);
    }

    @Test
    public void parser_test3() {
        TestEnvironment environment = new TestEnvironment();

        String input = """
                1 == 1
                "asd" || "dsa"
                """;

        Tests.assertStatementsMatch(environment.parse(input), ExpressionStatement.class, ExpressionStatement.class);
    }
}
