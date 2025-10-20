package markup;

import java.util.List;

public class Strong extends Paragraph implements markup{

    public Strong(List<markup> textList) {
        super(textList);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append("__");
        for (markup value: textList) {
            value.toMarkdown(sb);
        }
        sb.append("__");
    }
}