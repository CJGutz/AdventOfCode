import java.io.*;
import java.util.Arrays;

class Dec8Part2 {
    public static void main(String[] args) {
        FileReader fr;
        StringBuilder sb = new StringBuilder();
        try {
            fr = new FileReader("C:\\Users\\cjgut\\Desktop\\Advent of code\\inputdec8.txt");
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

        Display[] displays = new Display[inputs.length];

        for (int i = 0; i < inputs.length; i++) {
            String[] display = inputs[i].split("\\|");
            String[] patterns = display[0].trim().split(" ");
            String[] output = display[1].trim().split(" ");
            displays[i] = new Display(patterns, output);
        }

        int sum = 0;
        for (Display display : displays) {
            sum += display.outputNumber();
        }

        System.out.println(sum);
    }
}