import java.util.function.Predicate;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class myNewScanner implements AutoCloseable {
    private BufferedReader bufferedReader;
    private InputStream inputStream;
    private Predicate<Character> isWordChar;
    private char pushBackChar;

    public myNewScanner(InputStream inputStream, Predicate<Character> isWordChar) { // конструктор для чтения потока ввода
        this.inputStream = inputStream;
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8), 8192);
        this.isWordChar = isWordChar;
    }
    public myNewScanner(String text, Predicate<Character> isWordChar) {
        this.inputStream = new ByteArrayInputStream(text.getBytes());
        this.bufferedReader = new BufferedReader(new InputStreamReader(this.inputStream, StandardCharsets.UTF_8), 8192);
        this.isWordChar = isWordChar;
    }

    public boolean hasNext() throws IOException {
        if (pushBackChar != 0) {
            return true;
        }
        bufferedReader.mark(1);
        int ch = bufferedReader.read();
        if (ch == -1) return false;
        bufferedReader.reset();
        return true;
    }

    public String nextInLine() throws IOException {
        int ch;
        String lineSeparator = System.lineSeparator();

        if (pushBackChar != 0) {
            ch = pushBackChar;
            pushBackChar = 0;
        } else {
            ch = bufferedReader.read();
        }
        while (ch != -1) {
            if (ch == lineSeparator.charAt(0)) {
                bufferedReader.mark(lineSeparator.length());
                boolean isSeparator = true;
                for (int i = 1; i < lineSeparator.length(); i++) {
                    int nextCh = bufferedReader.read();
                    if (nextCh == -1 || (char)nextCh != lineSeparator.charAt(i)) {
                        isSeparator = false;
                        bufferedReader.reset();
                        break;
                    }
                }
                if (isSeparator) {
                    pushBackChar = ' ';
                    return null;
                }
            }
            if (isWordChar.test((char)ch)) {
                StringBuilder sb = new StringBuilder();
                sb.append((char) ch);
                while (true) {
                    ch = bufferedReader.read();
                    if (ch == -1 || !isWordChar.test((char)ch)) {
                        if (ch != -1) {
                            pushBackChar = (char) ch;
                        }
                        break;
                    }
                    sb.append((char) ch);
                }
                return sb.toString();
            }
            ch = bufferedReader.read();
        }
        return null;
    }

    @Override
    public void close() throws IOException {
            bufferedReader.close();
    }
}
