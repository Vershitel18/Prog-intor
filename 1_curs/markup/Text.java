package markup;

public class Text implements markup{
    String text;
    public Text(String text){
        this.text = text;
    }
    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(text);
    }
}