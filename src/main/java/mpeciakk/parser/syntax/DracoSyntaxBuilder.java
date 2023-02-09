package mpeciakk.parser.syntax;

import mpeciakk.DracoError;
import mpeciakk.parser.DracoParser;
import mpeciakk.parser.syntax.fragment.*;

public record DracoSyntaxBuilder(boolean error, Fragment<?>... fragments) {

    public boolean match(DracoParser parser, SyntaxEnvironment environment) {
        for (Fragment<?> fragment : fragments) {
            // If it's end of file, but the fragment is optional we want to return true
            if (parser.isAtEnd()) {
                if (!fragment.isOptional()) {
                    fragment.match(parser);
                    return false;
                } else {
                    return true;
                }
            }

            int index = parser.getIndex();

            if (fragment instanceof BlockFragment blockFragment) {
                if (!blockFragment.match(parser, environment)) {
                    // If the fragment is not matched but is optional we want to reset index, so the next fragment can parse used tokens again, otherwise throw error
                    if (blockFragment.isOptional()) {
                        parser.setIndex(index);
                        continue;
                    } else {
                        return false;
                    }
                }

                continue;
            }

            if (fragment instanceof SimpleFragment simpleFragment) {
                boolean result = simpleFragment.match(parser);

                // If fragment is matched we want to advance and check further
                if (result) {
                    parser.advance();

                    continue;
                }

                return false;
            }

            try {
                Object match = fragment.match(parser);
                environment.set(fragment, match);
            } catch (Throwable exception) {
                // If the fragment cannot be matched, but it's optional we still want to parse further, otherwise stop and throw error
                if (fragment.isOptional()) {
                    parser.setIndex(index);

                    continue;
                }

                if (error) {
                    throw exception;
                } else {
                    return false;
                }
            }
        }

        return true;
    }
}
