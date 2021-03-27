import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class EdmondsKarpAlgorithm{

    private List<Edge> flowGraph[];
    private final int numberOfNodes;
    private final int source;
    private final int sink;


    public EdmondsKarpAlgorithm(ArrayList<List<String>> fileData){
        numberOfNodes = Integer.parseInt(fileData.remove(0).get(0));
        flowGraph = new LinkedList[numberOfNodes];
        source = numberOfNodes - numberOfNodes;
        sink = numberOfNodes -1;


        for (int i = 0; i < numberOfNodes; i++) {
            flowGraph[i] = new LinkedList<>();
        }

        for(List<String> item : fileData){
            addEdge(Integer.parseInt(item.get(0)),Integer.parseInt(item.get(1)),Integer.parseInt(item.get(2)));
        }

/*        System.out.println(flowGraph);
        for(int i=0;i<flowGraph.length;i++) {
            System.out.println(i + "=>" + flowGraph[i]);
*//*            for (Edge edge : item){
                System.out.println(edge.getStartNode()+" " + edge.getEndNode() +" " + edge.getFlowCapacity());
            }*//*
        }*/
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


            //for (Edge edge = visitedEdges[sinkEdgeIndex]{

            if (visitedEdges[sink] == null){break;}
            for (Edge edge = visitedEdges[sink];edge!=null;edge=visitedEdges[edge.getStartNode()]){
                //System.out.println("botle "+ bottleNeck  +  "start node "+edge.getStartNode());
                //if (edge.getStartNode()== source){break;}
                bottleNeck = Math.min(edge.availableFlow(),bottleNeck);
                /*if (edge.availableFlow() < bottleNeck){
                    //System.out.println("hola");
                    bottleNeck = edge.availableFlow();}*/
            }
            System.out.println("path flow: " + bottleNeck);
            if (bottleNeck == 0){break;}
            for (Edge edge = visitedEdges[sink];edge !=null;edge = visitedEdges[edge.getStartNode()]){
                //System.out.println("i am here");
                int adjustedFlow = edge.getCurrentFlow() + bottleNeck;
               // int adjustedCapacity = edge.getFlowCapacity() - bottleNeck;

                edge.setCurrentFlow(adjustedFlow);
                //edge.setFlowCapacity(adjustedCapacity);
            }
            maxFlow +=bottleNeck;
            System.out.println("current maxflow of graph: " + maxFlow);
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

    private void addEdge(int startNode,int endNode,int flowCapacity){
        Edge edge = new Edge(startNode,endNode,flowCapacity);
        flowGraph[startNode].add(edge);
    }

    private boolean visitStatus(List<Integer> visitedNodes ,Edge edge){
        for (int visitedNode:visitedNodes){
            if (visitedNode == edge.getEndNode()){return true;}
        }
        return false;
    }
}
