package mpeciakk.parser.syntax;

import mpeciakk.parser.DracoParser;

public abstract class DracoSyntax {

    public abstract boolean match(DracoParser parser);

    public abstract void parse(DracoParser parser, SyntaxEnvironment environment);
}
