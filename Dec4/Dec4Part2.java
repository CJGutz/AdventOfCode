import java.io.*;
import java.util.ArrayList;

public class Dec4Part2 {
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

        int numberOfWinners = 0;
        for (int i = 0; i < targets.length; i++) {
            int target = Integer.parseInt(targets[i].trim());
            for (Matrix matrix : matrices) {
                matrix.scratchNumber(target);
                if (!matrix.getHasWon() && matrix.winningRow() != null) numberOfWinners++;
                if (numberOfWinners == matrices.size()) {
                    System.out.println((matrix.getUnmarkedNumbers(target)));
                    break;
                }
            }
            if (matrices.size() == numberOfWinners) break;
        }

    }
}
