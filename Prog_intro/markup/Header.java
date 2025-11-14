package markup;

import java.util.List;

public class Header implements Markdown, ToHtml {

    List<ToMarkup> textList;
    int levelHeaders;
    public Header(List<ToMarkup> textList, int levelHeaders) {
        this.textList = textList;
        this.levelHeaders = levelHeaders;
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        for (int i = 0; i < levelHeaders; i++) {
            sb.append('#');
        }
        sb.append(' ');
        for (ToMarkup value : textList) {
            value.toMarkdown(sb);
        }
    }

    @Override
    public void toHtml(StringBuilder sb) {
        sb.append("<h").append(String.valueOf(levelHeaders)).append(">");
        for (ToMarkup value: textList) {
            value.toHtml(sb);
        }
        sb.append("</h").append(String.valueOf(levelHeaders)).append(">");
    }
}