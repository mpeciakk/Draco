package mpeciakk;

import mpeciakk.parser.expression.pattern.PatternStatement;
import mpeciakk.parser.syntax.builtin.IfStatement;
import mpeciakk.parser.syntax.builtin.VariableDeclarationStatement;
import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void parser_test1() {
        TestEnvironment environment = new TestEnvironment();

        String input = """
                a = 11
                
                if (a == 11) {
                
                } else {
                
                }
                """;

        Tests.assertStatementsMatch(environment.parse(input), PatternStatement.class, PatternStatement.class);
    }
}
