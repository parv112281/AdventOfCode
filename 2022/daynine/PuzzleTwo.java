import java.util.*;
import java.io.*;

class PuzzleTwo {
    public static void main(String[] args) {
        Set<String> visitedPositions = new HashSet<>();
        String inputFileName = "input.txt";
        if (args.length > 0) {
            inputFileName = args[0];
        }
        try {
            File inputFile = new File(inputFileName);
            Scanner inputReader = new Scanner(inputFile);
            List<int[]> positions = new ArrayList<>();
            int ropeLength = 10;
            for (int i = 0; i < ropeLength; i++) {
                positions.add(new int[]{0, 0});
            }
            int row = 0;
            int col = 1;
            visitedPositions.add(Arrays.toString(positions.get(ropeLength-1)));
            while(inputReader.hasNextLine()) {
                String data = inputReader.nextLine();
                String[] parts = data.split(" ");
                String direction = parts[0];
                int distance = Integer.parseInt(parts[1]);
                //System.out.println("distance: " + distance);
                for (int i = 0; i < distance; i++) {
                    switch (direction) {
                        case "U":
                            positions.get(0)[row] -= 1;
                            break;
                        case "D":
                            positions.get(0)[row] += 1;
                            break;
                        case "L":
                            positions.get(0)[col] -= 1;
                            break;
                        case "R":
                            positions.get(0)[col] += 1;
                            break;
                    }
                    //System.out.println("i: " + i);
                    //System.out.println(Arrays.toString(positions.get(0)));
                    //System.out.println("XXXXXXXXXXXXXX");
                    for (int j = 1; j < positions.size(); j++) {
                        int[] headPos = positions.get(j-1);
                        int[] tailPos = positions.get(j);
                        if (Math.abs(headPos[col]-tailPos[col]) > 1 &&
                                Math.abs(headPos[row]-tailPos[row]) > 1) {
                            if (headPos[col] > tailPos[col]) {
                                tailPos[col] = tailPos[col] + 1;
                            } else {
                                tailPos[col] = tailPos[col] - 1;
                            }
                            if (headPos[row] > tailPos[row]) {
                                tailPos[row] = tailPos[row] + 1;
                            } else {
                                tailPos[row] = tailPos[row] - 1;
                            }
                            if (j == ropeLength-1) {
                                visitedPositions.add(Arrays.toString(tailPos));
                            }
                        } else if(Math.abs(headPos[col]-tailPos[col]) > 1) {
                            tailPos[row] = headPos[row];
                            if (headPos[col] > tailPos[col]) {
                                tailPos[col] = tailPos[col] + 1;
                            } else {
                                tailPos[col] = tailPos[col] - 1;
                            }
                            if (j == ropeLength-1) {
                                visitedPositions.add(Arrays.toString(tailPos));
                            }
                        } else if (Math.abs(headPos[row]-tailPos[row]) > 1) {
                            tailPos[col] = headPos[col];
                            if (headPos[row] > tailPos[row]) {
                                tailPos[row] = tailPos[row] + 1;
                            } else {
                                tailPos[row] = tailPos[row] - 1;
                            }
                            if (j == ropeLength-1) {
                                visitedPositions.add(Arrays.toString(tailPos));
                            }
                        }
                    }
                }
                for (int[] pos : positions) {
                     //System.out.println(Arrays.toString(pos));
                }
                //System.out.println("-----------------");
            }
            inputReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        // for (String pos : visitedPositions) {
        //     System.out.println(pos);
        // }
        System.out.println(visitedPositions.size());
    }
}
