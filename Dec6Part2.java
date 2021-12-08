import java.io.*;
import java.util.HashMap;

public class Dec6Part2 {
    public static void main(String[] args) {
        FileReader fr;
        StringBuilder sb = new StringBuilder();
        try {
            fr = new FileReader("C:\\Users\\cjgut\\Desktop\\Advent of code\\inputdec6.txt");
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

        String[] inputs = text.split(",");

        HashMap<Integer, Long> fishes = new HashMap<>();

        fishes.put(0, 0l);
        fishes.put(1, 0l);
        fishes.put(2, 0l);
        fishes.put(3, 0l);
        fishes.put(4, 0l);
        fishes.put(5, 0l);
        fishes.put(6, 0l);
        fishes.put(7, 0l);
        fishes.put(8, 0l);

        for (String input : inputs) {
            int key;
            try {
                key = Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                key = 1;
            }
            fishes.put(key, fishes.get(key) + 1);
        }

        System.out.println(fishes);
        int days = 256;
        for (int i = 0; i < days; i++) {
            long newFishes = fishes.get(0);
            for (int j = 1; j < fishes.size() - 1; j++) {
                fishes.put(j - 1, fishes.get(j));
                fishes.put(j, fishes.get(j + 1));
            }
            fishes.put(8, newFishes);
            fishes.put(6, fishes.get(6) + newFishes);
        }

        long sum = 0;
        for (long value : fishes.values()) {
            sum += value;
        }

        System.out.println(fishes);
        System.out.println(sum);
        
    }
}
