package me.osanda;

import java.util.ArrayList;
import java.util.List;

/**
 *  Name : Osanda Ginige
 *  UoW id : w1761754
 *  IIT id : 2018181
 *  Last updated:  8/04/2021
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

    /**
     * Edge can be deleted given the start and the end node
     *
     * @param startNode Starting node of the edge
     * @param endNode End node of the edge
     * @return The edge that was deleted
     * @throws Exception Raised if the start node or end node is incorrect
     */
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

    /**
     * Edge object can be added to the graph using using this method
     * @param edge Edge object that needs to be added to the graph
     */
    public void addEdge(Edge edge) {
        graph[edge.getStartNode()].add(edge);
    }

    public List<Edge>[] getGraph() {
        return graph;
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
