import java.io.*;

public class Dec2Part2 {
    public static void main(String[] args) {
        FileReader fr;
        StringBuilder sb = new StringBuilder();
        try {
            fr = new FileReader("C:\\Users\\cjgut\\Desktop\\Advent of code\\inputdec2.txt");
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
        int horizontal = 0;
        int depth = 0;
        int aim = 0;

        for (String input : inputs) {
            int movement = Integer.parseInt(String.valueOf(input.charAt(input.length() - 1)));
            if (input.contains("forward")) {
                horizontal += movement;
                depth += movement * aim;
            } else if (input.contains("up")) {
                aim -= movement;
            } else if (input.contains("down")) {
                aim += movement;
            }
        }

        System.out.println(depth + " * " + horizontal + " = " + depth * horizontal);
    }
}
