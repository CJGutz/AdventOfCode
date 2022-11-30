import java.io.*;
import java.util.HashMap;

public class Dec14Part1 {
    public static void main(String[] args) {
        FileReader fr;
        StringBuilder sb = new StringBuilder();
        try {
            fr = new FileReader("C:\\Users\\cjgut\\Desktop\\Advent of code\\inputdec14.txt");
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
        String formula = inputs[0];

        HashMap<String, Character> reactions = new HashMap<>();
        for (int i = 2; i < inputs.length; i++) {
            String[] reaction = inputs[i].split(" -> ");
            reactions.put(reaction[0], reaction[1].charAt(0));
        }

        HashMap<Character, Integer> characters = new HashMap<>();

        for (int i = 0; i < formula.length(); i++) {
            char c = formula.charAt(i);
            if (characters.containsKey(c)) characters.put(c, characters.get(c) + 1);
            else characters.put(c, 1);
        }

        for (int step = 0; step < 10; step++) {
            StringBuilder builder = new  StringBuilder();
            for (int i = 0; i < formula.length(); i++) {
                builder.append(formula.charAt(i));
                String pair = "";
                try {
                    pair = (String) formula.substring(i, i+2);
                } catch (StringIndexOutOfBoundsException e) {}
                if (reactions.containsKey(pair)) {
                    char c = reactions.get(pair);
                    if (characters.containsKey(c)) characters.put(c, characters.get(c) + 1);
                    else characters.put(c, 1);
                    builder.append(c);
                }
            }
            formula = builder.toString();
        }

        int max = 0;
        for (int i : characters.values()) {
            if (i > max) max = i;
        }
        int min = 1000000000;
        for (int i : characters.values()) {
            if (i < min) min = i;
        }
        
        System.out.println(characters);
        System.out.println(max - min);
    }
}
