package mpeciakk.parser.syntax.fragment;

import mpeciakk.expression.Expression;
import mpeciakk.parser.DracoParser;

public class ExpressionFragment extends Fragment<Expression> {

    private ExpressionFragment(boolean optional) {
        super(optional);
    }

    @Override
    public Expression match(DracoParser parser) {
        return parser.expression();
    }

    public static ExpressionFragment of() {
        return new ExpressionFragment(false);
    }

    public static ExpressionFragment optional() {
        return new ExpressionFragment(true);
    }
}
