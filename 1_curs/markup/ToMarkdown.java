package markup;

import java.util.List;

public abstract class toMarkdown implements ToMarkup{
    protected List<markup> listValue;

    public toMarkdown(List<markup> listValue) {
        this.listValue = listValue;
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        String operandMardown = getOperandMarkdownFromChild();
        sb.append(operandMardown);
        for (markup value: listValue) {
            value.toMarkdown(sb);
        }
        sb.append(operandMardown);
    }

    protected abstract String getOperandMarkdownFromChild();
    protected abstract String getOperandTexFromChild();

    @Override
    public void toTex(StringBuilder sb) {
        String operandTex = getOperandTexFromChild();
        sb.append(operandTex);
        sb.append("{");
        for (markup value: listValue) {
            value.toMarkdown(sb);
        }
        sb.append("}");
    }

}