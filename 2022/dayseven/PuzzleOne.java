import java.util.*;
import java.io.*;

class PuzzleOne {
    static List<Node> directorySizes = new ArrayList<>();
    public static void main(String[] args) {
        String inputFileName = "input.txt";
        long totalSize = 0;
        Node currNode = null;
        Node head = null;
        if (args.length > 0) {
            inputFileName = args[0];
        }
        try {
            File inputFile = new File(inputFileName);
            Scanner inputReader = new Scanner(inputFile);
            while(inputReader.hasNextLine()) {
                String data = inputReader.nextLine();
                String[] splitData = data.split(" ");
                if (splitData[0].equals("$")) {
                    if (splitData[1].equals("cd")) {
                        if (splitData[2].equals("..")) {
                            currNode = currNode.parent;
                        } else if (currNode == null) {
                            Node newNode = new Node(splitData[2]);
                            currNode = newNode;
                            if (head == null) {
                                head = currNode;
                            }
                        } else {
                            Node node = currNode.children.stream().
                                filter(n -> n.name.equals(splitData[2])).
                                findFirst().orElse(null);
                            if (node == null) {
                                Node newNode = new Node(splitData[2]);
                                newNode.parent = currNode;
                                currNode.children.add(newNode);
                                currNode = newNode;
                            } else {
                                currNode = node;
                            }

                        }
                    } else if (splitData[1].equals("ls")) {
                        // do nothing
                    }
                } else {
                    if (splitData[0].equals("dir")) {
                        
                        boolean childExists = currNode.children.stream().
                            filter(n -> n.name.equals(splitData[1])).
                            findFirst().orElse(null) != null;
                        if (!childExists) {
                            Node newNode = new Node(splitData[1]);
                            newNode.parent = currNode;
                            currNode.children.add(newNode);
                        }
                    } else {
                        Node node = currNode.children.stream().
                            filter(n -> n.name.equals(splitData[1])).
                            findFirst().orElse(null);
                        if (node == null) {
                            Node newNode = new Node(splitData[1], Long.parseLong(splitData[0]));
                            newNode.parent = currNode;
                            currNode.children.add(newNode);
                        }
                    }
                }
            }
            processTree(head);
            inputReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        var usedSpace = directorySizes.stream().filter(n -> n.name.equals("/")).findFirst().orElse(new Node("", 0)).size ;
        var freeSpace = 70000000 - usedSpace;
        var neededSpace = 30000000;
        System.out.println("neededSpace - freeSpace = " + (neededSpace - freeSpace));
        Collections.sort(directorySizes, (n1, n2) -> (int)(n1.size - n2.size));
        directorySizes.stream().filter(n -> n.size >= neededSpace - freeSpace).findFirst().ifPresent(n -> {
            System.out.println(n.name + " " + n.size);
        });
    }

    static long processTree(Node node) {
        if (node.children.size() == 0) {
            return node.size;
        }
        long totalSize = 0;
        for (Node child : node.children) {
            totalSize += processTree(child);
        }
        if (node.size == 0) {
            directorySizes.add(new Node(node.name, totalSize));
        }
        return totalSize;
    }

    static void printTree(Node head) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        int lvlSize = 1;
        while(!queue.isEmpty()) {
            Node currNode = queue.poll();
            System.out.print(currNode.name + ", ");
            for (Node child : currNode.children) {
                queue.add(child);
            }
            lvlSize--;
            if (lvlSize == 0) {
                System.out.println();
                lvlSize = queue.size();
            }
        }
    }
}



class Node {
    String name;
    long size;
    Node parent;
    List<Node> children;
    public Node(String name, long size) {
        this.name = name;
        this.size = size;
        this.children = new ArrayList<>();
    }
    public Node(String name) {
        this.name = name;
        this.size = 0;
        this.children = new ArrayList<>();
    }
}