package markup;

public interface ToMarkup extends markup, toTex{
    void toMarkdown(StringBuilder sb);
    void toTex(StringBuilder sb);
}