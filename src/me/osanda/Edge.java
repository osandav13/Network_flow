package me.osanda;

/**
 *  Name : Osanda Ginige
 *  UoW id : w1761754
 *  IIT id : 2018181
 */

public class Edge {

    private int startNode; // Start Node of a edge
    private int endNode; // End node of a edge
    private int currentFlow; // Flow going through the edge
    private int flowCapacity; // Total capacity of flow the edge can carry

    /**
     * This constructor is used o initialize the a edge with
     * the information that need to be stored
     *
     * @param startNode Starting node of the edge
     * @param endNode Ending node of the edge
     * @param flowCapacity capacity of the edge
     */
    public Edge(int startNode,int endNode,int flowCapacity){
        this.startNode = startNode;
        this.endNode = endNode;
        this.flowCapacity = flowCapacity;
        this.currentFlow=0;
    }
    public int getFlowCapacity() {
        return flowCapacity;
    }
    public void setCurrentFlow(int currentFlow) {
        this.currentFlow = currentFlow;
    }

    public int getCurrentFlow() {
        return currentFlow;
    }

    public int getStartNode() {
        return startNode;
    }

    public int getEndNode() {
        return endNode;
    }

    @Override
    public String toString() {
        return "[ " + endNode + " " + flowCapacity + " ]" ;
    }

    public int availableFlow(){
        return flowCapacity - currentFlow;
    }
}
