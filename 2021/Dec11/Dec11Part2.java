
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


class Dec11Part2 {

    private static String[] inputs;
    private static int[][] octies;
    public static void main(String[] args) {
        FileReader fr;
        StringBuilder sb = new StringBuilder();
        try {
            fr = new FileReader("C:\\Users\\cjgut\\Desktop\\Advent of code\\inputdec11.txt");
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

        inputs = text.split("\n");

        
        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = inputs[i].trim(); // trim all String so no whitespace is caught
        }

        octies = new int[inputs.length][inputs[0].length()];

        for (int i = 0; i < inputs.length; i++) {
            for (int j = 0; j < inputs[i].length(); j++) {
                octies[i][j] = Integer.parseInt(inputs[i].substring(j,j+1));
            }
        }

        int DAYS = 500;
        
        for (int day = 0; day < DAYS; day++) {


            for (int i = 0; i < octies.length; i++) // Add 1 to every octopus
                for (int j = 0; j < octies[i].length; j++)
                    octies[i][j]++;


            int[] nine = nextNine();
            while (nine != null) { // If there exist a nine, change all neighbours if they are not nine
            for (int i = -1; i <= 1; i++)
                    for (int j = -1; j <= 1; j++)
                        if (i+j != 0 || i*j == -1) {
                            try {
                                if (octies[nine[0] + i][nine[1] + j] <= 9 && octies[nine[0] + i][nine[1] + j] != 0)
                                    octies[nine[0] + i][nine[1] + j]++;   
                            } catch (IndexOutOfBoundsException e) {}
                        }
                octies[nine[0]][nine[1]] = 0;
                nine = nextNine();
            }

            int zeroes = 0;
            for (int i = 0; i < octies.length; i++) // Change all flashed octopie to 0
                for (int j = 0; j < octies[i].length; j++)
                    if (octies[i][j] > 9 || octies[i][j] == 0) {
                        octies[i][j] = 0;
                        zeroes++;
                    }

            if (zeroes == octies.length * octies[0].length) {
                System.out.println(day + 1);
                break;
            }


        }

    }

    public static int nineNeighbours(int y, int x) {
        int nines = 0;
        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++)
                if (i != 0 && j != 0)
                    try {
                        if (octies[y+i][x+j] >= 9)
                            nines++;
                    } catch (IndexOutOfBoundsException e) {}
        return nines;
    }

    public static int[] nextNine() {
        for (int i = 0; i < octies.length; i++)
            for (int j = 0; j < octies[i].length; j++)
                if (octies[i][j] > 9) {
                    int[] pos = {i, j};
                    return pos;
                }
        return null;
    }
}