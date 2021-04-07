package me.osanda;

import java.util.ArrayList;
import java.util.List;

/**
 *  Name : Osanda Ginige
 *  UoW id : w1761754
 *  IIT id : 2018181
 */
public class Graph {
    private ArrayList<Edge>[] graph; // Adjacency List
    private int numberOfNodes = 0; // Number of nodes in the graph
    private int source = -1;// Source node location
    private int sink = -1; // Sink node location

    /**
     * This constructor created the flow graph to be used
     * by the edmondsKarp algorithm. adjacency List is used
     * as the data structure created using a array of Array lists
     *
     * @param fileData Data read from the text files in the resources
     * @param source Source node
     * @param sink Sink node
     * @param numberOfNodes Number of nodes in the flow graph
     */
    public Graph(ArrayList<List<String>> fileData,int source,int sink,int numberOfNodes) {
        this.source = source;
        this.sink= sink;
        this.numberOfNodes = numberOfNodes;
        graph = new ArrayList[numberOfNodes];
        for (int i = 0; i < numberOfNodes; i++) {
            graph[i] = new ArrayList<>();
        }

        for(List<String> item : fileData){
            Edge edge = new Edge(Integer.parseInt(item.get(0)),Integer.parseInt(item.get(1)),Integer.parseInt(item.get(2)));
            addEdge(edge);
        }

    }
    public Edge DeleteEdge(int startNode, int endNode) throws Exception {
         ArrayList<Edge> Edges = graph[startNode];
         for (Edge edge: Edges){
             if (edge.getEndNode() == endNode){
                 graph[startNode].remove(edge);
                 return edge;
             }
         }
         throw new Exception();
    }

    public List<Edge>[] getGraph() {
        return graph;
    }

    public void addEdge(Edge edge) {
        graph[edge.getStartNode()].add(edge);
    }

    public int getNumberOfNodes() {
        return numberOfNodes;
    }

    public int getSource() {
        return source;
    }

    public int getSink() {
        return sink;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public void setSink(int sink) {
        this.sink = sink;
    }

}
