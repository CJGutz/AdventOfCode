import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Dec14Part2 {
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
        String formula = inputs[0].trim();

        HashMap<String, Character> reactions = new HashMap<>();
        HashMap<String, Long> pairs = new HashMap<>();
        HashMap<String, Long> pairsNextStep = new HashMap<>();
        HashMap<Character, Long> characters = new HashMap<>();
        

        for (int i = 2; i < inputs.length; i++) {
            String[] reaction = inputs[i].split(" -> ");
            reactions.put(reaction[0], reaction[1].charAt(0));
            pairs.put(reaction[0], 0l);
            pairsNextStep.put(reaction[0], 0l);
            characters.put(reaction[1].charAt(0), 0l);
        }

        for (int i = 0; i < formula.length(); i++) {
            char c = formula.charAt(i);
            characters.put(c, characters.get(c) + 1);

            String pair = "";
            try {pair = formula.substring(i, i+2);}
            catch (StringIndexOutOfBoundsException e) {}
            if (!pair.equals("")) pairs.put(pair, pairs.get(pair) + 1);
        }

        ArrayList<String> list = new ArrayList<>(pairs.keySet());

        for (int step = 0; step < 40; step++) {

            for (String pair : list) {

                char c = reactions.get(pair);
                String key1 = String.valueOf(pair.charAt(0)) + String.valueOf(c);
                String key2 = String.valueOf(c) + String.valueOf(pair.charAt(1));
                long number = pairs.get(pair);
                
                pairs.put(pair, 0l);
                
                if (number != 0) {
                    pairsNextStep.put(key1, pairsNextStep.get(key1) + number);
                    pairsNextStep.put(key2, pairsNextStep.get(key2) + number);
                    characters.put(c, characters.get(c) + number);
                }

            }

            for (String pair : list) {
                pairs.put(pair, pairsNextStep.get(pair));
                pairsNextStep.put(pair, 0l);
            }

        }

        long max = 0l;
        for (long i : characters.values()) {
            if (i > max) max = i;
        }
        long min = 100000000000000000l;
        for (long i : characters.values()) {
            if (i < min) min = i;
        }
        
        System.out.println(characters);
        System.out.println(max - min);

    }

}
