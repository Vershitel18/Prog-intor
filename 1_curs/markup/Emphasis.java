package markup;
import java.util.List;

public class Emphasis extends ToMarkdown {

    public Emphasis(List<ToMarkup> listValue) {
        super(listValue);
    }

    @Override
    protected String getOperandMarkdownFromChild() {
        return "*";
    }

    @Override
    protected String getOperandHtmlFromChild() {
        return "em";
    }

}