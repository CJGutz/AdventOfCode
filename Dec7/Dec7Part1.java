import java.io.*;
import java.util.*;

public class Dec7Part1 {
    public static void main(String[] args) {
        FileReader fr;
        StringBuilder sb = new StringBuilder();
        try {
            fr = new FileReader("C:\\Users\\cjgut\\Desktop\\Advent of code\\inputdec7.txt");
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
        
        ArrayList<Integer> positions = new ArrayList<>();
        for (int i = 0; i < inputs.length; i++) {
            positions.add(Integer.parseInt(inputs[i].trim()));
        }

        Collections.sort(positions);

        int median = positions.get(positions.size()/2);
        
        int fuelM = 0;
        for (int position : positions) {
            fuelM += Math.abs(position - median);
        }

        System.err.println(fuelM);
    }
}
