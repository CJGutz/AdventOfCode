import java.io.*;
import java.util.ArrayList;

public class Dec3Part2 {
    public static void main(String[] args) {
        FileReader fr;
        StringBuilder sb = new StringBuilder();
        try {
            fr = new FileReader("C:\\Users\\cjgut\\Desktop\\Advent of code\\inputdec3.txt");
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
        ArrayList<String> list = new ArrayList<>();
        for (String input : inputs) {
            list.add(input);
        }
        ArrayList<String> oxygen = find(list, 0, true);
        ArrayList<String> co2 = find(list, 0, false);

        String oxygenResult = oxygen.get(0);
        String co2Result = co2.get(0);

        int oxygenValue = 0;
        int co2Value = 0;
        for (int i = 0; i < oxygenResult.length(); i++) {
            if (oxygenResult.charAt(i) == '1') oxygenValue += Math.pow(2, oxygenResult.length() - i - 1);
        }
        for (int i = 0; i < co2Result.length(); i++) {
            if (co2Result.charAt(i) == '1') co2Value += Math.pow(2, co2Result.length() - i - 1);
        }
        System.out.println(oxygenValue + ", " + co2Value);
        System.out.println(oxygenValue * co2Value);

    }

    public static ArrayList<String> find(ArrayList<String> list, int position, boolean oxygen) {
        if (list.size() == 1) {
            System.out.println(list);
            return new ArrayList<>(list);
        }

        ArrayList<String> ones = new ArrayList<>();
        ArrayList<String> zeroes = new ArrayList<>();

        for (String bit : list) {
            if (bit.charAt(position) == '1')  {
                ones.add(bit);
            }
            else zeroes.add(bit);
        }

        int newPosition = position + 1;
        ArrayList<String> newList = new ArrayList<>();

        if (oxygen) {
            if (ones.size() >= zeroes.size())
                newList = find(ones, newPosition, oxygen);
            else newList = find(zeroes, newPosition, oxygen);
        } else {
            if (ones.size() >= zeroes.size())
                newList = find(zeroes, newPosition, oxygen);
            else newList = find(ones, newPosition, oxygen);
        }
 
        return newList;
    }
}
