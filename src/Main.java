import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Graph graph;
    private static int numberOfNodes = 0;
    private static int source = -1;
    private static int sink = -1;
    public static void main(String[] args) {
        menu();
    }

    private static void menu(){
        Scanner scanner = new Scanner(System.in);

        while (true) {
            File file = new File("src/resources");
            String[] fileArray = file.list();
            for (int i =0; i < fileArray.length;i++){
                System.out.println(i+1 + ". "+ fileArray[i]);
            }
            System.out.println("Select a graph data set from above files");
            System.out.print("Please enter the file number: ");
            int fileNumber = Integer.parseInt(scanner.nextLine());
            String fileName = "src/resources/" + fileArray[fileNumber-1];
            ArrayList<List<String>> data = parseData(fileName);
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
                    maxFlow(data);
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
                source = Integer.parseInt(sourceInput);
                sink = Integer.parseInt(sinkInput);
                if(sink == source){ System.out.println("Source and Sink can't be same");}
                else if((source < 0  && source > numberOfNodes) && (sink < 0  && sink > numberOfNodes)){
                    System.out.println("Source and sink out of range");
                }
                else{isInputCorrect = true;}
            }catch (NumberFormatException exception){ System.out.println("Source and Sink has to be Integers");}
        }while(!isInputCorrect);

    }

    private static void maxFlow(ArrayList<List<String>> data){
        graph = new Graph(data,numberOfNodes);
        EdmondsKarpAlgorithm ed = new EdmondsKarpAlgorithm(graph,numberOfNodes,source,sink);
        long start = System.nanoTime();
        int maxFlow =  ed.RunEdmondsKarp();
        long finish = System.nanoTime();
        System.out.println("Max Flow is: " + maxFlow);
        System.out.println(finish-start + " nanoseconds");
    }

    private static ArrayList<List<String>> parseData(String filename){
        ArrayList<List<String>> data = new ArrayList<>();
        parser parser = new parser();
        try {
            System.out.println(filename);
            data = parser.readFile(filename);
        } catch (IOException exception) {
            System.out.println("File Not Found");
        }
        numberOfNodes = Integer.parseInt(data.remove(0).get(0));
        source = 0;
        sink = numberOfNodes -1;
        return data;
    }

    private static void addEdge(){
        //write the code
    }

    private static void displayFlowGraph(){
        //System.out.println(graph.getGraph());
        for (int i=0;i<graph.getGraph().length;i++){
            System.out.println(i + " => " + graph.getGraph()[i]);
        }
    }
}
