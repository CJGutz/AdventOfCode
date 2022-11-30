import java.util.*;
import java.io.*;

public class Dec12Part2 {

    private static HashMap<String, ArrayList<String>> intersections;
    public static ArrayList<String> paths;
    public static void main(String[] args) {
        FileReader fr;
        StringBuilder sb = new StringBuilder();
        try {
            fr = new FileReader("C:\\Users\\cjgut\\Desktop\\Advent of code\\inputdec12.txt");
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

        for (String neighbour : intersections.get("start")) {
            dfs(neighbour, "start");
        }

        System.out.println(paths.size());
        

    }

    public static void dfs(String cave, String path) {
        boolean check = contains(path.split(","), cave.toLowerCase());
        if (!cave.equals("end") && !check) {
            for (String neighbour : intersections.get(cave)) {
                dfs(neighbour, path + "," + cave);
            }
        }
        else if (cave.equals("end")) {
            path = path + ",end";
            paths.add(path);            
        }
    }



    public static boolean contains(String[] list, String query) {
        if (query.equals("start") || query.equals("end"))
            return true;

        boolean hasDouble = false;
        int c = 0;
        for (int i = 0; i < list.length; i++)
            for (int j = 0; j < list.length; j++)
                if (list[i].toLowerCase().equals(list[j]) && i!=j) {
                    c++;
                    if (c > 1) {
                        hasDouble = true;
                    }
                }

        int count = 0;
        for (String item : list) {
            if (item.equals(query)) {
                count++;
                if (count > 1 || hasDouble) {
                    return true;
                }
            }
        }
        return false;
    }
}