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

public class ObjectTest {

    @Test
    public void object_test1() {
        String input = """
                a = {
                    key: "value"
                }
                                
                print(a.key)
                """.trim();

        DracoLexer lexer = new DracoLexer(input);
        DracoParser parser = new DracoParser(lexer.parse(), input.split("\n"));
        List<Statement> statements = parser.parse();
        DracoInterpreter interpreter = new DracoInterpreter();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        interpreter.evaluate(statements);

        assertEquals("value", baos.toString().trim());
    }

    @Test
    public void object_test2() {
        String input = """
                a = {
                    b: {
                        c: "d"
                    }
                }
                                
                print(a.b.c)
                """.trim();

        DracoLexer lexer = new DracoLexer(input);
        DracoParser parser = new DracoParser(lexer.parse(), input.split("\n"));
        List<Statement> statements = parser.parse();
        DracoInterpreter interpreter = new DracoInterpreter();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        interpreter.evaluate(statements);

        assertEquals("d", baos.toString().trim());
    }

    @Test
    public void object_test3() {
        String input = """
                a = {
                    key: "value",
                    b: {
                        c: function(d) {
                            print(d)
                        }
                    }
                }
                                
                a.b.c("it works!")
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

    @Test
    public void object_test4() {
        String input = """
                a = {
                    b: {
                        c: {
                            d: ["it works!", function() {
                                print("it works!")
                            }]
                        }
                    }
                }
                            
                a["b"]["c"].d[1]()
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
