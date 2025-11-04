
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.max;

public class ReverseMaxC {
    public static void main(String[] args) {
        Scanner scannerLines = new Scanner(System.in);
        int[][] array = new int[1][];
        int[] values = new int[1];
        int lineCount = 0; // счётчик количества найденных строк для своевременного расширения массива
        while (scannerLines.hasNextLine()) { // пока есть новая строка
            String nextLine = scannerLines.nextLine(); // создали сканер на строку
            if (array.length == lineCount) { // если количество строк больше длины массива, массив нужно увеличить
                array = Arrays.copyOf(array, array.length*2); // сделали массив в два раза больше
            }
            Scanner scannerNumbers = new Scanner(nextLine); // сканнер для самой строки
            int elementCount = 0; // количество элементов в строке для расширения подмассива
            array[lineCount] = new int[1]; // инициализируем подмассив т.к там null
            while (scannerNumbers.hasNextInt()){
                if (elementCount == array[lineCount].length) {
                    array[lineCount] = Arrays.copyOf(array[lineCount], array[lineCount].length*2);
                }
                array[lineCount][elementCount] = scannerNumbers.nextInt();
                elementCount++;
            }
            if (lineCount == values.length) {
                values = Arrays.copyOf(values, values.length*2);
            }
            values[lineCount] = elementCount; //
            lineCount++;

        }
        for(int i = lineCount-2; i >= 0; i--) { // идём по большому массиву снизу вверх
            for (int j = 0; j <= values[i]-1; j++) { // по строке идём обычно
                int time = 1;
                while (i+time <= lineCount-1) { // идём пока есть строка
                    if (values[i+time] > j) { // если индекс есть в нашей строке
                        array[i][j] = max(array[i][j], array[i+time][j]); // заменяем текущий элемент на максимум из него и элемента из предыдущей строки
                        break;
                    }
                    time++;
                }
            }
        }

        for(int i = 0; i < lineCount; i++) { // просто вывод
            for (int j = 0; j < values[i]; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

}