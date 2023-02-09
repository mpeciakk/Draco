package mpeciakk.parser.syntax.fragment;

import mpeciakk.parser.DracoParser;
import mpeciakk.parser.syntax.SyntaxKey;

public abstract class Fragment<T> implements SyntaxKey<T> {

    private final boolean optional;

    public Fragment(boolean optional) {
        this.optional = optional;
    }

    public abstract T match(DracoParser parser);

    public boolean isOptional() {
        return optional;
    }
}