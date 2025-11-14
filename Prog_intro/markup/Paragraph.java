package markup;

import java.util.List;

public class Paragraph implements Markdown, ToHtml {

    List<ToMarkup> textList;
    public Paragraph(List<ToMarkup> textList) {
        this.textList = textList;
    }

    public void toMarkdown(StringBuilder sb) {
        for (ToMarkup value: textList) {
            value.toMarkdown(sb);
        }
    }

    @Override
    public void toHtml(StringBuilder sb) {
        sb.append("<p>");
        for (ToMarkup value: textList) {
            value.toHtml(sb);
        }
        sb.append("</p>");
    }
}