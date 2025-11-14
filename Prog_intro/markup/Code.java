package markup;

import java.util.List;

public class Code extends ToMarkdown {

    public Code(List<ToMarkup> listValue) {
        super(listValue);
    }

    @Override
    protected String getOperandMarkdownFromChild() {
        return "'";
    }

    @Override
    protected String getOperandHtmlFromChild() {
        return "code";
    }

}