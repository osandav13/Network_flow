import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class EdmondsKarpAlgorithm{

    private List<Edge> flowGraph[];
    private int numberOfNodes;

    public EdmondsKarpAlgorithm(ArrayList<List<String>>  fileData){
        numberOfNodes = Integer.parseInt(fileData.remove(0).get(0));
        flowGraph = new LinkedList[numberOfNodes];

        for (int i = 0; i < numberOfNodes; i++) {
            flowGraph[i] = new LinkedList<>();
        }

        for(List<String> item : fileData){
            addEdge(Integer.parseInt(item.get(0)),Integer.parseInt(item.get(1)),Integer.parseInt(item.get(2)));
        }

        System.out.println(flowGraph);
        for(int i=0;i<flowGraph.length;i++){
            System.out.println(i+"=>"+flowGraph[i]);
/*            for (Edge edge : item){
                System.out.println(edge.getStartNode()+" " + edge.getEndNode() +" " + edge.getFlowCapacity());
            }*/
        }
        /*
        for (int i = 0; i < numberOfNodes; i++) {
            System.out.print(flowGraph[i].get(0). + flowGraph[i].get(1) + flowGraph[i].get(2));
            System.out.println("");
            System.out.println("////");
        }*/
    }

    private Edge[] BreadthFirstSearch(int numberOfNodes,int source,int sink){
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> visitedNodes = new ArrayList<>();
        Edge[] path = new Edge[numberOfNodes];

        visitedNodes.add(source);
        queue.add(source);
        while(!queue.isEmpty()){
            int node = queue.poll();
            if (node == sink){return path;}

            for (Edge edge : flowGraph[node]){
                if (visitStatus(visitedNodes,node) && edge.availableFlow() > 0){
                    queue.add(edge.getEndNode());
                    visitedNodes.add(edge.getEndNode());
                    path[edge.getEndNode()] = edge;
                }
            }
        }
        return path;
    }

    private void addEdge(int startNode,int endNode,int flowCapacity){
        Edge edge = new Edge(endNode,flowCapacity);
        flowGraph[startNode].add(edge);
    }

    private boolean visitStatus(List<Integer> visitedNodes ,int node){
        for (int visitedNode:visitedNodes){
            if (visitedNode == node){return true;}
        }
        return false;
    }
}
