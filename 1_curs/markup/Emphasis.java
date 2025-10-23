package markup;
import java.util.List;

public class Emphasis extends toMarkdown implements markup{

    public Emphasis(List<markup> listValue) {
        super(listValue);
    }

    @Override
    protected String getOperandMarkdownFromChild() {
        return "*";
    }

    @Override
    protected String getOperandTexFromChild() {
        return "emph";
    }

}