import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Dec9Part2 {
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

        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = inputs[i].trim(); // trim all String so no whitespace is caught
        }


        ArrayList<int[]> stack = new ArrayList<>(); // A queue of which cells is to be visited first
        ArrayList<int[]> visited = new ArrayList<>(); // List of cells that are visited
        ArrayList<Integer> basins = new ArrayList<>(); // List of number of cells in each basin

        StringBuilder graphical = new StringBuilder(); // String for graphical basins

        for (int i = 0; i < inputs.length; i++) { // Loops through all lines
            for (int j = 0; j < inputs[i].length(); j++) { // Loops through all characters

                int sumBasin = 0; // Number of cells in one basin

                String color = "\033[0m"; // Reset color

                if (inputs[i].charAt(j) != '9') { // Dont visit any nines
                    color = "\033[38;2;255;" + (10 + 20 * Integer.parseInt(String.valueOf(inputs[i].charAt(j)))) + ";50m"; // change to color dynamic to number
                    int[] n = {i,j}; 
                    stack.add(n);
                }

                graphical.append(color + inputs[i].charAt(j)).append(" "); // Print number with color

                while (!stack.isEmpty()) {
                    sumBasin += 1; // Adds up as long as there are availble cells that are not nines, not visited or already in the stack

                    int[] next = stack.remove(0); // Visit the first cell in the stack
                    visited.add(next); // say it is visited


                    for (int y = -1; y <= 1; y++)
                        for (int x = -1; x <= 1; x++)
                            if (x * y == 0 && x != y) { // Use only certain x's and y's
                                int yCell = next[0] + y;
                                int xCell = next[1] + x;
                                if (yCell >= 0 && yCell <= inputs.length - 1 && xCell >= 0 && xCell <= inputs[next[0]].length() - 1) // Check if cell is out of bounds
                                    if (inputs[yCell].charAt(xCell) != '9' && !inList(yCell, xCell, visited) && !inList(yCell, xCell, stack)) { // Check if cell is not a nine, not visited or already in the stack
                                        int[] newNext = {yCell, xCell}; 
                                        stack.add(0, newNext); // Add available cell to stack
                                    }
                            }
                }  
                
                if (sumBasin > 0) { // sumBasin == 0 means the cell was a 9
                    basins.add(sumBasin); // Add number of cells in one basin to a list
                }
            }
            graphical.append("\n");
        }
        
        Collections.sort(basins, Collections.reverseOrder());
        System.out.println(basins.get(0) * basins.get(1) * basins.get(2)); // Solution given there are more than three basins
        System.out.println(graphical.toString()); // Print out graphical basins
    }

    public static boolean inList(int nextY, int nextX, ArrayList<int[]> list) {
        for (int[] c : list) {
            if (nextY == c[0] && nextX == c[1]) {
                return true;
            }
        }
        return false;
    }

}