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

    private Graph flowGraph; //List<me.osanda.Edge>[] ;
    //private final int numberOfNodes;
    //private final int source;
    //private final int sink;


    public EdmondsKarpAlgorithm(Graph graph){
        flowGraph = graph;
        //this.numberOfNodes = numberOfNodes;
        //this.source = source;
        //this.sink = sink;
        //System.out.println(source + " " + sink);
    }

    /**
     *
     * @return
     */
    public int RunEdmondsKarp(){
        int maxFlow = 0;
        while(true){
            //System.out.println("hi");
            int bottleNeck = Integer.MAX_VALUE;
            List<Edge> path = new ArrayList<>();
            //List<me.osanda.Edge> visitedEdges = BreadthFirstSearch(numberOfNodes, source, sink);
            Edge[] visitedEdges = BreadthFirstSearch(flowGraph.getNumberOfNodes(),flowGraph.getSource(),flowGraph.getSink());

            if (visitedEdges[flowGraph.getSink()] == null){break;}
            System.out.print(flowGraph.getSink());
            for (Edge edge = visitedEdges[flowGraph.getSink()];edge!=null;edge=visitedEdges[edge.getStartNode()]){
                System.out.print(" -> "+edge.getStartNode());
                //if (edge.getStartNode()== source){break;}
                bottleNeck = Math.min(edge.availableFlow(),bottleNeck);
            }
            System.out.print("\n");
            System.out.println("Path Flow: " + bottleNeck);
            if (bottleNeck == 0){break;}
            for (Edge edge = visitedEdges[flowGraph.getSink()];edge !=null;edge = visitedEdges[edge.getStartNode()]){
                int adjustedFlow = edge.getCurrentFlow() + bottleNeck;
                edge.setCurrentFlow(adjustedFlow);
            }
            maxFlow +=bottleNeck;
            System.out.println("Current Max Flow : " + maxFlow);
        }
        return maxFlow;
    }

    /**
     *
     * @param numberOfNodes number of node in the graph
     * @param source source node of the graph
     * @param sink sink node of the graph
     * @return The visited edge object array
     */
    private Edge[] BreadthFirstSearch(int numberOfNodes,int source,int sink){
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> visitedNodes = new ArrayList<>();
        Edge[] visitedEdges = new Edge[numberOfNodes];

        visitedNodes.add(source);
        queue.add(source);
        while(!queue.isEmpty()){
            int node = queue.poll();
            if (node == sink){return visitedEdges;}

            for (Edge edge : flowGraph.getGraph()[node]){
                if (!visitStatus(visitedNodes,edge) && edge.availableFlow() > 0){
                    queue.add(edge.getEndNode());
                    visitedNodes.add(edge.getEndNode());
                    visitedEdges[edge.getEndNode()] = edge;
                }
            }
        }
        return visitedEdges;
    }


    private boolean visitStatus(List<Integer> visitedNodes ,Edge edge){
        for (int visitedNode:visitedNodes){
            if (visitedNode == edge.getEndNode()){return true;}
        }
        return false;
    }
}
