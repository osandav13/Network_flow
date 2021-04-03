import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class EdmondsKarpAlgorithm{

    private List<Edge>[] flowGraph;
    private final int numberOfNodes;
    private final int source;
    private final int sink;


    public EdmondsKarpAlgorithm(Graph graph,int numberOfNodes,int source,int sink){
        flowGraph = graph.getGraph();
        this.numberOfNodes = numberOfNodes;
        this.source = source;
        this.sink = sink;
    }

    public int RunEdmondsKarp(){
        int maxFlow = 0;
        while(true){
            //System.out.println("hi");
            int bottleNeck = Integer.MAX_VALUE;
            Edge sinkEdge = null;
            int sinkEdgeIndex = -1;
            List<Edge> path = new ArrayList<>();
            //List<Edge> visitedEdges = BreadthFirstSearch(numberOfNodes, source, sink);
            Edge[] visitedEdges = BreadthFirstSearch(numberOfNodes, source, sink);
            /*for (Edge visit : visitedEdges){
                System.out.println(visit);
            }*/
            //break;
/*
            for (Edge edge : visitedEdges){
                if (edge.getEndNode() == sink){
                    sinkEdge = edge;

                }
            }

            if(sinkEdge == null){break;}
            path.add()
            for (Edge edge = path.get(0); edge.getStartNode()!=source;){
                if (edge.getStartNode()==)

            }*/

            if (visitedEdges[sink] == null){break;}
            //System.out.print(sink);
            for (Edge edge = visitedEdges[sink];edge!=null;edge=visitedEdges[edge.getStartNode()]){
                //System.out.print(" -> "+edge.getStartNode());
                //System.out.println("botle "+ bottleNeck  +  "start node "+edge.getStartNode());
                //if (edge.getStartNode()== source){break;}
                bottleNeck = Math.min(edge.availableFlow(),bottleNeck);
            }
            //System.out.print("\n");
            //System.out.println("Path Flow: " + bottleNeck);
            if (bottleNeck == 0){break;}
            for (Edge edge = visitedEdges[sink];edge !=null;edge = visitedEdges[edge.getStartNode()]){
                int adjustedFlow = edge.getCurrentFlow() + bottleNeck;
                edge.setCurrentFlow(adjustedFlow);
            }
            maxFlow +=bottleNeck;
            //System.out.println("Current Max Flow Of Graph: " + maxFlow);
        }
        return maxFlow;
    }

    private Edge[] BreadthFirstSearch(int numberOfNodes,int source,int sink){
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> visitedNodes = new ArrayList<>();
        Edge[] visitedEdges = new Edge[numberOfNodes];

        visitedNodes.add(source);
        queue.add(source);
        while(!queue.isEmpty()){
            int node = queue.poll();
            if (node == sink){return visitedEdges;}

            for (Edge edge : flowGraph[node]){
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
