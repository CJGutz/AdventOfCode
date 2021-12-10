import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;
import java.awt.event.*;

class Dec9Part2Animation implements ActionListener {

    private static JFrame frame = new JFrame();
    private static JPanel panel = new JPanel();
    private static JLabel txt = new JLabel();
    private static String[] inputs;
    private static ArrayList<int[]> visited;

    public static void main(String[] args) {

        makeFrame();

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

        inputs = text.split("\n");

        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = inputs[i].trim(); // trim all String so no whitespace is caught
        }


        ArrayList<int[]> stack = new ArrayList<>(); // A queue of which cells is to be visited first
        visited = new ArrayList<>(); // List of cells that are visited
        ArrayList<Integer> basins = new ArrayList<>(); // List of number of cells in each basin

        StringBuilder graphical = new StringBuilder(); // String for graphical basins

        for (int i = 0; i < inputs.length; i++) { // Loops through all lines
            for (int j = 0; j < inputs[i].length(); j++) { // Loops through all characters

                int sumBasin = 0; // Number of cells in one basin

                String color = "\033[0m"; // Reset color
                
                int[] n = {i,j}; 
                if (inputs[i].charAt(j) != '9') { // Dont visit any nines
                    color = "\033[48;2;0;" + (10 + 20 * Integer.parseInt(String.valueOf(inputs[i].charAt(j)))) + ";50m"; // change to color dynamic to number
                    stack.add(n);
                }

                graphical.append(color + "  "); // Print with color

                while (!stack.isEmpty()) {
                    sumBasin += 1; // Adds up as long as there are availble cells that are not nines, not visited or already in the stack

                    int[] next = stack.remove(0); // Visit the first cell in the stack
                    if (!inList(next[0], next[1], visited))
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
            graphical.append("\033[0m\n");
        }
        
        Collections.sort(basins, Collections.reverseOrder());
        System.out.println(basins.get(0) * basins.get(1) * basins.get(2)); // Solution given there are more than three basins
        System.out.println(graphical.toString()); // Print out graphical basins

        Dec9Part2Animation a = new Dec9Part2Animation();
        a.animate(visited, inputs);
    }

    public static boolean inList(int nextY, int nextX, ArrayList<int[]> list) {
        for (int[] c : list) {
            if (nextY == c[0] && nextX == c[1]) {
                return true;
            }
        }
        return false;
    }

    public static void makeFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        txt.setHorizontalAlignment(0);
        panel.add(txt);
        frame.add(panel);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    public void animate(ArrayList<int[]> visited, String[] inputs) {
        String[][] cells = new String[inputs.length][inputs[0].length()];
        for (int[] vC : visited) {
            cells[vC[0]][vC[1]] = "<b style=\"background-color:rgb(255, " + (10 + 20 * Integer.parseInt(String.valueOf(inputs[vC[0]].charAt(vC[1])))) + ", 50)\">" + "_ " + " </b>";

            StringBuilder sb = new StringBuilder();
            sb.append("<html>");
            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells[i].length; j++) {
                    if (cells[i][j] == null) {
                        sb.append("_ ");
                    } else sb.append(cells[i][j]);
                }
                sb.append("<br/>");
            }
            sb.append("</html>");

            txt.setText(sb.toString());
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        
    }

}