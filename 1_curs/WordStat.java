import Scanner.myNewScanner;
import java.io.*;
import java.util.*;

public class WordStat {
    public static void main(String[] args) {
        try {
            String inputFile = args[0];
            String outputFile = args[1];
            try {
                // Ввод и обработка данных
                myNewScanner in = new myNewScanner(new FileInputStream(inputFile), c -> (Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || c == '\''));
                Map<String, Integer> wordStat = new LinkedHashMap<>();
                try {
                    // считаем слова и записываем в табличку
                    while (in.hasNext()) {
                        String word;
                        while ((word = in.nextInLine()) != null) {
                            wordStat.merge(word.toLowerCase(), 1, Integer::sum);
                        }
                    }
                    // Вывод данных в outputFile
                    BufferedWriter out = new BufferedWriter(
                            new OutputStreamWriter(
                                    new FileOutputStream(outputFile), "utf8")
                    );
                    try {
                        for (String key : wordStat.keySet()) {
                            out.write(key);
                            out.write(' ');
                            out.write(wordStat.get(key).toString());
                            out.newLine();

                        }
                    } finally {
                        out.close();
                    }
                }
                finally {
                    in.close();
                }
            } catch (FileNotFoundException e) {
                System.out.println("inputFile not found:" + e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("IndexOutOfBoundsException" + e.getMessage());
        }
    }
}