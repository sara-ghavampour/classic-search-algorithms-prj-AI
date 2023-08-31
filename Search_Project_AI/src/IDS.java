import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class  IDS {

    public static void search(State initialState,int maxCutOff){
        for(int c=0;c<maxCutOff;c++){
            Stack<State> frontier = new Stack<State>(); // fringe
            Hashtable<String, Boolean> inFrontier = new Hashtable<>();
            Hashtable<String, Boolean> explored = new Hashtable<>();
            if(isGoal(initialState)){
                result(initialState);
                return;
            }
            frontier.push(initialState);
            inFrontier.put(initialState.hash(),true);
            while (!frontier.isEmpty()){
                State tempState = frontier.pop();
                inFrontier.remove(tempState.hash());
                if (isGoal(tempState)) {
                    result(tempState);
                    return;
                }
                explored.put(tempState.hash(),true);



                if(getDepth(tempState)<=c){
                    ArrayList<State> children = tempState.successor();
                    for(int i = 0;i<children.size();i++){
                        if(!(inFrontier.containsKey(children.get(i).hash()))
                                && !(explored.containsKey(children.get(i).hash()))) {
                            frontier.add(children.get(i));
                            inFrontier.put(children.get(i).hash(), true);
                        }
                    }
                }
            }
        }
    }



    public static int getDepth(State state){
        int depth=1;
        while(state.getParentState()!=null){
            depth++;
            state=state.getParentState();
        }
        return depth;
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
        Stack<State>  states = new Stack<State>();
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
            FileWriter myWriter = new FileWriter("IdsResult.txt");
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



