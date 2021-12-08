import java.io.*;

public class Dec3Part1 {
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

        StringBuilder gamma = new StringBuilder();
        for (int i = 0; i < inputs[0].length(); i++) {
            int numOfOne = 0;
            for (int j = 0; j < inputs.length; j++) {
                if (inputs[j].charAt(i) == '1') {
                    numOfOne++;
                }
            }
            if (numOfOne > inputs.length/2) gamma.append("1");
            else gamma.append("0");
        }

        System.out.println(gamma.toString());

        int gammaValue = 0;
        int epsilonValue = 0;
        for (int i = 0; i < gamma.length(); i++) {
            if (gamma.charAt(i) == '1') gammaValue += Math.pow(2, gamma.length() - i - 1);
            else epsilonValue += Math.pow(2, gamma.length() - i - 1);
        }
        System.out.println(gammaValue + ", " + epsilonValue);
        System.out.println(gammaValue * epsilonValue);
    }
}
