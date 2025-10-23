import WordStatPlusPlus.myNewScanner;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WsppPosition {

    public static void main(String[] args) {
        try {
            String inputFile = args[0];
            String outputFile = args[1];
            try {
                myNewScanner in = new myNewScanner(
                        new FileInputStream(inputFile),
                        c -> ((Character.isLetterOrDigit(c)
                                        || Character.getType(c) == Character.DASH_PUNCTUATION
                                        || c == '\'' || c == '$' || c == '_'))
                );
                Map<String, ArrayList<Integer>> wordStat = new LinkedHashMap<>();
                try {
                    int lineNumber = 1;
                    int wordInLine = 0;
                    while (true) {
                        String word = in.nextInLine();
                        if (word == null) {
                            lineNumber++;
                            if (!in.hasNext()) {
                                break;
                            }
                            continue;
                        }
                        wordInLine++;
                        wordStat.computeIfAbsent(word.toLowerCase(), k-> new ArrayList<>()).add(lineNumber);
                        wordStat.computeIfAbsent(word.toLowerCase(), k-> new ArrayList<>()).add(wordInLine);
                    }
                    BufferedWriter out = new BufferedWriter(
                            new OutputStreamWriter(
                                    new FileOutputStream(outputFile), StandardCharsets.UTF_8)
                    );
                    try {
                        List<String> entries = new ArrayList<>(wordStat.keySet());
                        entries.sort(Comparator.comparingInt(String::length));
                        try {
                            for (String key : entries) {
                                out.write(key);
                                ArrayList<Integer> list = wordStat.get(key);
                                int counter;
                                out.write(' ');
                                out.write(String.valueOf(wordStat.get(key).size() / 2));
                                for (int i = 0; i < list.size(); i += 2) {
                                    out.write(' ');
                                    out.write(list.get(i).toString());
                                    out.write(':');
                                    counter = wordInLine - list.get(i + 1) + 1;
                                    out.write(String.valueOf(counter));
                                }
                                out.newLine();
                            }
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("IndexOutOfBoundsException in valuesHashMapList" + e.getMessage());
                        }
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("IndexOutOfBoundsException in listKeyHashMap" + e.getMessage());
                    } catch (FileNotFoundException e) {
                        System.out.println("outputFile not found:" + e.getMessage());
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
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
            System.out.println("IndexOutOfBoundsException: " + e.getMessage());
        }
    }
}