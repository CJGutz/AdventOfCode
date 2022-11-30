import java.io.*;
import java.util.ArrayList;

public class Dec4Part1 {
    public static void main(String[] args) {
        FileReader fr;
        StringBuilder sb = new StringBuilder();
        try {
            fr = new FileReader("C:\\Users\\cjgut\\Desktop\\Advent of code\\inputdec4.txt");
            int i = fr.read();
            while (i != -1) {
                sb.append((char) i);
                i = fr.read();
            }
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        String text = sb.toString();

        String[] inputs = text.split("\n\n");

        ArrayList<Matrix> matrices = new ArrayList<>();

        for (int i = 1; i < inputs.length; i++) {
            String[] array = inputs[i].trim().split("\\s+");
            int[][] matrix = new int[5][5];
            for (int j = 0; j < array.length; j += 5) {
                int[] row = new int[5];
                for (int k = 0; k < 5; k++) {
                    row[k] = Integer.parseInt(array[j + k]);
                }
                matrix[j/5] = row;
            }
            matrices.add(new Matrix(matrix));
        }

        String[] targets = inputs[0].split(",");

        for (int i = 0; i < targets.length; i++) {
            boolean won = false;
            int target = Integer.parseInt(targets[i]);
            for (Matrix matrix : matrices) {
                matrix.scratchNumber(target);
                if (matrix.winningRow() != null) {
                    won = true;
                    int answer = matrix.getUnmarkedNumbers(target);
                    System.out.println(answer);
                    break;
                }
            }
            if (won) break;
        }

    }
}