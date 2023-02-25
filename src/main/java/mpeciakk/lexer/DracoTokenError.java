package mpeciakk.lexer;

public class DracoTokenError extends Error {

    private final String line;
    private final int lineIndex;
    private final int index;
    private final String issue;

    public DracoTokenError(String message) {
        super(message);
        this.issue = null;
        this.line = "";
        this.lineIndex = 0;
        this.index = 0;
    }

    public DracoTokenError(String issue, String line, int lineIndex, int index) {
        super();
        this.issue = issue;
        this.line = line;
        this.lineIndex = lineIndex;
        this.index = index;
    }

    @Override
    public String getMessage() {
        if (issue == null) {
            return super.getMessage();
        }

        var leadingWhitespace = line.indexOf(line.trim());
        var pipeOffset = String.valueOf(lineIndex + 1).length() - 1;
        var arrow = " ".repeat(index - leadingWhitespace) + "^";
        var offsetPipe = " ".repeat(pipeOffset) + "|";
        var pipe = "|";

        return String.format("""
                        %s:
                           %s
                         %d%s   %s
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
