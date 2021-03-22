public class Edge {

    private int startNode;
    private int endNode;
    private int currentFlow;
    private int flowCapacity;

    public Edge(int endNode,int flowCapacity){
        //this.startNode = startNode;
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

    @Override
    public String toString() {
        return "[ " + endNode + " " + flowCapacity + " ]" ;
    }

    public int availableFlow(){
        return flowCapacity - currentFlow;
    }
}
