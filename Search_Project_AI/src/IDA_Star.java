import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;


public class IDA_Star {
    public static void search(State initialState){
        int nextCutOff;
        System.out.println("IDA_Star");
        int currCutOff = HeuristicEvaluator.h_value(initialState);  // first cutoff is equal to f(0)=g(0)+h(0)=h(0)

        A_StarState initNode = new A_StarState(initialState,0,currCutOff);  // currCutOff as f
        ArrayList<A_StarState> currentSearchPath = new ArrayList<>();  // nodes in our current paths
        currentSearchPath.add(initNode);  // add root to path -> visit root

        while (true){
            nextCutOff = returnCutOffRecursive(currentSearchPath, currCutOff);// find nextCutOff recursively by traversing the current path

            if (isGoal(currentSearchPath.get(currentSearchPath.size() - 1).state)) {    // last element in path is the last node that we add ti path in our search before termination = goal
                result(currentSearchPath.get(currentSearchPath.size() - 1).state);
                return;
            }

            currCutOff = nextCutOff; // update cut off
        }



    }


    private static int returnCutOffRecursive(ArrayList<A_StarState> path, int currFBound){  // takes current oath and current cutoff and move forward recursively to update cut off and look for goal
        int returnedCutOff;

        //// returnedCutOff for
        A_StarState currNode = path.get(path.size() - 1); // node that we want to expand
        ArrayList<State> children = currNode.state.successor();  // generate next level states(children)
        if(isGoal(currNode.state))  // goal node
            return 0;
        if(currNode.gN + currNode.h > currFBound)  // update f of this node
            return currNode.gN + currNode.h;

        else {
             returnedCutOff = Integer.MAX_VALUE;   // if this node is a leaf and doesnt have any children
        }


        for(int i=0;i<children.size();i++){

            A_StarState childNode = new A_StarState(children.get(i) , currNode.gN +  1 , HeuristicEvaluator.h_value(children.get(i) ));

            if(!path.contains(childNode)){// skip expanded nodes

                path.add(childNode);
                int minimumCutOffBiggerThanCurr = returnCutOffRecursive(path,currFBound);// expand this child


                if (minimumCutOffBiggerThanCurr < returnedCutOff)   // find mimimum f which is bigger than current cutoff to update cutOff
                    returnedCutOff = minimumCutOffBiggerThanCurr;  // update to find min


                if (minimumCutOffBiggerThanCurr == 0)   // goal node   // termination condition
                    return 0;


                path.remove(path.size() - 1); // back from this node
            }


        }

        return returnedCutOff;

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
            FileWriter myWriter = new FileWriter("IDA_StarResult.txt");
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

