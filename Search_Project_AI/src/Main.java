public class Main {

    public static void main(String[] args) {

        //-----------------------------------------------> test1 :
//        Graph initialGraph= new Graph(5);
//        initialGraph.addNode(new Node(0, Color.Red));
//        initialGraph.addNode(new Node(1, Color.Red));
//        initialGraph.addNode(new Node(2, Color.Red));
//        initialGraph.addNode(new Node(3, Color.Green));
//        initialGraph.addNode(new Node(4, Color.Red));
//        initialGraph.addLinkBetween(initialGraph.getNode(0), initialGraph.getNode(1));
//        initialGraph.addLinkBetween(initialGraph.getNode(0), initialGraph.getNode(2));
//        initialGraph.addLinkBetween(initialGraph.getNode(0), initialGraph.getNode(3));
//        initialGraph.addLinkBetween(initialGraph.getNode(1), initialGraph.getNode(2));
//        initialGraph.addLinkBetween(initialGraph.getNode(3), initialGraph.getNode(4));

        //-----------------------------------------------> test2 :
//        Graph initialGraph= new Graph(7);
//        initialGraph.addNode(new Node(0, Color.Red));
//        initialGraph.addNode(new Node(1, Color.Black));
//        initialGraph.addNode(new Node(2, Color.Green));
//        initialGraph.addNode(new Node(3, Color.Red));
//        initialGraph.addNode(new Node(4, Color.Red));
//        initialGraph.addNode(new Node(5, Color.Green));
//        initialGraph.addNode(new Node(6, Color.Red));
//        initialGraph.addLinkBetween(initialGraph.getNode(0), initialGraph.getNode(4));
//        initialGraph.addLinkBetween(initialGraph.getNode(1), initialGraph.getNode(2));
//        initialGraph.addLinkBetween(initialGraph.getNode(1), initialGraph.getNode(3));
//        initialGraph.addLinkBetween(initialGraph.getNode(1), initialGraph.getNode(4));
//        initialGraph.addLinkBetween(initialGraph.getNode(3), initialGraph.getNode(5));
//        initialGraph.addLinkBetween(initialGraph.getNode(4), initialGraph.getNode(5));
//        initialGraph.addLinkBetween(initialGraph.getNode(5), initialGraph.getNode(6));
//
//        Graph goalGraph= new Graph(7);
//        goalGraph.addNode(new Node(0, Color.Green));
//        goalGraph.addNode(new Node(1, Color.Green));
//        goalGraph.addNode(new Node(2, Color.Green));
//        goalGraph.addNode(new Node(3, Color.Green));
//        goalGraph.addNode(new Node(4, Color.Green));
//        goalGraph.addNode(new Node(5, Color.Green));
//        goalGraph.addNode(new Node(6, Color.Green));
//        goalGraph.addLinkBetween(goalGraph.getNode(0), goalGraph.getNode(4));
//        goalGraph.addLinkBetween(goalGraph.getNode(1), goalGraph.getNode(2));
//        goalGraph.addLinkBetween(goalGraph.getNode(1), goalGraph.getNode(3));
//        goalGraph.addLinkBetween(goalGraph.getNode(1), goalGraph.getNode(4));
//        goalGraph.addLinkBetween(goalGraph.getNode(3), goalGraph.getNode(5));
//        goalGraph.addLinkBetween(goalGraph.getNode(4), goalGraph.getNode(5));
//        goalGraph.addLinkBetween(goalGraph.getNode(5), goalGraph.getNode(6));





        Graph initialGraph= new Graph(10);
        initialGraph.addNode(new Node(0, Color.Red));
        initialGraph.addNode(new Node(1, Color.Red));
        initialGraph.addNode(new Node(2, Color.Red));
        initialGraph.addNode(new Node(3, Color.Green));
        initialGraph.addNode(new Node(4, Color.Red));
        initialGraph.addNode(new Node(5, Color.Green));
        initialGraph.addNode(new Node(6, Color.Green));
        initialGraph.addNode(new Node(7, Color.Red));
        initialGraph.addNode(new Node(8, Color.Black));
        initialGraph.addNode(new Node(9, Color.Black));

        initialGraph.addLinkBetween(initialGraph.getNode(0), initialGraph.getNode(1));
        initialGraph.addLinkBetween(initialGraph.getNode(0), initialGraph.getNode(3));
        initialGraph.addLinkBetween(initialGraph.getNode(1), initialGraph.getNode(2));
        initialGraph.addLinkBetween(initialGraph.getNode(0), initialGraph.getNode(9));
        initialGraph.addLinkBetween(initialGraph.getNode(6), initialGraph.getNode(5));
        initialGraph.addLinkBetween(initialGraph.getNode(8), initialGraph.getNode(0));
        initialGraph.addLinkBetween(initialGraph.getNode(4), initialGraph.getNode(8));
        initialGraph.addLinkBetween(initialGraph.getNode(9), initialGraph.getNode(8));
        initialGraph.addLinkBetween(initialGraph.getNode(7), initialGraph.getNode(8));
        initialGraph.addLinkBetween(initialGraph.getNode(1), initialGraph.getNode(5));



        //-----------------------------------------------> test3 :
//        Graph initialGraph= new Graph(15);
//        initialGraph.addNode(new Node(0, Color.Red));
//        initialGraph.addNode(new Node(1, Color.Black));
//        initialGraph.addNode(new Node(2, Color.Black));
//        initialGraph.addNode(new Node(3, Color.Black));
//        initialGraph.addNode(new Node(4, Color.Red));
//        initialGraph.addNode(new Node(5, Color.Green));
//        initialGraph.addNode(new Node(6, Color.Green));
//        initialGraph.addNode(new Node(7, Color.Red));
//        initialGraph.addNode(new Node(8, Color.Red));
//        initialGraph.addNode(new Node(9, Color.Green));
//        initialGraph.addNode(new Node(10, Color.Red));
//        initialGraph.addNode(new Node(11, Color.Red));
//        initialGraph.addNode(new Node(12, Color.Red));
//        initialGraph.addNode(new Node(13, Color.Green));
//        initialGraph.addNode(new Node(14, Color.Red));
//
//        initialGraph.addLinkBetween(initialGraph.getNode(0), initialGraph.getNode(1));
//        initialGraph.addLinkBetween(initialGraph.getNode(0), initialGraph.getNode(2));
//        initialGraph.addLinkBetween(initialGraph.getNode(1), initialGraph.getNode(14));
//        initialGraph.addLinkBetween(initialGraph.getNode(1), initialGraph.getNode(2));
//        initialGraph.addLinkBetween(initialGraph.getNode(1), initialGraph.getNode(3));
//        initialGraph.addLinkBetween(initialGraph.getNode(2), initialGraph.getNode(5));
//        initialGraph.addLinkBetween(initialGraph.getNode(2), initialGraph.getNode(6));
//        initialGraph.addLinkBetween(initialGraph.getNode(2), initialGraph.getNode(7));
//        initialGraph.addLinkBetween(initialGraph.getNode(3), initialGraph.getNode(13));
//        initialGraph.addLinkBetween(initialGraph.getNode(3), initialGraph.getNode(14));
//        initialGraph.addLinkBetween(initialGraph.getNode(3), initialGraph.getNode(7));
//        initialGraph.addLinkBetween(initialGraph.getNode(4), initialGraph.getNode(6));
//        initialGraph.addLinkBetween(initialGraph.getNode(4), initialGraph.getNode(11));
//        initialGraph.addLinkBetween(initialGraph.getNode(5), initialGraph.getNode(10));
//        initialGraph.addLinkBetween(initialGraph.getNode(5), initialGraph.getNode(12));
//        initialGraph.addLinkBetween(initialGraph.getNode(6), initialGraph.getNode(11));
//        initialGraph.addLinkBetween(initialGraph.getNode(7), initialGraph.getNode(8));
//        initialGraph.addLinkBetween(initialGraph.getNode(7), initialGraph.getNode(9));
//        initialGraph.addLinkBetween(initialGraph.getNode(8), initialGraph.getNode(14));


        State initialState= new State(initialGraph, -1, null);
       // State goalState = new State(goalGraph, -1, null);

     // BFS.search(initialState);
       // DFS.search(initialState);
       // IDS.search(initialState,100);
       //GBFSSearch.search(initialState);
       // A_Star.search(initialState);
//        RBFS.search(initialState);
   IDA_Star.search(initialState);
//        UCS.search(initialState);
//         BDS.search(initialState,goalState);
    }
}
