package markup;

import java.util.List;

public abstract class ToMarkdown implements ToMarkup{
    protected List<ToMarkup> listValue;

    public ToMarkdown(List<ToMarkup> listValue) {
        this.listValue = listValue;
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        String operandMarkdown = getOperandMarkdownFromChild();
        sb.append(operandMarkdown);
        for (ToMarkup value: listValue) {
            value.toMarkdown(sb);
        }
        sb.append(operandMarkdown);
    }

    protected abstract String getOperandMarkdownFromChild();
    protected abstract String getOperandHtmlFromChild();

    @Override
    public void toHtml(StringBuilder sb) {
        String operandTex = getOperandHtmlFromChild();
        sb.append("<");
        sb.append(operandTex);
        sb.append(">");
        for (ToMarkup value: listValue) {
            value.toHtml(sb);
        }
        sb.append("</");
        sb.append(operandTex);
        sb.append(">");
    }

}