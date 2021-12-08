import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Display {
    
    private final String[] input;
    private final String[] output;
    private HashMap<Integer, String> numbers;

    public Display(String[] input, String[] output) {
        this.input = input;
        this.output = output;
        numbers = findConfiguration();
    }

    public String[] getInput() {
        return input;
    }

    public String[] getOutput() {
        return output;
    }

    public HashMap<Integer, String> getConfiguration() {
        return numbers;
    }

    public int easyNumbers() {
        int digits = 0;
        for (String digit : output) {
            int length = digit.length();
            if (length <= 4 || length == 7) {
                digits++;
            }
        }
        return digits;
    }

    public HashMap<Integer, String> findConfiguration() {
        HashMap<Integer, String> conf = new HashMap<>();

        for (String d : input) {
            int len = d.length();
            String digit = sortString(d);
            if (len == 2)
                conf.put(1, digit);
            else if (len == 3)
                conf.put(7, digit);
            else if (len == 4)
                conf.put(4, digit);
            else if (len == 7)
                conf.put(8, digit);
        }

        for (String d : input) {
            int len = d.length();
            String digit = sortString(d);
            if (len == 5) { // 2/3/5
                if (equalChars(digit, conf.get(1)) == 1 && equalChars(digit, conf.get(4)) == 2 && equalChars(digit, conf.get(7)) == 2)  // 2
                    conf.put(2, digit);
                if (equalChars(digit, conf.get(1)) == 2 && equalChars(digit, conf.get(4)) == 3 && equalChars(digit, conf.get(7)) == 3)  // 3
                    conf.put(3, digit);
                if (equalChars(digit, conf.get(1)) == 1 && equalChars(digit, conf.get(4)) == 3 && equalChars(digit, conf.get(7)) == 2)  // 5
                    conf.put(5, digit);
            }
            if (len == 6) { // 0/6/9
                if (equalChars(digit, conf.get(1)) == 2 && equalChars(digit, conf.get(4)) == 3 && equalChars(digit, conf.get(7)) == 3)  // 0
                conf.put(0, digit);
            if (equalChars(digit, conf.get(1)) == 1 && equalChars(digit, conf.get(4)) == 3 && equalChars(digit, conf.get(7)) == 2)  // 6
                conf.put(6, digit);
            if (equalChars(digit, conf.get(1)) == 2 && equalChars(digit, conf.get(4)) == 4 && equalChars(digit, conf.get(7)) == 3)  // 9
                conf.put(9, digit);
            }
        }


        return conf;
    }

    public static String sortString(String inputString) {
        char tempArray[] = inputString.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
    }



    public int equalChars(String s1, String s2) {
        if (s1.equals(s2)) 
            return s1.length();

        int equalChars = 0;
        for (String c1 : s1.split("")) {
            for (String c2 : s2.split("")) {
                if (c1.equals(c2))
                    equalChars++;
            }
        }
        return equalChars;
    }


    public int outputNumber() {
        StringBuilder sb = new StringBuilder();
        for (String digit : output) {
            for (int i : numbers.keySet()) {
                if (sortString(digit).equals(sortString(numbers.get(i)))) {
                    sb.append(i);
                }
            }
        }
        return Integer.parseInt(sb.toString());
    }
}

/*
 0 0 0 0
1       2
1       2
 3 3 3 3
4       5
4       5
 6 6 6 6
*/