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

public class InterpreterTest {

    @Test
    void print_test() {
        String value = "auid289d02";

        String input = "print(\"" + value + "\")";

        DracoLexer lexer = new DracoLexer(input);
        DracoParser parser = new DracoParser(lexer.parse(), input.split("\n"));
        List<Statement> statements = parser.parse();
        DracoInterpreter interpreter = new DracoInterpreter();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        interpreter.evaluate(statements);

        assertEquals(value, baos.toString().trim());
    }

    @Test
    void variable_test1() {
        String input = """
                a = 2137
                b = 420
                c = 69
                                
                print(b)
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
    void variable_test2() {
        String input = """
                a = "test"
                b = 11
                c = "other"
                                
                print(c)
                """.trim();

        DracoLexer lexer = new DracoLexer(input);
        DracoParser parser = new DracoParser(lexer.parse(), input.split("\n"));
        List<Statement> statements = parser.parse();
        DracoInterpreter interpreter = new DracoInterpreter();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        interpreter.evaluate(statements);

        assertEquals("other", baos.toString().trim());
    }

    @Test
    void if_test1() {
        String input = """
                a = 2
                b = 3
                                
                if (a > b) {
                    print("1")
                } else if (a < b) {
                    print("2")
                } else {
                    print("3")
                }

                """.trim();


        DracoLexer lexer = new DracoLexer(input);
        DracoParser parser = new DracoParser(lexer.parse(), input.split("\n"));
        DracoInterpreter interpreter = new DracoInterpreter();
        List<Statement> statements = parser.parse();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        interpreter.evaluate(statements);

        assertEquals("2", baos.toString().trim());
    }

    @Test
    void if_test2() {
        String input = """
                a = 3
                b = 3
                                
                if (a > b) {
                    print("1")
                } else if (a < b) {
                    print("2")
                } else {
                    print("3")
                }

                """.trim();


        DracoLexer lexer = new DracoLexer(input);
        DracoParser parser = new DracoParser(lexer.parse(), input.split("\n"));
        DracoInterpreter interpreter = new DracoInterpreter();
        List<Statement> statements = parser.parse();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        interpreter.evaluate(statements);

        assertEquals("3", baos.toString().trim());
    }

    @Test
    void function_test1() {
        String input = """
                function test(a, b, c) {
                    print(a + b + c)
                }
                            
                test(1, 2, 3)

                    """.trim();


        DracoLexer lexer = new DracoLexer(input);
        DracoParser parser = new DracoParser(lexer.parse(), input.split("\n"));
        DracoInterpreter interpreter = new DracoInterpreter();
        List<Statement> statements = parser.parse();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        interpreter.evaluate(statements);

        assertEquals("6.0", baos.toString().trim());
    }

    @Test
    void function_test2() {
        String input = """
                function invoke(fun, a, b) {
                    fun(a, b)
                }
                            
                function add(a, b) {
                    print(a + b)
                }
                            
                invoke(add, 4, 5)

                        """.trim();


        DracoLexer lexer = new DracoLexer(input);
        DracoParser parser = new DracoParser(lexer.parse(), input.split("\n"));
        DracoInterpreter interpreter = new DracoInterpreter();
        List<Statement> statements = parser.parse();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        interpreter.evaluate(statements);

        assertEquals("9.0", baos.toString().trim());
    }

    @Test
    void function_test3() {
        String input = """
                b = -1
                function invoke(a) {
                    if (a > 10) {
                        b = 0
                    } else if (a < 10) {
                        b = 1
                    } else {
                        b = 2
                    }
                }
                            
                invoke(-45)
                   
                print(b)

                            """.trim();


        DracoLexer lexer = new DracoLexer(input);
        DracoParser parser = new DracoParser(lexer.parse(), input.split("\n"));
        DracoInterpreter interpreter = new DracoInterpreter();
        List<Statement> statements = parser.parse();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        interpreter.evaluate(statements);

        assertEquals("1.0", baos.toString().trim());
    }

    @Test
    void inline_if1() {
        String input = """
                print(if (1 == 1) 1 else 2)
                """.trim();

        DracoLexer lexer = new DracoLexer(input);
        DracoParser parser = new DracoParser(lexer.parse(), input.split("\n"));
        List<Statement> statements = parser.parse();
        DracoInterpreter interpreter = new DracoInterpreter();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        interpreter.evaluate(statements);

        assertEquals("1.0", baos.toString().trim());
    }

    @Test
    void inline_if2() {
        String input = """
                a = 2
                b = 2
                print(if (a > 2) 1 else if (a == b) 2 else 3)
                """.trim();

        DracoLexer lexer = new DracoLexer(input);
        DracoParser parser = new DracoParser(lexer.parse(), input.split("\n"));
        List<Statement> statements = parser.parse();
        DracoInterpreter interpreter = new DracoInterpreter();

//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(baos));

        interpreter.evaluate(statements);

//        assertEquals("2.0", baos.toString().trim());
    }

    @Test
    void inline_if3() {
        String input = """
                a = 1
                b = 2
                                
                function test(p) {
                    print(p)
                }
                                
                test(if (a > b) "asd" else "dsa")
                """.trim();

        DracoLexer lexer = new DracoLexer(input);
        DracoParser parser = new DracoParser(lexer.parse(), input.split("\n"));
        List<Statement> statements = parser.parse();
        DracoInterpreter interpreter = new DracoInterpreter();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        interpreter.evaluate(statements);

        assertEquals("dsa", baos.toString().trim());
    }

    @Test
    void anonymous_function() {
        String input = """
                    a = function(b) {
                    b()
                }
                            
                a(function() {
                    print("dziala")
                })
                    """.trim();

        DracoLexer lexer = new DracoLexer(input);
        DracoParser parser = new DracoParser(lexer.parse(), input.split("\n"));
        List<Statement> statements = parser.parse();
        DracoInterpreter interpreter = new DracoInterpreter();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        interpreter.evaluate(statements);

        assertEquals("dziala", baos.toString().trim());
    }
}
