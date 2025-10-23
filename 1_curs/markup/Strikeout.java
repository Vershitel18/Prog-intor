package markup;
import markup.Text;

import java.util.List;

public class Strikeout extends toMarkdown{

    public Strikeout(List<markup> listValue) {
        super(listValue);
    }

    @Override
    protected String getOperandMarkdownFromChild() {
        return "~";
    }
    @Override
    protected String getOperandTexFromChild() {
        return "\\textst";
    }
}