package WordStatPlusPlus;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Wspp {
    public static void main(String[] args) {
        try {
            String inputFile = args[0];
            String outputFile = args[1];
            try {
                // Ввод и обработка данных
                myNewScanner in = new myNewScanner(
                        new FileInputStream(inputFile),
                        c -> ((Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || c == '\'')));
                Map<String, ArrayList<Integer>> wordStat = new LinkedHashMap<>();
                try {
                    int count = 0;
                    while (in.hasNext()) {
                        String word;
                        while ((word = in.nextInLine()) != null) {
                            count++;
                            wordStat.computeIfAbsent(word.toLowerCase(), k-> new ArrayList<>()).add(count);
                        }
                    }
                    // Вывод данных в outputFile
                    BufferedWriter out = new BufferedWriter(
                            new OutputStreamWriter(
                                    new FileOutputStream(outputFile), StandardCharsets.UTF_8)
                    );
                    try {
                        for (String key : wordStat.keySet()) {
                            out.write(key);
                            out.write(' ');
                            out.write(String.valueOf(wordStat.get(key).size()));
                            for (Integer element: wordStat.get(key)) {
                                out.write(' ');
                                out.write(element.toString());
                            }
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
                System.out.println("File not found:" + e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("IndexOutOfBoundsException" + e.getMessage());
        }
    }
}