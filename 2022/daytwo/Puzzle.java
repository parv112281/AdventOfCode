import java.util.*;
import java.io.*;

class Puzzle {
    public static void main(String[] args) {
        Map<String, Integer> shapeScore = new HashMap<>(){{
            put("X", 1);
            put("Y", 2);
            put("Z", 3);
        }};
        int totalScore = 0;
        try {
            File inputFile = new File("input.txt");
            Scanner inputReader = new Scanner(inputFile);

            while(inputReader.hasNextLine()) {
                String data = inputReader.nextLine();
                String[] splitData = data.split(" ");
                totalScore += calculateScore(splitData[0], splitData[1]);
            }
            inputReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
       
        System.out.println(totalScore);
    }

    private static int calculateScore(String player, String me) {
        switch (player) {
            case "A":
                if (me.equals("X")) {
                    return 3;
                } else if (me.equals("Y")) {
                    return 1+3;
                } else {
                    return 2+6;
                }
            case "B":
                if (me.equals("X")) {
                    return 1+0;
                } else if (me.equals("Y")) {
                    return 2+3;
                } else {
                    return 3+6;
                }
            case "C":
                if (me.equals("X")) {
                    return 2+0;
                } else if (me.equals("Y")) {
                    return 3+3;
                } else {
                    return 1+6;
                }
            default:
                return 0;
        }
    }

    private static int calculateScore2(String player, String me) {
        switch (player) {
            case "A":
                if (me.equals("X")) {
                    return 0;
                } else if (me.equals("Y")) {
                    return 3;
                } else {
                    return 6;
                }
            case "B":
                if (me.equals("X")) {
                    return 0;
                } else if (me.equals("Y")) {
                    return 3;
                } else {
                    return 6;
                }
            case "C":
                if (me.equals("X")) {
                    return 6;
                } else if (me.equals("Y")) {
                    return 0;
                } else {
                    return 3;
                }
            default:
                return 0;
        }
    }
}
