package mpeciakk.parser.syntax.fragment;

import mpeciakk.lexer.TokenType;
import mpeciakk.parser.DracoParser;

public class TokenFragment extends SimpleFragment {

    private final TokenType value;

    private TokenFragment(TokenType value, boolean optional) {
        super(optional);
        this.value = value;
    }

    @Override
    public Boolean match(DracoParser parser) {
        return parser.getCurrent().type() == value;
    }

    public static TokenFragment of(TokenType value) {
        return new TokenFragment(value, false);
    }

    public static TokenFragment optional(TokenType value) {
        return new TokenFragment(value, true);
    }
}
