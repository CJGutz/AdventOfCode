import java.util.*;
import java.io.*;

public class Dec12Part1 {

    private static HashMap<String, ArrayList<String>> intersections;
    public static ArrayList<ArrayList<String>> paths;
    public static ArrayList<String> path;
    public static ArrayList<String> marked;
    public static ArrayList<String> stack;
    public static void main(String[] args) {
        FileReader fr;
        StringBuilder sb = new StringBuilder();
        try {
            fr = new FileReader("C:\\Users\\cjgut\\Desktop\\Advent of code\\small.txt");
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
        
        intersections = new HashMap<>();

        for (int i = 0; i < inputs.length; i++)
            inputs[i] = inputs[i].trim(); // Trim all lines

        for (int i = 0; i < inputs.length; i++) { // make hashmap
            for (int j = 0; j <= 1; j++) {
                ArrayList<String> possibilities = new ArrayList<>();
                String[] intersection = inputs[i].split("-");
                if (intersections.containsKey(intersection[j])) {
                    intersections.get(intersection[j]).add(intersection[1-j]);
                } else {
                    if (!possibilities.contains(intersection[j]))
                        possibilities.add(intersection[1-j]);
                    intersections.put(intersection[j], possibilities);
                }
            }

        }
        

        paths = new ArrayList<>();
        path = new ArrayList<>();
        marked = new ArrayList<>();
        stack = new ArrayList<>();

        dfs("start");
        System.out.println(paths);
        

    }

    public static void dfs(String cave) {
        stack.add(cave);
        while (stack.size() > 0) {
            cave = stack.remove(stack.size() - 1);
            if (!marked.contains(cave) && !cave.toLowerCase().equals(cave) || cave.equals("start")) {
                path.add(cave);
                marked.add(cave);
                for (String neighbour : intersections.get(cave)) {
                    if (!marked.contains(neighbour) && !neighbour.toLowerCase().equals(neighbour)) {
                        stack.add(neighbour);
                    }
                }
            }
        }
            
    }



    public static ArrayList<String> deepcopy(ArrayList<String> list) {
        ArrayList<String> newList = new ArrayList<>();
        for (String item : list) {
            newList.add(item);
        }
        return newList;
    }
}