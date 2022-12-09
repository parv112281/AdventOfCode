import java.util.*;
import java.io.*;

class PuzzleOne {
    public static void main(String[] args) {
        Set<String> visitedPositions = new HashSet<>();
        String inputFileName = "input.txt";
        if (args.length > 0) {
            inputFileName = args[0];
        }
        try {
            File inputFile = new File(inputFileName);
            Scanner inputReader = new Scanner(inputFile);
            int[] headPos = {0, 0};
            int[] tailPos = {0, 0};
            int row = 0;
            int col = 1;
            visitedPositions.add(Arrays.toString(tailPos));
            while(inputReader.hasNextLine()) {
                String data = inputReader.nextLine();
                String[] parts = data.split(" ");
                String direction = parts[0];
                int distance = Integer.parseInt(parts[1]);
                for (int i = 0; i < distance; i++) {
                    switch (direction) {
                        case "U":
                            headPos[col] -= 1;
                            break;
                        case "D":
                            headPos[col] += 1;
                            break;
                        case "L":
                            headPos[row] -= 1;
                            break;
                        case "R":
                            headPos[row] += 1;
                            break;
                    }
                    if (Math.abs(headPos[row]-tailPos[row]) > 1) {
                        tailPos[col] = headPos[col];
                        if (headPos[row] > tailPos[row]) {
                            tailPos[row] = tailPos[row] + 1;
                        } else {
                            tailPos[row] = tailPos[row] - 1;
                        }
                        visitedPositions.add(Arrays.toString(tailPos));
                    } else if(Math.abs(headPos[col]-tailPos[col]) > 1) {
                        tailPos[row] = headPos[row];
                        if (headPos[col] > tailPos[col]) {
                            tailPos[col] = tailPos[col] + 1;
                        } else {
                            tailPos[col] = tailPos[col] - 1;
                        }
                        visitedPositions.add(Arrays.toString(tailPos));
                    }
                }
                
            }
            inputReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
       for (String pos : visitedPositions) {
            System.out.println(pos);
       }
        System.out.println(visitedPositions.size());
    }
}
