import java.util.*;
import java.io.*;

class PuzzleTwo {
    public static void main(String[] args) {
        String inputFileName = "input.txt";
        Map<Integer, Stack<Character>> stacks = new HashMap<>();
        StringBuilder retString = new StringBuilder();
        if (args.length > 0) {
            inputFileName = args[0];
        }
        try {
            File inputFile = new File(inputFileName);
            Scanner inputReader = new Scanner(inputFile);
            boolean readingStack = true;
            while(inputReader.hasNextLine()) {
                String data = inputReader.nextLine();
                if (data.trim().length() == 0) {
                    readingStack = false;
                } else if (readingStack) {
                    for (int i = 1; i < data.length(); i += 4) {
                        if (data.charAt(i) >= 'A' && data.charAt(i) <= 'Z') {
                            int sNumber = i / 4 + 1;
                            if (!stacks.containsKey(sNumber)) {
                                stacks.put(sNumber, new Stack<Character>());
                            }
                            stacks.get(sNumber).push(data.charAt(i));
                        }
                    }
                } else {
                    String[] info = data.split(" ");
                    int moves = Integer.parseInt(info[1]);
                    int from = Integer.parseInt(info[3]);
                    int to = Integer.parseInt(info[5]);
                    Stack<Character> interimStack = new Stack<>();
                    for (int i = 0; i < moves; i++) {
                        interimStack.push(stacks.get(from).pop());
                    }
                    for (int i = 0; i < moves; i++) {
                        stacks.get(to).push(interimStack.pop());
                    }
                }
                
            }
            for (int i = 0; i < stacks.size(); i++) {
                retString.append(stacks.get(i+1).peek());
            }
            inputReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
       
        System.out.println(retString.toString());
    }
}
