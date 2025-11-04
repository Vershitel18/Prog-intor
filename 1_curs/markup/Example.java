package markup;

import java.util.List;

public class Example extends ToMarkdown {

    public Example(List<ToMarkup> listValue) {
        super(listValue);
    }

    @Override
    protected String getOperandMarkdownFromChild() {
        return "!";
    }

    @Override
    protected String getOperandHtmlFromChild() {
        return "samp";
    }

}