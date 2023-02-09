package mpeciakk.parser.syntax.fragment;

import mpeciakk.parser.DracoParser;
import mpeciakk.parser.syntax.SyntaxEnvironment;
import mpeciakk.parser.syntax.DracoSyntaxBuilder;

public class BlockFragment extends SimpleFragment {

    private final DracoSyntaxBuilder syntaxBuilder;

    private BlockFragment(DracoSyntaxBuilder syntaxBuilder, boolean optional) {
        super(optional);
        this.syntaxBuilder = syntaxBuilder;
    }

    public boolean match(DracoParser parser, SyntaxEnvironment environment) {
        return syntaxBuilder.match(parser, environment);
    }

    @Override
    public Boolean match(DracoParser parser) {
        return null;
    }

    public static BlockFragment of(Fragment<?>... fragments) {
        return new BlockFragment(new DracoSyntaxBuilder(false, fragments), false);
    }

    public static BlockFragment of(DracoSyntaxBuilder syntaxBuilder) {
        return new BlockFragment(syntaxBuilder, false);
    }

    public static BlockFragment optional(Fragment<?>... fragments) {
        return new BlockFragment(new DracoSyntaxBuilder(false, fragments), true);
    }

    public static BlockFragment optional(DracoSyntaxBuilder syntaxBuilder) {
        return new BlockFragment(syntaxBuilder, true);
    }
}
