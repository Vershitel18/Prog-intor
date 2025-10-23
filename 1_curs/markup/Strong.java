package markup;

import java.util.List;

public class Strong extends toMarkdown{

    public Strong(List<markup> listValue) {
        super(listValue);
    }

    @Override
    protected String getOperandMarkdownFromChild() {
        return "__";
    }

    @Override
    protected String getOperandTexFromChild() {
        return "\\textbf";
    }
}