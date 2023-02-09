package mpeciakk.parser.syntax.fragment;

import mpeciakk.parser.DracoParser;
import mpeciakk.parser.syntax.SyntaxEnvironment;
import mpeciakk.parser.syntax.DracoSyntaxBuilder;

import java.util.ArrayList;
import java.util.List;

public class RepeatingFragment extends Fragment<List<SyntaxEnvironment>> {

    private final DracoSyntaxBuilder syntaxBuilder;

    private RepeatingFragment(DracoSyntaxBuilder syntaxBuilder, boolean optional) {
        super(optional);
        this.syntaxBuilder = syntaxBuilder;
    }

    @Override
    public List<SyntaxEnvironment> match(DracoParser parser) {
        List<SyntaxEnvironment> result = new ArrayList<>();

        while (true) {
            SyntaxEnvironment environment = new SyntaxEnvironment();
            if (syntaxBuilder.match(parser, environment)) {
                result.add(environment);
            } else {
                break;
            }
        }

        return result;
    }

    public static RepeatingFragment of(Fragment... fragments) {
        return new RepeatingFragment(new DracoSyntaxBuilder(false, fragments), false);
    }

    public static RepeatingFragment of(DracoSyntaxBuilder syntaxBuilder) {
        return new RepeatingFragment(syntaxBuilder, false);
    }

    public static RepeatingFragment optional(Fragment... fragments) {
        return new RepeatingFragment(new DracoSyntaxBuilder(false, fragments), true);
    }

    public static RepeatingFragment optional(DracoSyntaxBuilder syntaxBuilder) {
        return new RepeatingFragment(syntaxBuilder, true);
    }
}
