package mpeciakk.parser.syntax.fragment;

import mpeciakk.parser.DracoParser;

public final class LiteralFragment extends SimpleFragment {

    private final String value;

    private LiteralFragment(String value, boolean optional) {
        super(optional);

        this.value = value;
    }

    @Override
    public Boolean match(DracoParser parser) {
        if (isOptional()) {
            return true;
        }

        return value.equals(parser.getCurrent().literal());
    }

    public static LiteralFragment of(String value) {
        return new LiteralFragment(value, false);
    }

    public static LiteralFragment optional(String value) {
        return new LiteralFragment(value, true);
    }
}