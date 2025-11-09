package md2html;
import markup.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Md2Html {
    private static HashMap<Character, String> htmlTegs = new HashMap<>() {{
        put('*', "em");
        put('_', "em");
        put('-', "so");
        put('`', "c");
        put('!', "te");
    }};

    public static void main(String[] args) {
        String inputFile = args[0];
        String outputFile = args[1];
        try (Scanner scanner = new Scanner(new File(inputFile), StandardCharsets.UTF_8)) {
            StringBuilder paragraph = new StringBuilder();
            StringBuilder output = new StringBuilder();
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.isEmpty() && !paragraph.isEmpty()) {
                    output.append(Md2Html(paragraph)).append(System.lineSeparator());
                    paragraph.setLength(0);
                } else {
                    if (!paragraph.isEmpty()) {
                        paragraph.append(System.lineSeparator());
                    }
                    paragraph.append(line);
                }
            }
            if (!paragraph.isEmpty()) {
                output.append(Md2Html(paragraph));
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, StandardCharsets.UTF_8))) {
                writer.write(output.toString());
            } catch (IOException e) {
                System.out.println("Output Error" + e.getMessage());
            }

        } catch (IOException e) {
            System.out.println("InputFile Error" + e.getMessage());
        }
    }

    public static StringBuilder Md2Html(StringBuilder paragraph) {
        StringBuilder block = new StringBuilder();
        if (paragraph.charAt(0) != '#') {
            Paragraph paragraph1 = new Paragraph(parse(paragraph, 0, paragraph.length()));
            paragraph1.toHtml(block);
        } else {
            int i = 1;
            int levelHeaders = 1;
            while (i < paragraph.length() && paragraph.charAt(i) == '#') {
                i++;
                levelHeaders++;
            }
            if (i < paragraph.length() && paragraph.charAt(i) != ' ') {
                Paragraph paragraph1 = new Paragraph(parse(paragraph, 0, paragraph.length()));
                paragraph1.toHtml(block);
                return block; 
            }
            Header paragraph1 = new Header(parse(paragraph, levelHeaders+1, paragraph.length()), levelHeaders);
            paragraph1.toHtml(block);
        }
        return block;
    }

    public static List<ToMarkup> parse(StringBuilder paragraph, int start, int end) {
        List<ToMarkup> result = new ArrayList<>();
        StringBuilder textBuff = new StringBuilder();
        int markerEnd;
        int len;
        for(int i = start; i < end; i++){
            char ch = paragraph.charAt(i);
            if (ch == '\\' && (i + 1 < end && htmlTegs.containsKey(paragraph.charAt(i + 1)))) {
                textBuff.append(paragraph.charAt(++i));
                if (i+1 < end && paragraph.charAt(i+1) == ch) {
                    textBuff.append(paragraph.charAt(++i));
                }
                continue;
            }
            if (htmlTegs.containsKey(ch)) {
                if (!textBuff.isEmpty()) {
                    result.add(new Text(textBuff.toString()));
                    textBuff.setLength(0);
                }
                len = 1;
                if (i + 1 < end && paragraph.charAt(i + 1) == ch) {
                    len = 2;
                }
                markerEnd = findClose(paragraph, i+len, end, ch, len);
                if (markerEnd != -1 && !(markerEnd > 0 && paragraph.charAt(markerEnd-1) == '\\')) {
                    result.add(returnToMarkupClass(ch, len, paragraph, i + len, markerEnd));
                    i = markerEnd + len-1;
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
            if ((i == end - 1 && len == 2) || (len == 2 && text.charAt(i+1) != marker)) continue;
            if (i != end -1 && (len < 2 && text.charAt(i+1) == marker && htmlTegs.get(marker).length() >= 2)) {
                if (findClose(text, start, end, marker, 2) == -1) return i;
                i++;
                continue;
            }
            if (len == 2 && text.charAt(i) == marker) return i;
            return i;
        }
        return -1;
    }

    public static ToMarkup returnToMarkupClass(char marker, int len, StringBuilder text, int start, int end){
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
        if (marker == '!' && len == 1) {
            return new Text(text.substring(start-1, end+1));
        }
        return new Text(text.substring(start, end));
    }

}