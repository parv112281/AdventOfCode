import java.util.*;
import java.io.*;

class PuzzleOne {
    public static void main(String[] args) {
        String inputFileName = "input.txt";
        int marker = -1;
        if (args.length > 0) {
            inputFileName = args[0];
        }
        try {
            File inputFile = new File(inputFileName);
            Scanner inputReader = new Scanner(inputFile);
            Map<Character, Integer> charMap = new HashMap<>();
            int windowSize = 3;
            while(inputReader.hasNextLine()) {
                String data = inputReader.nextLine();
                
                for (int i = 0; i < data.length(); i++) {
                    if (charMap.containsKey(data.charAt(i))) {
                        charMap.put(data.charAt(i), charMap.get(data.charAt(i)) + 1);
                    } else {
                        charMap.put(data.charAt(i), 1);
                    }
                    
                    boolean nonRepeating = checkNonRepeating(charMap);
                    
                    if (nonRepeating && i >= windowSize) {
                        marker = i + 1;
                        break;
                    }
                    
                    if (i >= windowSize) {
                        if (charMap.containsKey(data.charAt(i - windowSize))) {
                            char c = data.charAt(i - windowSize);
                            charMap.put(c, charMap.get(data.charAt(i - windowSize)) - 1);
                            if (charMap.get(c) == 0) {
                                charMap.remove(c);
                            }
                        }
                    }
                }
            }
            inputReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
       
        System.out.println(marker);
    }

    static boolean checkNonRepeating(Map<Character, Integer> charMap) {
        boolean nonRepeating = true;
        for (Map.Entry<Character, Integer> entry : charMap.entrySet()) {
            if(entry.getValue() > 1) {
                nonRepeating = false;
                break;
            }
        }
        return nonRepeating;
    }
}
