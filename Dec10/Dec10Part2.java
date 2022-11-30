import java.util.*;
import java.io.*;

public class Dec10Part2 {
    public static void main(String[] args) {
        FileReader fr;
        StringBuilder sb = new StringBuilder();
        try {
            fr = new FileReader("C:\\Users\\cjgut\\Desktop\\Advent of code\\inputdec10.txt");
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

        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = inputs[i].trim(); // trim all String so no whitespace is caught
        }

        ArrayList<Character> expected;
        HashMap<Character, Character> connection = new HashMap<>();
        connection.put('(', ')'); connection.put('[', ']');
        connection.put('{', '}'); connection.put('<', '>');
        HashMap<Character, Integer> points = new HashMap<>();
        points.put(')', 1); points.put(']', 2);
        points.put('}', 3); points.put('>', 4);

        ArrayList<Long> scores = new ArrayList<>();

        for (int i = 0; i < inputs.length; i++) {
            expected  = new ArrayList<>();
            for (int j = 0; j < inputs[i].length(); j++) {
                char symbol = inputs[i].charAt(j);
                if (inList(symbol, new ArrayList<Character>(connection.keySet())))
                    expected.add(0, connection.get(symbol));
                else
                    if (expected.remove(0) != symbol) {
                        expected.clear(); 
                        break;
                    }
            }

            long lineSum = 0;
            for (char symbol : expected) {
                lineSum *= 5;
                lineSum += points.get(symbol);
            }

            if (lineSum > 0)
                scores.add(lineSum);
        }

        Collections.sort(scores);
        System.out.println(scores.get((scores.size() - 1)/2));
    }

    public static boolean inList(Character symbol, ArrayList<Character> list) {
        for (char item : list)
            if (symbol == item)
                return true;
        return false;
    }
}
