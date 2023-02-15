package mpeciakk;

import mpeciakk.parser.expression.Statement;
import mpeciakk.lexer.DracoLexer;
import mpeciakk.parser.DracoParser;
import mpeciakk.runtime.DracoInterpreter;

import java.util.List;

public class Main {

    static String input = """
            a = {
                key: "value",
                number: 420,
                f: function(a) {
                    print("function a: " + a)
                }
            }
            
            print(a.key)
            print(a.number)
            a.f("test")
                        """.trim();

    public static void main(String[] args) {
        DracoLexer lexer = new DracoLexer(input);
        DracoParser parser = new DracoParser(lexer.parse(), input.split("\n"));
        DracoInterpreter interpreter = new DracoInterpreter();
        List<Statement> statements = parser.parse();
        interpreter.evaluate(statements);
    }
}
