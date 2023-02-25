package mpeciakk.parser;

import mpeciakk.lexer.Token;

public class DracoParseError extends Error {

    private final String line;
    private final int lineIndex;
    private final int characterIndex;
    private final Token token;
    private final String issue;

    public DracoParseError(String message) {
        super(message);
        this.issue = null;
        this.line = "";
        this.lineIndex = 0;
        this.characterIndex = 0;
        this.token = null;
    }

    public DracoParseError(String issue, String line, int lineIndex, Token token) {
        super();
        this.issue = issue;
        this.line = line;
        this.lineIndex = lineIndex;
        this.characterIndex = token.start() - token.lineStart();
        this.token = token;
    }

    @Override
    public String getMessage() {
        if (issue == null) {
            return super.getMessage();
        }

        var leadingWhitespace = line.indexOf(line.trim());
        var pipeOffset = String.valueOf(lineIndex + 1).length() - 1;
        var arrow = " ".repeat(characterIndex - leadingWhitespace) + "^".repeat(token == null || token.literal() == null ? 1 : ((String) token.literal()).length());
        var offsetPipe = " ".repeat(pipeOffset) + "|";
        var pipe = "|";

        return String.format("""
                        %s:
                           %s
                         %d %s   %s
                           %s   %s
                           %s
                        """.trim(),
                issue,
                offsetPipe,
                lineIndex + 1,
                offsetPipe,
                line.trim(),
                pipe,
                arrow,
                offsetPipe);
    }
}
