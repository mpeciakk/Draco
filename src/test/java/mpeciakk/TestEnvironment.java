package mpeciakk;

import mpeciakk.lexer.DracoLexer;
import mpeciakk.object.DracoObject;
import mpeciakk.parser.DracoParser;
import mpeciakk.parser.expression.statement.Statement;
import mpeciakk.runtime.DracoInterpreter;

import java.util.List;

public class TestEnvironment {

    private DracoLexer lexer;
    private DracoParser parser;
    private DracoInterpreter interpreter;

    public void interpret(String input) {
        lexer = new DracoLexer(input);
        parser = new DracoParser(lexer.parse(), input.split("\n"));
        interpreter = new DracoInterpreter();
        interpreter.evaluate(parser.parse());
    }

    public List<Statement> parse(String input) {
        lexer = new DracoLexer(input);
        parser = new DracoParser(lexer.parse(), input.split("\n"));
        return parser.parse();
    }

    public DracoObject get(String name) {
        return interpreter.getEnvironment().get(name);
    }
}
