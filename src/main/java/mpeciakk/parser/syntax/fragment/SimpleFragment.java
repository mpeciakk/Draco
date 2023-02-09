package mpeciakk.parser.syntax.fragment;

import mpeciakk.parser.DracoParser;

public class SimpleFragment extends Fragment<Boolean> {

    public SimpleFragment(boolean optional) {
        super(optional);
    }

    @Override
    public Boolean match(DracoParser parser) {
        return null;
    }
}
