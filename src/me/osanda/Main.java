package me.osanda;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *  Name : Osanda Ginige
 *  UoW id : w1761754
 *  IIT id : 2018181
 *  Last updated:  8/04/2021
 */
public class Main {
    private static Graph graph;
    private static ArrayList<List<String>> data = new ArrayList<>();
    public static void main(String[] args) {
        menu();
    }

    /**
     * This is the starting point of the program
     */
    private static void menu(){
        Scanner scanner = new Scanner(System.in);
        File file = new File("src/me/osanda/resources");
        String[] fileArray = file.list();

        for (int i =0; i < fileArray.length; i++){
            System.out.println(i+1 + ". " + fileArray[i]);
        }

        System.out.println("Select a graph data set from above files");
        System.out.print("Please enter the file number: ");
        int fileNumber = Integer.parseInt(scanner.nextLine());
        String fileName = "src/me/osanda/resources/" + fileArray[fileNumber-1];
        parseData(fileName);

        while (true) {
            System.out.println("-----------------------------------------------");
            System.out.println("Please Select A Choice From below Options");
            System.out.println("1. Change Source and Sink Nodes");
            System.out.println("2. Calculate Maximum Flow");
            System.out.println("3. Add Edge");
            System.out.println("4. Delete Edge");
            System.out.println("5. Display Flow Graph");
            System.out.println("0. To Exit The Menu");
            System.out.print("Enter your selection: ");
            String userInput = scanner.nextLine();
            switch (userInput) {
                case "1":
                    changeSourceAndSink();
                    break;
                case "2":
                    maxFlow();
                    break;
                case "3":
                    addEdge();
                    break;
                case "4":
                    deleteEdge();
                    break;
                case "5":
                    displayFlowGraph();
                    break;
                case "0":
                    System.exit(0);
                default:
                    System.out.println("Your input is not correct");
                    break;
            }
        }
    }

    /**
     *  Change the source and sink
     */
    private static void changeSourceAndSink(){
        Scanner scanner = new Scanner(System.in);
        boolean isInputCorrect = false;
        do {
            System.out.print("Enter Source Node : ");
            String sourceInput = scanner.nextLine();
            System.out.print("Enter Sink Node : ");
            String sinkInput = scanner.nextLine();

            // try block are used to prevent wrong data types entering the graph
            try {
                graph.setSource(Integer.parseInt(sourceInput));
                graph.setSink(Integer.parseInt(sinkInput));
                boolean conditionOne = (graph.getSource() < 0  && graph.getSource() > graph.getNumberOfNodes() - 1);
                boolean conditionTwo = (graph.getSink() < 0  && graph.getSink() > graph.getNumberOfNodes() - 1);

                if(graph.getSink() == graph.getSource()){
                    System.out.println("Source and Sink can't be same");
                }
                else if(conditionOne && conditionTwo){
                    System.out.println("Source and sink out of range");
                }else{isInputCorrect = true;}
                System.out.println("Source has changed to: " + graph.getSource() + " and Sink has changed to: " + graph.getSink());
            }catch (NumberFormatException exception){
                System.out.println("Source and Sink has to be Integers");
            }

        }while(!isInputCorrect);
    }

    /**
     * Calculate the max flow for a given flow graph
     */
    private static void maxFlow(){
        System.out.println("Source is : " + graph.getSource() + "  Sink is: " + graph.getSink());
        EdmondsKarpAlgorithm algorithm = new EdmondsKarpAlgorithm(graph);
        long start = System.nanoTime();
        System.out.println("-----------------------------------------------");
        System.out.println("Path augmentation is in progress...");
        int maxFlow =  algorithm.RunEdmondsKarp();
        long finish = System.nanoTime();
        System.out.println("-------------------------");
        System.out.println("* Max Flow Of Graph: " + maxFlow + " *");
        System.out.println("-------------------------");
        Long value = finish - start;
        System.out.println( "Execution time :- " + value + " Nanoseconds");
    }

    /**
     *  This method is used to read data from a file
     *
     * @param filename File name of the data set
     */
    private static void parseData(String filename){
        Parser parser = new Parser();
        try {
            System.out.println(filename);
            data = parser.readFile(filename);
        } catch (IOException exception) {
            System.out.println("File Not Found");
        }
        // relevant information extract to create the graph
        int numberOfNodes = Integer.parseInt(data.remove(0).get(0));
        int source = 0;
        int sink = numberOfNodes -1;
        graph = new Graph(data,source,sink,numberOfNodes);
    }

    /**
     * Adding a edge to the graph that is already created
     */
    private static void addEdge(){
        int start = -1;
        int end = -1;
        int capacity = 0;
        Scanner scanner = new Scanner(System.in);
        boolean isInputsCorrect= false;

        do {
            System.out.print("Enter Start Node : ");
            String startInput = scanner.nextLine();
            System.out.print("Enter End Node : ");
            String endInput = scanner.nextLine();
            System.out.print("Enter edge capacity : ");
            String capacityInput = scanner.nextLine();

            // try block are used to prevent wrong data types entering the graph
            try {
                start = Integer.parseInt(startInput);
                end = Integer.parseInt(endInput);
                capacity = Integer.parseInt(capacityInput);
                boolean conditionOne = (graph.getSource() < 0  && graph.getSource() > graph.getNumberOfNodes() - 1);
                boolean conditionTwo = (graph.getSink() < 0  && graph.getSink() > graph.getNumberOfNodes() - 1);

                if(graph.getSink() == graph.getSource()){
                    System.out.println("start and end nodes can't be same");
                }else if(conditionOne && conditionTwo){
                    System.out.println("start and end  nodes out of range");
                }else{isInputsCorrect = true;}

            }catch (NumberFormatException exception){ System.out.println("Source and Sink has to be Integers");}

        }while(!isInputsCorrect);
        graph.addEdge(new Edge(start,end,capacity));
    }

    /**
     * Display the the flow graph store in the adjacency list
     */
    private static void displayFlowGraph(){
        for (int i=0;i<graph.getGraph().length;i++){
            System.out.println(i + " => " + graph.getGraph()[i]);
        }
    }

    /**
     * Delete a edge from the given graph
     */
    private static void deleteEdge(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Start Node: ");
        String startInput = scanner.nextLine();
        System.out.print("Enter the End Node: ");
        String endInput = scanner.nextLine();
        int startNode = -1;
        int endNode = -1;

        // try block are used to prevent wrong data types entering the graph
        try {
            startNode = Integer.parseInt(startInput);
            endNode = Integer.parseInt(endInput);

            try {
                Edge edge = graph.DeleteEdge(startNode,endNode);
                System.out.println("This edge was removed [ " + edge.getStartNode() + " " + edge.getEndNode() + " " +edge.getFlowCapacity() + " ]");
            } catch (Exception e) {
                System.out.println("Given start node and end node are incorrect");
            }

        }catch (NumberFormatException exception){
            System.out.println("Start and End nodes has to be Integers");
        }
    }
}
