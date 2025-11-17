//package Prog_intro.Icpc;
import java.util.Scanner;

public class Last {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] start = new int[n][n];

        for (int i = 0; i < n; i++) {
            String line = scanner.next();
            for (int j = 0; j < n; j++) {
                start[i][j] = line.charAt(j) - '0';
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (start[i][j] == 0) {
                    continue;
                } else {
                    for (int k = j+1; k < n; k++) {
                        start[i][k] = (start[i][k] - start[j][k] + 10)%10;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                System.out.print(start[i][j]);
            }
            System.out.println();
        }
    }
}