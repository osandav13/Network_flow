package me.osanda;

/**
 *  Name : Osanda Ginige
 *  UoW id : w1761754
 *  IIT id : 2018181
 */

public class Edge {

    private int startNode;
    private int endNode;
    private int currentFlow;
    private int flowCapacity;

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
