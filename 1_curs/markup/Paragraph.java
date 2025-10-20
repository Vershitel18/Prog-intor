package markup;

import java.util.List;

public class Paragraph implements markup{

    List<markup> textList;
    StringBuilder element;
    public Paragraph(List<markup> textList) {
        this.textList = textList;
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        for (markup value: textList) {
            value.toMarkdown(sb);
        }
    }

}