package mpeciakk.runtime;

import mpeciakk.lexer.Token;

public class DracoRuntimeError extends Error {
    private final String line;
    private final int lineIndex;
    private final int characterIndex;
    private final Token token;
    private final String issue;

    public DracoRuntimeError(String message) {
        super(message);
        this.issue = null;
        this.line = "";
        this.lineIndex = 0;
        this.characterIndex = 0;
        this.token = null;
    }

    public DracoRuntimeError(String issue, String line, int lineIndex, Token token) {
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
                           %s   %s
                         %d %s   %s
                           %s
                        """,
                issue,
                offsetPipe,
                offsetPipe,
                line.trim(),
                lineIndex + 1,
                pipe,
                arrow,
                offsetPipe);
    }

    @Override
    public void printStackTrace() {
        if (issue != null) {
            return;
        }

        super.printStackTrace();
    }
}
