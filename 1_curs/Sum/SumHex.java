public class SumHex {
    public static void main(String[] args) {
        int answer = 0;
        for (int i = 0; i < args.length; i++) {
            int left = 0;
            for (int j = 0; j <= args[i].length();j++) {
                if (j == args[i].length() || Character.isWhitespace(args[i].charAt(j))) {
                    String number = args[i].substring(left, j);
                    left = j + 1;
                    if (!number.isEmpty()) {
                        if (number.startsWith("0x") || number.startsWith("0X")){
                            answer += Integer.parseUnsignedInt(number.substring(2), 16);
                        }
                        else {
                            answer += Integer.parseInt(number);
                        }
                    }
                }
            }
        }
        System.out.println(answer);
    }
}