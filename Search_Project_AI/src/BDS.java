import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class  BDS {

    public static void search(State initialState, State goal){
        /// construct data structures
        Queue<State> forward_frontier = new LinkedList<State>();
        Queue<State> backward_frontier = new LinkedList<State>();

        Hashtable<String, Boolean> inforward_frontier = new Hashtable<>();
        Hashtable<String, Boolean> inbackward_frontier = new Hashtable<>();

        Hashtable<String, Boolean> forwardExplored = new Hashtable<>();
        Hashtable<String, Boolean> backwardExplored = new Hashtable<>();
   // add root state and goal state
        forward_frontier.add(initialState);
        inforward_frontier.put(initialState.hash(),true);

        backward_frontier.add(goal);
        inbackward_frontier.put(goal.hash(), true);

        while (!forward_frontier.isEmpty() && !backward_frontier.isEmpty()){
            State frontCurrState = forward_frontier.poll();
            inforward_frontier.remove(frontCurrState.hash());
            forwardExplored.put(frontCurrState.hash(),true);

            State backCurrState = backward_frontier.poll();
            inbackward_frontier.remove(backCurrState.hash());
            backwardExplored.put(backCurrState.hash(), true);

            ArrayList<State> children_front = frontCurrState.successor();
            ArrayList<State> children_back = backCurrState.successor();

            for(int i = 0;i<children_front.size();i++){
                if(!(inforward_frontier.containsKey(children_front.get(i).hash()))
                        && !(forwardExplored.containsKey(children_front.get(i).hash()))) {

                    forward_frontier.add(children_front.get(i));
                    inforward_frontier.put(children_front.get(i).hash(), true);
                }
            }

            for(int i = 0;i<children_back.size();i++){
                if(!(inbackward_frontier.containsKey(children_back.get(i).hash()))
                        && !(backwardExplored.containsKey(children_back.get(i).hash()))) {

                    backward_frontier.add(children_back.get(i));
                    inbackward_frontier.put(children_back.get(i).hash(), true);
                }
            }

            State frontCollision = null;
            State backCollision = null;


            for (State s : forward_frontier) {
                for (State state : backward_frontier) {
                    if (s.hash().equals(state.hash())) {
                        frontCollision = s;
                        backCollision = state;


                        result(frontCollision,backCollision);

                        return;
                    }
                }
            }
        }
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

    private static void result(State frontCollision ,State backCollision){
        Stack<State> back_traverse = new Stack<>();
        Stack<State> states = new Stack<>();
        while (backCollision != null) {
            back_traverse.push(backCollision);
            backCollision = backCollision.getParentState();
        }

        while (!back_traverse.isEmpty()) {
            states.push(back_traverse.pop());
        }
        frontCollision = frontCollision.getParentState();
        while (frontCollision != null) {
            states.push(frontCollision);
            frontCollision = frontCollision.getParentState();
        }


        try {
            FileWriter myWriter = new FileWriter("BdsResult.txt");
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


