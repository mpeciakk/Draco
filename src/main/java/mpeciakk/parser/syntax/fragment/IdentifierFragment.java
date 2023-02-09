package mpeciakk.parser.syntax.fragment;

import mpeciakk.lexer.Token;
import mpeciakk.lexer.TokenType;
import mpeciakk.parser.DracoParser;

public final class IdentifierFragment extends Fragment<Token> {

    public IdentifierFragment(boolean optional) {
        super(optional);
    }

    @Override
    public Token match(DracoParser parser) {
        return parser.consume(TokenType.IDENTIFIER, "Expected identifier");
    }

    public static IdentifierFragment of() {
        return new IdentifierFragment(false);
    }

    public static IdentifierFragment optional() {
        return new IdentifierFragment(true);
    }
}