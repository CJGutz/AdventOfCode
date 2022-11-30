import java.io.*;
import java.util.*;

public class Dec1Part2 {
    public static void main(String[] args) {
        FileReader fr;
        StringBuilder sb = new StringBuilder();
        try {
            fr = new FileReader("inputdec1.txt");
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

        ArrayList<Integer> triplets = new ArrayList<>();
        
        try {
            for (int i = 1; i < inputs.length; i++) {
                ArrayList<Integer> triplet = new ArrayList<>();
                for (int j = -1; j < 2; j++) {
                    triplet.add(Integer.parseInt(inputs[i+j]));
                }
                int numberAdd = 0;
                for (int number : triplet) {
                    numberAdd += number;
                }
                triplets.add(numberAdd);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e);
        }

        int numberOfIncreases = -1;
        int previous = 0;
        for (int number : triplets) {          
            if (number > previous) {
                numberOfIncreases++;
            }
            previous = number;
        }
        System.out.println(numberOfIncreases + " / " + triplets.size());
    }
    
}
