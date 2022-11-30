import java.io.*;
import java.util.Arrays;

public class Dec13Part1 {
    public static void main(String[] args) {
        FileReader fr;
        StringBuilder sb = new StringBuilder();
        try {
            fr = new FileReader("C:\\Users\\cjgut\\Desktop\\Advent of code\\inputdec13.txt");
            int i = fr.read();
            while (i != -1) {
                sb.append((char) i);
                i = fr.read();
            }
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        String text = sb.toString();



        String[] inputs = text.split("-");


        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = inputs[i].trim(); // trim all String so no whitespace is caught
        }

        String[] points = inputs[0].split("\n");
        
        Paper paper = new Paper();

        for (String input : points) {
            paper.addCoordinate(input.split(","));
        }

        System.out.println(paper.numOfPoints());

        for (String input : inputs[1].split("\n")) {
            int axis = Integer.parseInt(input.split("=")[1].trim());
            if (input.contains("x"))
                paper.foldXAxis(axis);
            else if (input.contains("y"))
                paper.foldYAxis(axis);
            break;
        }

        System.out.println(paper.numOfPoints());

    }
}
