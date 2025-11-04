package markup;

import java.util.List;

public class Strong extends ToMarkdown {

    public Strong(List<ToMarkup> listValue) {
        super(listValue);
    }

    @Override
    protected String getOperandMarkdownFromChild() {
        return "__";
    }

    @Override
    protected String getOperandHtmlFromChild() {
        return "strong";
    }
}