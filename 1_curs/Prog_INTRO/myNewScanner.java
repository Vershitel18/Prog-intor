import java.util.function.Predicate;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class myNewScanner implements AutoCloseable{
    private final BufferedReader reader;
    private final Predicate<Character> isWordChar;
    private final char[] buffer = new char[8192];
    private int pos = 0;
    private int limit = 0;
    private boolean endOfStream = false;
    private char pushBackChar = 0;
    private int markPos = -1;

    public myNewScanner(InputStream inputStream, Predicate<Character> isWordChar) {
        this.reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        this.isWordChar = isWordChar;
    }

    public myNewScanner(String text, Predicate<Character> isWordChar) {
        this.reader = new BufferedReader(new StringReader(text));
        this.isWordChar = isWordChar;
    }

    private void ensureBuffer() throws IOException {
        if (pos >= limit && !endOfStream) {
            limit = reader.read(buffer, 0, buffer.length);
            pos = 0;
            if (limit == -1) {
                endOfStream = true;
                limit = 0;
            }
        }
    }

    private int read1() throws IOException {
        if (pushBackChar != 0) {
            int ch = pushBackChar;
            pushBackChar = 0;
            return ch;
        }
        ensureBuffer();
        if (pos >= limit) return -1;
        return buffer[pos++];
    }

    private void mark1(int readAheadLimit) throws IOException {
        markPos = pos;
    }

    private void reset1() throws IOException {
        pos = markPos;
    }

    public boolean hasNext() throws IOException {
        if (pushBackChar != 0) {
            return true;
        }
        mark1(1);
        int ch = read1();
        if (ch == -1) {
            return false;
        }
        reset1();
        return true;
    }

    public String nextInLine() throws IOException {
        int ch;
        String lineSeparator = System.lineSeparator();

        if (pushBackChar != 0) {
            ch = pushBackChar;
            pushBackChar = 0;
        } else {
            ch = read1();
        }
        while (ch != -1) {
            if (ch == lineSeparator.charAt(0)) {
                mark1(lineSeparator.length());
                boolean isSeparator = true;
                for (int i = 1; i < lineSeparator.length(); i++) {
                    int nextCh = read1();
                    if (nextCh == -1 || (char)nextCh != lineSeparator.charAt(i)) {
                        isSeparator = false;
                        reset1();
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
                    ch = read1();
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
            ch = read1();
        }
        return null;
    }
    @Override
    public void close() throws IOException {
        reader.close();
    }
}
