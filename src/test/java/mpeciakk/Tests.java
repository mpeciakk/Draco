package mpeciakk;

import mpeciakk.lexer.Token;
import mpeciakk.lexer.TokenType;
import mpeciakk.parser.expression.statement.Statement;
import mpeciakk.lexer.DracoLexer;
import mpeciakk.parser.DracoParser;
import mpeciakk.runtime.DracoInterpreter;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {

    @Test
    void printTest() {
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
    void variableTest1() {
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
    void variableTest2() {
        String input = """
                a = "test"
                b = "tset"
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
    void ifTest1() {
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
    void ifTest2() {
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
    void functionTest1() {
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
    void functionTest2() {
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
    void functionTest3() {
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
    void inlineIfTest1() {
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
    void inlineIfTest2() {
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
    void inlineIfTest3() {
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
    void anonymousFunctionTest() {
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

    public static void assertTokensMatch(List<Token> tokens, TokenType... types) {
        for (int i = 0; i < types.length; i++) {
            if(tokens.get(i).type() != types[i]) {
                throw new IllegalStateException(String.format("Token at index %d did not match expected type %s (found %s)!\nFull token stack: %s", i, types[i], tokens.get(i).type(), String.join(", ", tokens.stream().map(Token::type).map(Enum::name).toList())));
            }
        }
    }

    @SafeVarargs
    public static void assertStatementsMatch(List<Statement> tokens, Class<? extends Statement>... statements) {
        for (int i = 0; i < statements.length; i++) {
            if(tokens.size() <= i) {
                throw new IllegalStateException(String.format("Statement at index %d did not match expected type %s (found nothing!)!", i, statements[i]));
            } else if(tokens.get(i).getClass() != statements[i]) {
                throw new IllegalStateException(String.format("Statement at index %d did not match expected type %s (found %s!)!", i, statements[i], tokens.get(i).getClass()));
            }
        }
    }
}
