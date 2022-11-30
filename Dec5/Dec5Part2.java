import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Dec5Part2 {
    public static void main(String[] args) {
        FileReader fr;
        StringBuilder sb = new StringBuilder();
        try {
            fr = new FileReader("inputdec5.txt");
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

        String[] inputs = text.split("\n");
        ArrayList<String[]> validInputsX = new ArrayList<>();
        ArrayList<String[]> validInputsY = new ArrayList<>();
        ArrayList<String[]> validInputsDiagonal = new ArrayList<>();

        for (String input : inputs) {
            String[] coordinates = input.split("[,\\s]");
            if (coordinates[0].equals(coordinates[3])) // only straight lines in X-direction
                validInputsX.add(coordinates);
            else if (coordinates[1].equals(coordinates[4])) // only straight lines in Y-direction
                validInputsY.add(coordinates);
            else 
                validInputsDiagonal.add(coordinates);
        }

        HashMap<String, Integer> map = new HashMap<>();

        for (String[] coordinates : validInputsX) {
            int start = Integer.parseInt(coordinates[1]);
            int end = Integer.parseInt(coordinates[4]);
            if (end < start) {
                int temp = start;
                start = end;
                end = temp;
            }
            for (int i = start; i <= end; i++) {
                String key = coordinates[0] + "," + i;
                if (map.get(key) == null) {
                    map.put(key, 1);
                } else
                    map.put(key, 2);
            }
        }

        for (String[] coordinates : validInputsY) {
            int start = Integer.parseInt(coordinates[0]);
            int end = Integer.parseInt(coordinates[3]);
            if (end < start) {
                int temp = start;
                start = end;
                end = temp;
            }
            for (int i = start; i <= end; i++) {
                String key = i + "," + coordinates[1];
                if (map.get(key) == null) {
                    map.put(key, 1);
                } else
                    map.put(key, 2);
            }
        }

        for (String[] coordinates : validInputsDiagonal) {
            int startX = Integer.parseInt(coordinates[0]);
            int endX = Integer.parseInt(coordinates[3]);
            int startY = Integer.parseInt(coordinates[1]);
            int endY = Integer.parseInt(coordinates[4]);
            int negativeX = 1;
            int negativeY = 1;
            if (endX < startX) negativeX = -1;
            if (endY < startY) negativeY = -1;
            for (int i = 0; i <= endY * negativeY - startY * negativeY; i++) {
                String key = (startX + i * negativeX) + "," + (startY + i * negativeY);
                if (map.get(key) == null) {
                    map.put(key, 1);
                } else
                    map.put(key, 2);

            }
        }

        int sum = 0;
        for (int i : map.values()) {
            if (i == 2)
                sum++;
        }
        System.out.println(sum);



    }
}
