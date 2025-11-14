package markup;

import java.util.List;

public class Strikeout extends ToMarkdown {

    public Strikeout(List<ToMarkup> listValue) {
        super(listValue);
    }

    @Override
    protected String getOperandMarkdownFromChild() {
        return "~";
    }
    @Override
    protected String getOperandHtmlFromChild() {
        return "s";
    }
}