import java.util.*;
import java.io.*;

class PuzzleTwo {
    public static void main(String[] args) {
        String inputFileName = "input.txt";
        if (args.length > 0) {
            inputFileName = args[0];
        }
        try {
            File inputFile = new File(inputFileName);
            Scanner inputReader = new Scanner(inputFile);

            while(inputReader.hasNextLine()) {
                String data = inputReader.nextLine();
                
            }
            inputReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
       
        System.out.println("");
    }
}
