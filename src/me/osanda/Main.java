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
 */
public class Main {
    private static Graph graph;
    private static ArrayList<List<String>> data = new ArrayList<>();
    //private static int numberOfNodes = 0;
    //private static int source = -1;
    //private static int sink = -1;
    public static void main(String[] args) {
        menu();
    }

    private static void menu(){
        Scanner scanner = new Scanner(System.in);
        File file = new File("src/me/osanda/resources");
        String[] fileArray = file.list();
        for (int i =0; i < fileArray.length;i++){
            System.out.println(i+1 + ". "+ fileArray[i]);
        }
        System.out.println("Select a graph data set from above files");
        System.out.print("Please enter the file number: ");
        int fileNumber = Integer.parseInt(scanner.nextLine());
        String fileName = "src/me/osanda/resources/" + fileArray[fileNumber-1];
        ArrayList<List<String>> data = parseData(fileName);
        while (true) {

            System.out.println("-----------------------------------------------");
            System.out.println("Please Enter Your Choice:");
            System.out.println("1. Change Source and Sink Nodes");
            System.out.println("2. Calculate Maximum Flow");
            System.out.println("3. Add Edge");
            System.out.println("4. Display Flow Graph");
            System.out.println("0. To Exit The Menu");
            System.out.print("Enter your selection: ");
            String userInput = scanner.nextLine();
            if (userInput == "1"){}
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
    private static void changeSourceAndSink(){
        Scanner scanner = new Scanner(System.in);
        boolean isInputCorrect = false;
        do {
            System.out.print("Enter Source Node : ");
            String sourceInput = scanner.nextLine();
            System.out.print("Enter Sink Node : ");
            String sinkInput = scanner.nextLine();
            try {
                graph.setSource(Integer.parseInt(sourceInput));
                graph.setSink(Integer.parseInt(sinkInput));
                System.out.println(graph.getSource() + " " + graph.getSink());
                if(graph.getSink() == graph.getSource()){ System.out.println("Source and Sink can't be same");}
                else if((graph.getSource() < 0  && graph.getSource() > graph.getNumberOfNodes()-1) && (graph.getSink() < 0  && graph.getSink() > graph.getNumberOfNodes()-1)){
                    System.out.println("Source and sink out of range");
                }
                else{isInputCorrect = true;}
            }catch (NumberFormatException exception){ System.out.println("Source and Sink has to be Integers");}
        }while(!isInputCorrect);
    }

    private static void maxFlow(){
        //graph = new me.osanda.Graph(data,numberOfNodes);
        System.out.println("source is : " + graph.getSource() + " sink is: " + graph.getSink());
        EdmondsKarpAlgorithm ed = new EdmondsKarpAlgorithm(graph);
        long start = System.nanoTime();
        System.out.println("-----------------------------------------------");
        System.out.println("Path augmentation is in progress...");
        int maxFlow =  ed.RunEdmondsKarp();
        long finish = System.nanoTime();
        System.out.println("-------------------------");
        System.out.println("* Max Flow Of Graph: " + maxFlow + " *");
        System.out.println("-------------------------");
        System.out.println(finish-start + " nanoseconds");
    }

    private static ArrayList<List<String>> parseData(String filename){

        parser parser = new parser();
        try {
            System.out.println(filename);
            data = parser.readFile(filename);
        } catch (IOException exception) {
            System.out.println("File Not Found");
        }
        int numberOfNodes = Integer.parseInt(data.remove(0).get(0));
        int source = 0;
        int sink = numberOfNodes -1;
        graph = new Graph(data,source,sink,numberOfNodes);
        return data;
    }

    private static void addEdge(){
        int start = -1;
        int end = -1;
        int capacity = 0;
        Scanner scanner = new Scanner(System.in);
        boolean isInputsCorrect= false;
        do {
            System.out.print("Enter start Node : ");
            String startInput = scanner.nextLine();
            System.out.print("Enter Sink Node : ");
            String endInput = scanner.nextLine();
            System.out.print("Enter edge capacity : ");
            String capacityInput = scanner.nextLine();
            try {
                start = Integer.parseInt(startInput);
                end = Integer.parseInt(endInput);
                capacity = Integer.parseInt(capacityInput);
                if(graph.getSink() == graph.getSource()){ System.out.println("start and end nodes can't be same");}
                else if((graph.getSource() < 0  && graph.getSource() > graph.getNumberOfNodes()-1) && (graph.getSink()< 0  && graph.getSink() > graph.getNumberOfNodes()-1)){
                    System.out.println("start and end  nodes out of range");
                }
                else{isInputsCorrect = true;}
            }catch (NumberFormatException exception){ System.out.println("Source and Sink has to be Integers");}
        }while(!isInputsCorrect);
        graph.addEdge(new Edge(start,end,capacity));
    }

    private static void displayFlowGraph(){
        for (int i=0;i<graph.getGraph().length;i++){
            System.out.println(i + " => " + graph.getGraph()[i]);
        }
    }
}
