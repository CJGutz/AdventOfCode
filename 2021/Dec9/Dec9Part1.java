import java.io.*;

class Dec9Part1 {
    public static void main(String[] args) {
        FileReader fr;
        StringBuilder sb = new StringBuilder();
        try {
            fr = new FileReader("C:\\Users\\cjgut\\Desktop\\Advent of code\\inputdec9.txt");
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

        int sum = 0;

        for (int i = 0; i < inputs.length; i++) {
            for (int j = 0; j < inputs[i].length(); j++) {            

                if (isBigger(inputs, i, j, -1, 0) && isBigger(inputs, i, j, 1, 0) && isBigger(inputs, i, j, 0, -1) && isBigger(inputs, i, j, 0, 1))
                    sum += Integer.parseInt(String.valueOf(inputs[i].charAt(j))) + 1;

            }
        }

        System.out.println(sum);
    }

    public static boolean isBigger(String[] inputs, int oy, int ox, int changey, int changex) {
        if (oy + changey < 0 || oy + changey > inputs.length - 1 || ox + changex < 0 || ox + changex > inputs[oy].length() - 1) 
            return true;
        int original = Integer.parseInt(String.valueOf(inputs[oy].charAt(ox)));
        int next = Integer.parseInt(String.valueOf(inputs[oy + changey].charAt(ox + changex)));
        return next > original;
    }
}