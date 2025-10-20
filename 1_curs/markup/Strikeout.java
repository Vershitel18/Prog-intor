package markup;
import markup.Text;

import java.util.List;

public class Strikeout extends Paragraph implements markup{

    public Strikeout(List<markup> textList) {
        super(textList);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append("~");
        for (markup value: textList) {
            value.toMarkdown(sb);
        }
        sb.append("~");
    }
}