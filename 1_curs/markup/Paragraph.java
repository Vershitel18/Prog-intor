package markup;

import java.util.List;

public class Paragraph implements markup, toTex{

    List<markup> textList;
    public Paragraph(List<markup> textList) {
        this.textList = textList;
    }

    public void toMarkdown(StringBuilder sb) {
        for (markup value: textList) {
            value.toMarkdown(sb);
        }
    }

    @Override
    public void toTex(StringBuilder sb) {
        sb.append("\\par{");
        for (markup value: textList) {
            value.toMarkdown(sb);
        }
        sb.append("}");
    }
}