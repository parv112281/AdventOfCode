import java.util.*;
import java.io.*;

class PuzzleOne {
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
            System.out.println("File not found");
        }

        int[] maxRow = createMaxArray(grid.size());
        int[] maxCol = createMaxArray(grid.get(0).size());
        boolean[][] visible = new boolean[grid.size()][grid.get(0).size()];
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                int value = grid.get(i).get(j);
                if (value > maxRow[i]) {
                    maxRow[i] = value;
                    visible[i][j] = true;
                }
                if (value > maxCol[j]) {
                    maxCol[j] = value;
                    visible[i][j] = true;
                }
            }
        }
        maxRow = createMaxArray(grid.size());
        maxCol = createMaxArray(grid.get(0).size());
        for (int i = grid.size()-1; i >= 0; i--) {
            for (int j = grid.get(i).size()-1; j >= 0; j--) {
                int value = grid.get(i).get(j);
                if (value > maxRow[i]) {
                    maxRow[i] = value;
                    visible[i][j] = true;
                }
                if (value > maxCol[j]) {
                    maxCol[j] = value;
                    visible[i][j] = true;
                }
            }
        }

        int totalVisible = 0;
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                if (visible[i][j]) {
                    totalVisible++;
                } 
                System.out.print(visible[i][j] ? "X" : " ");
            }
            System.out.println();
        }
        System.out.println(totalVisible);
    }

    static int[] createMaxArray(int size) {
        int[] max = new int[size];
        for (int i = 0; i < size; i++) {
            max[i] = Integer.MIN_VALUE;
        }
        return max;
    }

    static List<Integer> buildRow(String data) {
        List<Integer> row = new ArrayList<>();
        for (int i = 0; i < data.length(); i++) {
            row.add(Integer.parseInt(data.substring(i, i + 1)));
        }
        return row;
    }
}
