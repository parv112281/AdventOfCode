import java.util.*;
import java.io.*;

class PuzzleTwo {
    public static void main(String[] args) {
        String inputFileName = "input.txt";
        int total = 0;
        if (args.length > 0) {
            inputFileName = args[0];
        }
        try {
            File inputFile = new File(inputFileName);
            Scanner inputReader = new Scanner(inputFile);

            while(inputReader.hasNextLine()) {
                String data = inputReader.nextLine();
                String[] sections = data.split(",");
                if (sectionsOverlap(sections)) {
                    total++;
                }
            }
            inputReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
       
        System.out.println(total);
    }

    static boolean sectionsOverlap(String[] sections) {
        String[] sectionOne = sections[0].split("-");
        String[] sectionTwo = sections[1].split("-");
        int secOneStart = Integer.parseInt(sectionOne[0]);
        int secOneEnd = Integer.parseInt(sectionOne[1]);
        int secTwoStart = Integer.parseInt(sectionTwo[0]);
        int secTwoEnd = Integer.parseInt(sectionTwo[1]);
        if (secOneStart <= secTwoStart && secOneEnd >= secTwoStart) {
            return true;
        }
        if (secTwoStart <= secOneStart && secTwoEnd >= secOneStart) {
            return true;
        }
        return false;
    }
}
