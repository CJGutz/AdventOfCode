import java.util.*;
import java.io.*;

public class Dec10Part1 {
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
        points.put(')', 3); points.put(']', 57);
        points.put('}', 1197); points.put('>', 25137);

        int sum = 0;

        for (int i = 0; i < inputs.length; i++) {
            expected  = new ArrayList<>();
            for (int j = 0; j < inputs[i].length(); j++) {
                char symbol = inputs[i].charAt(j);
                if (inList(symbol, new ArrayList<Character>(connection.keySet())))
                    expected.add(0, connection.get(symbol));
                else
                    if (expected.remove(0) != symbol) {
                        sum += points.get(symbol); 
                        break;
                    }

            }
        }

        System.out.println(sum);

    }

    public static boolean inList(Character symbol, ArrayList<Character> list) {
        for (char item : list)
            if (symbol == item)
                return true;
        return false;
    }
}
