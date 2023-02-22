package mpeciakk;

import mpeciakk.lexer.DracoLexer;
import mpeciakk.lexer.Token;
import mpeciakk.parser.DracoParser;
import mpeciakk.parser.Statement;

import java.util.List;

public class TestEnvironment {

    private DracoLexer lexer;
    private DracoParser parser;

    public List<Statement> parse(String input) {
        lexer = new DracoLexer(input);
        parser = new DracoParser(lexer.parse(), input.split("\n"));
        return parser.parse();
    }

    public List<Token> lex(String input) {
        lexer = new DracoLexer(input);
        return lexer.parse();
    }
}
