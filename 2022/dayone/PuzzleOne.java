import java.util.*;
import java.io.*;

class PuzzleOne {
    public static void main(String[] args) {
            List<Integer> calories = new ArrayList<>();
        try {
            File inputFile = new File("input.txt");
            Scanner inputReader = new Scanner(inputFile);
            int currentCalCount = 0;
            while(inputReader.hasNextLine()) {
                String data = inputReader.nextLine();
                if (data.equals("")) {
                    calories.add(currentCalCount);
                    currentCalCount = 0;
                } else {
                    currentCalCount += Integer.parseInt(data);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
       /* int maxIndex = 0;
        int maxCalories = 0;
        for (int i = 0; i < calories.size(); i++) {
            int c = calories.get(i);
            if (c > maxCalories) {
                maxIndex = i + 1;
                maxCalories = c;
            }
        }
        System.out.println(maxIndex + " : " + maxCalories);*/
        Collections.sort(calories);
        int topk = 3;
        int totalCalories = 0;
        while (topk > 0) {
            totalCalories += calories.get(calories.size()-topk);
            topk--;
        }
        System.out.println(totalCalories);
    }
}
