//package Prog_intro.Icpc;
import java.util.Scanner;
import java.util.*;

public class Managin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] answers = new int[n];
        for (int r = 0; r < n; r++) {
            int a = scanner.nextInt();
            int[] list = new int[a];
            for (int h = 0; h < a; h++) {
                list[h] = scanner.nextInt();
            }
            Map<Integer, Integer> count = new HashMap<>();
            int ans = 0;

            for (int j = a-1; j > 0; j--) {
                for (int i = 0; i < j; i++) {
                    int k = 2 * list[j] - list[i];
                    ans += count.getOrDefault(k, 0);
                }
                count.put(list[j], count.getOrDefault(list[j], 0) + 1);
            }
            answers[r] = ans;
        }
        for (int ans: answers) {
            System.out.println(ans);
        }
    }
}