package mpeciakk.parser.syntax.fragment;

import mpeciakk.expression.statement.Statement;
import mpeciakk.parser.DracoParser;

public class StatementFragment extends Fragment<Statement> {

    private StatementFragment(boolean optional) {
        super(optional);
    }

    @Override
    public Statement match(DracoParser parser) {
        return parser.statement();
    }

    public static StatementFragment of() {
        return new StatementFragment(false);
    }

    public static StatementFragment optional() {
        return new StatementFragment(true);
    }
}
