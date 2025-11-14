package md2html;

import markup.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Md2Html {
    private static final Set<Character> HTML_TAGS = Set.of('*', '_','-', '`', '!');

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Argument error");
            return;
        }
        String inputFile = args[0];
        String outputFile = args[1];

        // :NOTE: можно использовать buffered reader
        try (Scanner scanner = new Scanner(new File(inputFile), StandardCharsets.UTF_8)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, StandardCharsets.UTF_8))) {
                StringBuilder paragraph = new StringBuilder();
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (!line.isEmpty()) {
                        if (!paragraph.isEmpty()) {
                            paragraph.append(System.lineSeparator());
                        }
                        paragraph.append(line);
                    }
                    if (!paragraph.isEmpty() && (line.isEmpty() || !scanner.hasNextLine())) {
                        writer.append(md2Htmls(paragraph).append(System.lineSeparator()));
                        paragraph.setLength(0);
                    }
                }
            } catch (IOException e) {
                System.out.println("Output Error" + e.getMessage());
            }
        } catch (FileNotFoundException e) {
            System.out.println("inputFile not found" + e.getMessage());
        } catch (IOException e) {
            System.out.println("Input Error" + e.getMessage());
        }
    }

    public static StringBuilder md2Htmls(StringBuilder paragraph) {
        StringBuilder block = new StringBuilder();
        int levelHeaders = 1;
        while (levelHeaders < paragraph.length() && paragraph.charAt(levelHeaders) == '#') {
            levelHeaders++;
        }
        if (paragraph.charAt(0) != '#' || (levelHeaders < paragraph.length() && paragraph.charAt(levelHeaders) != ' ')) {
            Paragraph outputBlockText = new Paragraph(parse(paragraph, 0, paragraph.length()));
            outputBlockText.toHtml(block);
        } else {
            Header outputBlockText = new Header(parse(paragraph, levelHeaders + 1, paragraph.length()), levelHeaders);
            outputBlockText.toHtml(block);
        }
        return block;
    }

    public static List<ToMarkup> parse(StringBuilder paragraph, int start, int end) {
        List<ToMarkup> result = new ArrayList<>();
        StringBuilder textBuff = new StringBuilder();
        int markerEnd;
        int len;
        for (int i = start; i < end; i++) {
            char ch = paragraph.charAt(i);
            if (i + 1 < end && ch == '\\' && HTML_TAGS.contains(paragraph.charAt(i + 1))) {
                textBuff.append(paragraph.charAt(++i));
                if (i + 1 < end && paragraph.charAt(i + 1) == ch) {
                    textBuff.append(paragraph.charAt(++i));
                }
                continue;
            }
            if (HTML_TAGS.contains(ch)) {
                if (!textBuff.isEmpty()) {
                    result.add(new Text(textBuff.toString()));
                    textBuff.setLength(0);
                }
                len = 1;
                if (i + 1 < end && paragraph.charAt(i + 1) == ch) {
                    len = 2;
                }
                if ((ch == '!' || ch == '-') && len == 1) {
                    textBuff.append(ch);
                    continue;
                }
                markerEnd = findClose(paragraph, i + len, end, ch, len);
                if (markerEnd != -1 && !(markerEnd > 0 && paragraph.charAt(markerEnd - 1) == '\\')) {
                    result.add(returnToMarkupClass(ch, len, paragraph, i + len, markerEnd));
                    i = markerEnd + len - 1;
                    continue;
                }
            }
            textBuff.append(ch);
        }
        if (!textBuff.isEmpty()) {
            result.add(new Text(textBuff.toString()));
        }
        return result;
    }

    public static int findClose(StringBuilder text, int start, int end, char marker, int len) {
        for (int i = start; i < end; i++) {
            if (text.charAt(i) != marker) continue;
            if ((i == end - 1 && len == 2) || (len == 2 && text.charAt(i + 1) != marker)) continue;
            if (i != end - 1 && (len < 2 && text.charAt(i + 1) == marker && marker != '`')) {
                i++;
                continue;
            }
            return i;
        }
        return -1;
    }

    public static ToMarkup returnToMarkupClass(char marker, int len, StringBuilder text, int start, int end) {
        if (marker == '*' || marker == '_') {
            if (len == 2) {
                return new Strong(parse(text, start, end));
            } else {
                return new Emphasis(parse(text, start, end));
            }
        }
        if (marker == '-' && len == 2) {
            return new Strikeout(parse(text, start, end));
        }
        if (marker == '`' && len == 1) {
            return new Code(parse(text, start, end));
        }
        if (marker == '!' && len == 2) {
            return new Example(parse(text, start, end));
        }
        return new Text(text.substring(start, end));
    }
}