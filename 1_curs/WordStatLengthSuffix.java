import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class WordStatLengthSuffix {
        public static void main(String[] args) {
            try {
                String inputFile = args[0];
                String outputFile = args[1];
                try {
                    myNewScanner in = new myNewScanner(new FileInputStream(inputFile), c -> (Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || c == '\''));
                    Map<String, Integer> wordStat = new LinkedHashMap<>();
                    try {
                        while (in.hasNext()) {
                            String word;
                            while ((word = in.nextInLine()) != null) {
                                if (word.length()>=2) {
                                    wordStat.merge(word.substring(word.length()-word.length()/2).toLowerCase(), 1, Integer::sum);
                                }
                            }
                        }
                        try {
                            BufferedWriter out = new BufferedWriter(
                                    new OutputStreamWriter(
                                            new FileOutputStream(outputFile), StandardCharsets.UTF_8)
                            );
                            List<String> entries = new ArrayList<>(wordStat.keySet());
                            entries.sort(Comparator.comparingInt(String::length));
                            try {
                                for (String key : entries) {
                                    out.write(key);
                                    out.write(' ');
                                    out.write(wordStat.get(key).toString());
                                    out.newLine();

                                }
                            } finally {
                                out.close();
                            }
                        } catch (FileNotFoundException e) {
                            System.out.println("outputFile not found:" + e.getMessage());
                        } catch (IOException e) {
                            System.out.println("IOExeption outputFile:" + e.getMessage());
                        }

                    } finally {
                        in.close();
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("inputFile not found:" + e.getMessage());
                } catch (IOException e) {
                    System.out.println("IOExeption inputFile:"+ e.getMessage());
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("IndexOutOfBoundsException" + e.getMessage());
            }
        }
}
