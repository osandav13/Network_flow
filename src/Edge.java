public class Edge {

    private int startNode;
    private int endNode;
    private int flowCapacity;

    public Edge(int startNode,int endNode,int flowCapacity){
        this.startNode = startNode;
        this.endNode = endNode;
        this.flowCapacity = flowCapacity;
    }

    public int getStartNode() {
        return startNode;
    }

    public int getEndNode() {
        return endNode;
    }

    public int getFlowCapacity() {
        return flowCapacity;
    }
}
