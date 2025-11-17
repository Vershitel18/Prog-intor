//package Prog_intro.Icpc;
import java.util.Scanner;
import java.lang.Math;

public class Piramid {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int x_r = Integer.MIN_VALUE;
        int y_r = Integer.MIN_VALUE;
        int x_l = Integer.MAX_VALUE;
        int y_l = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int x_i = scanner.nextInt();
            int y_i = scanner.nextInt();
            int h_i = scanner.nextInt();
            x_r = Math.max(x_r, x_i + h_i);
            x_l = Math.min(x_l, x_i - h_i);
            y_r = Math.max(y_r, y_i + h_i);
            y_l = Math.min(y_l, y_i - h_i);
        }
        int h = ((Math.max(x_r - x_l, y_r - y_l)+1)/2);
        int x = (x_l + x_r)/2;
        int y = (y_l + y_r)/2;
        System.out.println(x + " " + y + " " + h);
    }
}