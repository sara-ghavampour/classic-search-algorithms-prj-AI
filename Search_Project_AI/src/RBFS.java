import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class RBFSState implements Comparable<RBFSState>{
     State state;
     int gN;
     int h_value;
     int totalNode_cost;

    public RBFSState(State state, int gN, int h_value) {
        this.state = state;
        this.h_value = h_value;
        this.gN = gN;
        this.totalNode_cost = h_value+gN;
    }
    public int compareTo(RBFSState g2) {
        return this.totalNode_cost - g2.totalNode_cost;
    }

}
public class RBFS {

    public static void search(State state) {
        rbfs(new RBFSState(state, 0, HeuristicEvaluator.h_value(state)), Integer.MAX_VALUE);
    }

    public static int rbfs(RBFSState initialState, int localCutOff) {


        ArrayList<State> successors = initialState.state.successor();
        ArrayList<RBFSState> rbfsStateSuccessors = new ArrayList<>();

        for (int i=0 ; i<successors.size();i++) { // construct successor array with  RBFSState
            RBFSState  RBFSState = new RBFSState(successors.get(i), initialState.gN + HeuristicEvaluator.h_value(successors.get(i)), HeuristicEvaluator.h_value(successors.get(i)));
            rbfsStateSuccessors.add(RBFSState);
        }
        if (isGoal(initialState.state)) { // goal test
            result(initialState.state);
            return -100;
        }


        if (rbfsStateSuccessors.size() == 0) { // a state that doesnt have any children should have f=MAX_VALUE
            return Integer.MAX_VALUE;
        }


        rbfsStateSuccessors.sort((s1, s2) -> (int) (s1.totalNode_cost) - (s2.totalNode_cost));
        RBFSState currentNode = rbfsStateSuccessors.get(0);

        while (currentNode.totalNode_cost <= localCutOff) {

            int newCutOff;
            if (rbfsStateSuccessors.size() > 1) { // has a second child
                RBFSState secondBestChild = rbfsStateSuccessors.get(1);
                newCutOff = Math.min(localCutOff, secondBestChild.totalNode_cost);
            } else {
                newCutOff = localCutOff;
            }
            int newFCost = rbfs(currentNode, newCutOff);


            if (newFCost < 0) {
                return -100;
            }

            currentNode.totalNode_cost=newFCost;
            rbfsStateSuccessors.sort((s1, s2) -> (int) (s1.totalNode_cost) - (s2.totalNode_cost));
            currentNode = rbfsStateSuccessors.get(0);
        }

        return currentNode.totalNode_cost;
    }


    private static boolean isGoal(State state){
        for (int i = 0; i < state.getGraph().size(); i++) {
            if(state.getGraph().getNode(i).getColor() == Color.Red
                    || state.getGraph().getNode(i).getColor() == Color.Black){
                return false;
            }
        }
        return true;
    }

    private static void result(State state){
        Stack<State> states = new Stack<State>();
        while (true){
            states.push(state);
            if(state.getParentState() == null){
                break;
            }
            else {
                state = state.getParentState();
            }
        }
        try {
            FileWriter myWriter = new FileWriter("RBFSResult.txt");
            System.out.println("initial state : ");
            while (!states.empty()){
                State tempState = states.pop();
                if(tempState.getSelectedNodeId() != -1) {
                    System.out.println("selected id : " + tempState.getSelectedNodeId());
                }
                tempState.getGraph().print();

                myWriter.write(tempState.getSelectedNodeId()+" ,");
                myWriter.write(tempState.outputGenerator()+"\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
