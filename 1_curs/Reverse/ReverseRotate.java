package Reverse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Math.max;

public class ReverseRotate {
    public static void main(String[] args) {
        myNewScanner scannerLines = new myNewScanner(System.in, c -> (Character.isDigit(c) || c=='-'));
        int[][] array = new int[1][];
        int[] values = new int[1];
        int lineCount = 0;
        int maxValues = 0;
        try {
            while (scannerLines.hasNext()) {
                if (array.length == lineCount) {
                    array = Arrays.copyOf(array, array.length * 2);
                }
                int elementCount = 0;
                array[lineCount] = new int[1];
                String numbers;
                while ((numbers = scannerLines.nextInLine()) != null) {
                    if (elementCount == array[lineCount].length) {
                        array[lineCount] = Arrays.copyOf(array[lineCount], array[lineCount].length * 2);
                    }
                    array[lineCount][elementCount] = Integer.parseInt(numbers);
                    elementCount++;
                }
                maxValues = max(maxValues,elementCount);
                if (lineCount == values.length) {
                    values = Arrays.copyOf(values, values.length * 2);
                }
                values[lineCount] = elementCount;
                lineCount++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        ArrayList<StringBuilder> List = new ArrayList<>(maxValues);
        for (int i = 0; i < maxValues; i++) {
            List.add(new StringBuilder());
        }

        for(int i = lineCount-1; i >= 0; i--) {
            for (int j = values[i]-1; j >= 0 ; j--) {
                List.get(j).append(array[i][j]);
                List.get(j).append(" ");
            }
        }
        for (StringBuilder i:List) {
            System.out.println(i);
        }
    }

}