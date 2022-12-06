import java.util.*;
import java.io.*;

class PuzzleOne {
    public static void main(String[] args) {
        String inputFileName = "input.txt";
        if (args.length > 0) {
            inputFileName = args[0];
        }
        int total = 0;
        try {
            File inputFile = new File(inputFileName);
            Scanner inputReader = new Scanner(inputFile);

            while(inputReader.hasNextLine()) {
                String data = inputReader.nextLine();
                String[] sections = data.split(",");
                if (sectionCompletelyOverlapping(sections)) {
                    total++;
                }
            }
            inputReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
       
        System.out.println(total);
    }

    static boolean sectionCompletelyOverlapping(String[] sections) {
        String[] sectionOne = sections[0].split("-");
        String[] sectionTwo = sections[1].split("-");
        int secOneStart = Integer.parseInt(sectionOne[0]);
        int secOneEnd = Integer.parseInt(sectionOne[1]);
        int secTwoStart = Integer.parseInt(sectionTwo[0]);
        int secTwoEnd = Integer.parseInt(sectionTwo[1]);
        if (secOneStart <= secTwoStart && secOneEnd >= secTwoEnd) {
            return true;
        }
        if (secTwoStart <= secOneStart && secTwoEnd >= secOneEnd) {
            return true;
        }
        return false;
    }
}
