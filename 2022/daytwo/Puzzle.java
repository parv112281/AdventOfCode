import java.util.*;
import java.io.*;

class Puzzle {
    public static void main(String[] args) {
        try {
            File inputFile = new File("input.txt");
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
