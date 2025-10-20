package markup;
import java.util.List;

public class Emphasis extends Paragraph implements markup{

    public Emphasis(List<markup> textList) {
        super(textList);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append("*");
        for (markup value: textList) {
            value.toMarkdown(sb);
        }
        sb.append("*");
    }
}