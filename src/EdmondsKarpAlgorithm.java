import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class EdmondsKarpAlgorithm{

    private int[][] flowGraph;
    private int numberOfNodes;

    public EdmondsKarpAlgorithm(ArrayList<List<String>>  fileData){
        numberOfNodes = Integer.parseInt(fileData.remove(0).get(0));
        flowGraph = new int[numberOfNodes][numberOfNodes];

        for (int i = 0; i < numberOfNodes; i++) {
            for (int j = 0; j < numberOfNodes; j++) {
                flowGraph[i][j] = 0;
            }
        }

        for(List<String> item : fileData){
            flowGraph[Integer.parseInt(item.get(0))][Integer.parseInt(item.get(1))] = Integer.parseInt(item.get(2));
            flowGraph[Integer.parseInt(item.get(1))][Integer.parseInt(item.get(0))] = Integer.parseInt(item.get(2));
        }
/*
        System.out.println(flowGraph);
        for (int i = 0; i < numberOfNodes; i++) {
            for (int j = 0; j < numberOfNodes; j++) {
                System.out.print(flowGraph[i][j]);
            }
            System.out.println("");
            System.out.println("////");
        }*/
    }

}
