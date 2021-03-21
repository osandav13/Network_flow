import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class EdmondsKarpAlgorithm{

    private List<Edge>[] flowGraph;
    private int numberOfNodes;

    public EdmondsKarpAlgorithm(ArrayList<List<String>>  fileData){
        numberOfNodes = Integer.parseInt(fileData.remove(0).get(0));
        flowGraph = new List[numberOfNodes];

        for (int i = 0; i < numberOfNodes; i++) {
            flowGraph[i] = new ArrayList<>();
        }

        for(List<String> item : fileData){
            addEdge(Integer.parseInt(item.get(0)),Integer.parseInt(item.get(1)),Integer.parseInt(item.get(2)));
        }

        System.out.println(flowGraph);
        for(List<Edge> item:flowGraph){
            for (Edge edge : item){
                System.out.println(edge.getStartNode()+" " + edge.getEndNode() +" " + edge.getFlowCapacity());
            }
        }
        /*
        for (int i = 0; i < numberOfNodes; i++) {
            System.out.print(flowGraph[i].get(0). + flowGraph[i].get(1) + flowGraph[i].get(2));
            System.out.println("");
            System.out.println("////");
        }*/
    }

    private void BreadthFirstSearch(int source,int sink){
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> visitedNodes = new ArrayList<>();

        visitedNodes.add(source);
        queue.add(source);
        while(!queue.isEmpty()){
            int node = queue.poll();
            if (source == node){return;}
        }
    }

    private void addEdge(int startNode,int endNode,int flowCapacity){
        Edge edge = new Edge(startNode,endNode,flowCapacity);
        flowGraph[startNode].add(edge);
    }
}
