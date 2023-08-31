import java.util.ArrayList;

public class Graph {

    public Node[] nodes;
    private int nodeIndex= 0;  //? number pf currunt nodes?
    private int nodesNumber;// maximum number of  nodes?

    public Graph(int nodesNumber){
        nodes= new Node[nodesNumber];
        this.nodesNumber= nodesNumber;
    }


    public void addNode(Node node){
        if(nodeIndex < nodesNumber) {
            nodes[nodeIndex] = node;
            nodeIndex++;
        }
    }

    public void addLinkBetween(Node node1, Node node2){
        node1.addNeighborId(node2.getId());
        node2.addNeighborId(node1.getId());
    }

    public Graph copy(){
        Graph newGraph= new Graph(nodesNumber);
        for (int i = 0; i < nodesNumber; i++) {
            Node copiedNode= nodes[i].copy();
            newGraph.addNode(copiedNode);
        }
        return newGraph;
    }

    public Node getNode(int index){
        return nodes[index];
    }

    public int size(){
        return nodeIndex;
    }

    public ArrayList<Node> getLeafNodes() {
        ArrayList<Node> leafNodes = new ArrayList<>();
        for(int i=0 ; i< nodes.length;i++){
            if(nodes[i].getNeighborsIds().size()==0){
                leafNodes.add(nodes[i]);
            }
        }
        return  leafNodes;
    }

    public void print(){
//        System.out.println("................................ graph ................................");
        for (int i = 0; i < nodes.length; i++) {
            System.out.print(nodes[i].getId() + ":" + nodes[i].getColor().toString() + " // ");
        }
        System.out.println("\n--------------------------------------------------------------------------");
    }
}
