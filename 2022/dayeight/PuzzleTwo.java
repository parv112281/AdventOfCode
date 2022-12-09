import java.util.*;
import java.io.*;

class PuzzleTwo {
    public static void main(String[] args) {
        String inputFileName = "input.txt";
        List<List<Integer>> grid = new ArrayList<>();
        if (args.length > 0) {
            inputFileName = args[0];
        }
        try {
            File inputFile = new File(inputFileName);
            Scanner inputReader = new Scanner(inputFile);

            while(inputReader.hasNextLine()) {
                String data = inputReader.nextLine();
                grid.add(buildRow(data));
            }
            inputReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + new File(inputFileName).getAbsolutePath());
        }
        List<Integer> maxindices = List.of(-1, -1);
        int max = 0;
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                int value = grid.get(i).get(j);
                int leftVal = 0;
                int left = j-1;
                while (left >= 0) {
                    leftVal++;
                    if (grid.get(i).get(left) >= value) {
                        break;
                    }
                    left--;
                }
                int rightVal = 0;
                int right = j+1;
                while (right < grid.get(i).size()) {
                    rightVal++;
                    if (grid.get(i).get(right) >= value) {
                        break;
                    }
                    right++;
                }
                int upVal = 0;
                int up = i-1;
                while (up >= 0) {
                    upVal++;
                    if (grid.get(up).get(j) >= value) {
                        break;
                    }
                    up--;
                }
                int downVal = 0;
                int down = i+1;
                while (down < grid.size() ) {
                    downVal++;
                    if (grid.get(down).get(j) >= value) {
                        break;
                    }
                    down++;
                }
                int total = leftVal * rightVal * upVal * downVal;
                if (total > max) {
                    max = total;
                    maxindices = List.of(i, j);
                }
            }
            
        }
        System.out.println(maxindices);
        System.out.println(max);
    }

    static List<Integer> buildRow(String data) {
        List<Integer> row = new ArrayList<>();
        for (int i = 0; i < data.length(); i++) {
            row.add(Integer.parseInt(data.substring(i, i + 1)));
        }
        return row;
    }
}
