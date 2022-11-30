import java.io.*;

public class Dec1Part1 {
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

        int numberOfIncreases = -1;
        int previous = 0;
        for (String input : inputs) {
            int number = 0;
            try {
                number = Integer.parseInt(input);
            } catch (NumberFormatException e) {
            }
            if (number > previous) {
                numberOfIncreases++;
            }
            previous = number;
        }
        System.out.println(numberOfIncreases + " / " + inputs.length);
    }

    
}