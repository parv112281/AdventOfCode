import java.util.*;
import java.io.*;

class Puzzle {
    public static void main(String[] args) {
        solvePuzzleTwo();
    }

    static void solvePuzzleTwo() {
        int pTotal = 0;
        try {
            File inputFile = new File("input.txt");
            Scanner inputReader = new Scanner(inputFile);
            List<String> lines = new ArrayList<>();
            while(inputReader.hasNextLine()) {
                String data = inputReader.nextLine();
                lines.add(data);
                if (lines.size() == 3) {
                    char c = puzzleTwoHelper(lines);
                    if (c >= 'a' && c <= 'z') {
                        pTotal += c - 'a' + 1;
                    } else {
                        pTotal += c - 'A' + 27;
                    }
                    lines.clear();
                }
            }
            inputReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
       
        System.out.println(pTotal);
    }

    static char puzzleTwoHelper(List<String> lines) {
        Set<Character> i1 = buildCharSet(lines.get(0));
        Set<Character> i2 = buildCharSet(lines.get(1));
        Set<Character> i3 = buildCharSet(lines.get(2));
        i1.retainAll(i2);
        i1.retainAll(i3);
        return i1.iterator().next();
    }

    static Set<Character> buildCharSet(String s) {
        Set<Character> set = new HashSet<Character>();
        for (int i = 0; i < s.length(); i++) {
            set.add(s.charAt(i));
        }
        return set;
    }

    static void solvePuzzleOne() {
        int pTotal = 0;
        try {
            File inputFile = new File("input.txt");
            Scanner inputReader = new Scanner(inputFile);

            while(inputReader.hasNextLine()) {
                String data = inputReader.nextLine();
                Set<Character> commonItems = findCommonItems(data);
                pTotal += calculatePriorityTotal(commonItems);
            }
            inputReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
       
        System.out.println(pTotal);
    }

    static Set<Character> findCommonItems(String data) {
        int mid = data.length() / 2;
        Set<Character> firstHalf = new HashSet<Character>();
        Set<Character> commonChars = new HashSet<>();
        for (int i = 0; i < mid; i++) {
            firstHalf.add(data.charAt(i));
        }
        for (int i = mid; i < data.length(); i++) {
            if (firstHalf.contains(data.charAt(i))) {
                commonChars.add(data.charAt(i));
            }
        }
        return commonChars;
    }

    static int calculatePriorityTotal(Set<Character> commonItems) {
        int total = 0;
        for (Character c : commonItems) {
            if (c >= 'a' && c <= 'z') {
                total += c - 'a' + 1;
            } else {
                total += c - 'A' + 27;
            }
            System.out.println("item: " + c + " total: " + total);
        }
        System.out.println("total: " + total);
        return total;
    }

}
