//package Prog_intro.Icpc;
import java.util.Scanner;

public class Hiload {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] tranzaction = new int[n];
        int maxi = 0;
        int A = 0;

        for (int i = 0; i < n; i++) {
            tranzaction[i] = scanner.nextInt();
            maxi = Math.max(maxi, tranzaction[i]);
            A += tranzaction[i];
        }

        int[] pref_F = new int[A + 1];
        int pos = 1;
        int[] prefixSum = new int[n + 1];
        prefixSum[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < tranzaction[i]; j++) {
                pref_F[pos] = i + 1;
                pos++;
            }
            prefixSum[i + 1] = prefixSum[i] + tranzaction[i];
        }

        int q = scanner.nextInt();
        int[] answers_remember = new int[A+1];
        int[] answers = new int[q];
        for (int k = 0; k < A+1; k++) {
            answers_remember[k] = -1;
        }
        int batches;
        int tranz;
        for (int i = 0; i < q; i++) {
            int t = scanner.nextInt();
            if (answers_remember[t] != -1) {
                answers[i] = answers_remember[t];
                continue;
            }
            if (t < maxi) {
                answers[i] = -2;
                continue;
            } else {
                batches = 0;
                tranz = 1;

                while (tranz <= n) {
                    batches++;
                    pos = prefixSum[tranz - 1];
                    int lastTranz = pref_F[Math.min(A, pos + t)];
                    while (lastTranz >= tranz && prefixSum[lastTranz] - pos > t) {
                        lastTranz--;
                    }
                    tranz = lastTranz + 1;
                }
                answers[i] = batches;
                answers_remember[t] = batches;
            }
        }
        for (int i: answers) {
            if (i == -2) {
                System.out.println("Impossible");
            } else {
                System.out.println(i);
            }
        }
    }
}