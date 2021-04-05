package me.osanda;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *  Name : Osanda Ginige
 *  UoW id : w1761754
 *  IIT id : 2018181
 */
public class EdmondsKarpAlgorithm{

    private Graph flowGraph;

    public EdmondsKarpAlgorithm(Graph graph){
        flowGraph = graph;
    }

    /**
     * max flow is calculated according to edmondsKarp
     * algorithm
     *
     * @return max flow of the flow graph
     */
    public int RunEdmondsKarp(){
        // At the start of the algorithm max flow is set as zero
        int maxFlow = 0;
        // Infinite loop is run until bfs return a path with bottleneck
        // value of zero or if the provided path by bfs didnt reach the
        // sink node.
        while(true){
            int bottleNeck = Integer.MAX_VALUE;
            // Bfs returns the visited edges array to visitedEdges
            Edge[] visitedEdges = BreadthFirstSearch(flowGraph.getNumberOfNodes(),flowGraph.getSource(),flowGraph.getSink());

            if (visitedEdges[flowGraph.getSink()] == null){break;}
            System.out.print(flowGraph.getSink());
            // Below loop run to recreate the path from visitedEdges and print it
            for (Edge edge = visitedEdges[flowGraph.getSink()];edge!=null;edge=visitedEdges[edge.getStartNode()]){
                System.out.print(" -> "+edge.getStartNode());
                bottleNeck = Math.min(edge.availableFlow(),bottleNeck);
            }
            System.out.print("\n");
            System.out.println("Path Flow: " + bottleNeck);
            // If the bottle neck is not 0 then the path can be augmented
            if (bottleNeck == 0){break;}
            // Path is augmented and flow rates are adjusted to in the edges of the path
            for (Edge edge = visitedEdges[flowGraph.getSink()];edge !=null;edge = visitedEdges[edge.getStartNode()]){
                int adjustedFlow = edge.getCurrentFlow() + bottleNeck;
                edge.setCurrentFlow(adjustedFlow);
            }
            // After each augmentation bottleneck value is added to max flow
            maxFlow +=bottleNeck;
            System.out.println("Current Max Flow : " + maxFlow);
        }
        return maxFlow;
    }

    /**
     * Normal breadth first search with a return value of
     * the edges visited by the algorithm. bfs is used to find
     * the shortest path through the flow graph
     *
     * @param numberOfNodes number of node in the graph
     * @param source source node of the graph
     * @param sink sink node of the graph
     * @return The visited edge object array
     */
    private Edge[] BreadthFirstSearch(int numberOfNodes,int source,int sink){
        Queue<Integer> queue = new LinkedList<>(); // queue is used to find the next exploring node
        List<Integer> visitedNodes = new ArrayList<>(); // keep track of visited nodes by bfs
        Edge[] visitedEdges = new Edge[numberOfNodes];// keep track of visited edge to reconstruct the path

        visitedNodes.add(source);
        queue.add(source);

        // loop through the queue until its empty
        while(!queue.isEmpty()){
            // first element from queue is removed to start the exploring
            int node = queue.poll();
            // if he node equal to sink exploration of the graph stopped
            if (node == sink){return visitedEdges;}
            // all the connected edges are explored to find a path to sink
            for (Edge edge : flowGraph.getGraph()[node]){
                // if the edge is not previously explored and flow is bigger than 0
                // that edge is added to queue for exploration
                if (!visitStatus(visitedNodes,edge) && edge.availableFlow() > 0){
                    queue.add(edge.getEndNode());
                    visitedNodes.add(edge.getEndNode());
                    visitedEdges[edge.getEndNode()] = edge;
                }
            }
        }
        return visitedEdges;
    }

    /**
     * This Internal method used to find if a edge has
     * been previously visited by bfs
     *
     * @param visitedNodes list of nodes
     * @param edge Edge object
     * @return boolean is returned
     */
    private boolean visitStatus(List<Integer> visitedNodes ,Edge edge){
        for (int visitedNode:visitedNodes){
            if (visitedNode == edge.getEndNode()){return true;}
        }
        return false;
    }
}
