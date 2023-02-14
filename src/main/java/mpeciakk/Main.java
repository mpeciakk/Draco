package mpeciakk;

import mpeciakk.parser.expression.Statement;
import mpeciakk.lexer.DracoLexer;
import mpeciakk.parser.DracoParser;
import mpeciakk.runtime.DracoInterpreter;

import java.util.List;

public class Main {

    static String input = """
            a = [function(s) {
                print(s)
            }, 2, "Republika poludniowej afryki"]
            
            a[0]("siur123")
            print(a[1])
            print(a[2])
                        """.trim();

    public static void main(String[] args) {
        DracoLexer lexer = new DracoLexer(input);
        DracoParser parser = new DracoParser(lexer.parse(), input.split("\n"));
        DracoInterpreter interpreter = new DracoInterpreter();
        List<Statement> statements = parser.parse();
        interpreter.evaluate(statements);
    }
}
