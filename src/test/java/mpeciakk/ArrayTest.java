package mpeciakk;

import mpeciakk.lexer.DracoLexer;
import mpeciakk.parser.DracoParser;
import mpeciakk.parser.expression.Statement;
import mpeciakk.runtime.DracoInterpreter;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrayTest {

    @Test
    public void array_test1() {
        String input = """
                a = [2137, 420, 69]
                
                print(a[1])
                """.trim();

        DracoLexer lexer = new DracoLexer(input);
        DracoParser parser = new DracoParser(lexer.parse(), input.split("\n"));
        List<Statement> statements = parser.parse();
        DracoInterpreter interpreter = new DracoInterpreter();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        interpreter.evaluate(statements);

        assertEquals("420.0", baos.toString().trim());
    }

    @Test
    public void array_test2() {
        String input = """
                a = [2137, "420", 69]
                
                print(a[1])
                """.trim();

        DracoLexer lexer = new DracoLexer(input);
        DracoParser parser = new DracoParser(lexer.parse(), input.split("\n"));
        List<Statement> statements = parser.parse();
        DracoInterpreter interpreter = new DracoInterpreter();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        interpreter.evaluate(statements);

        assertEquals("420", baos.toString().trim());
    }

    @Test
    public void array_test3() {
        String input = """
                a = [function() {
                    print("it works!")
                }, 420, "test"]
                
                a[0]()
                """.trim();

        DracoLexer lexer = new DracoLexer(input);
        DracoParser parser = new DracoParser(lexer.parse(), input.split("\n"));
        List<Statement> statements = parser.parse();
        DracoInterpreter interpreter = new DracoInterpreter();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        interpreter.evaluate(statements);

        assertEquals("it works!", baos.toString().trim());
    }
}
