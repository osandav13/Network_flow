import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Graph {
    //private int numberOfVertices; // No. of vertices
    //private int[][] adj; //Adjacency matrix
    private List<Edge>[] graph;

    public Graph(ArrayList<List<String>> fileData,int numberOfNodes) {
        graph = new ArrayList[numberOfNodes];
        for (int i = 0; i < numberOfNodes; i++) {
            graph[i] = new ArrayList<>();
        }

        for(List<String> item : fileData){
            Edge edge = new Edge(Integer.parseInt(item.get(0)),Integer.parseInt(item.get(1)),Integer.parseInt(item.get(2)));
            addEdge(edge);
        }

    }

    public List<Edge>[] getGraph() {
        return graph;
    }

    private void addEdge(Edge edge) {
        graph[edge.getStartNode()].add(edge);
    }

/*    @Override
    public String toString(){
        for(int i=0;i<graph.length;i++) {
            System.out.println(i + "=>" + graph[i]);
            *//*for (Edge edge : item){
            System.out.println(edge.getStartNode()+" " + edge.getEndNode() +" " + edge.getFlowCapacity());
            }*//*
        }
    }*/
}
