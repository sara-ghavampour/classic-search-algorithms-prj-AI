import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class UCSStateComparator implements Comparator<UCSState> {

    @Override
    public int compare(UCSState o1, UCSState o2) {
        if (o1.gN > o2.gN) {
            return 1;
        } else {
            return -1;
        }
    }
}


class UCSState {


    public State state;
    public int gN;

    public UCSState(State state, int gN) {
        this.state = state;
        this.gN = gN;
    }
}

public class UCS {

    public static void search(State initialState) {
        System.out.println("UCS");
        PriorityQueue<UCSState> frontier = new PriorityQueue<UCSState>(new UCSStateComparator());
        frontier.add(new UCSState(initialState, 0));
        Hashtable<String, Boolean> inFrontier = new Hashtable<>();
        Hashtable<String, Boolean> explored = new Hashtable<>();
        inFrontier.put(initialState.hash(), true);
        while (!frontier.isEmpty()) {
            UCSState currState = frontier.poll();   // expansion
            inFrontier.remove(currState.state.hash() );
            if (isGoal(currState.state)) {
                result(currState.state);
                return;
            }
            ArrayList<State> children = currState.state.successor();  // generate next level states(children)
            explored.put(currState.state.hash() , true);  // put in explore after expansion

            for (int i=0;i<children.size();i++) {
                if (!(inFrontier.containsKey(children.get(i).hash()))
                        && !(explored.containsKey(children.get(i).hash()))) {
                    int state_colorCost = 0;
                    Color color=currState.state.getGraph().getNode(children.get(i).getSelectedNodeId()).getColor();
                    if(color.equals(Color.Red)){state_colorCost = 1;}
                   else if(color.equals(Color.Black)){state_colorCost = 2;}
                    else if(color.equals(Color.Green)){state_colorCost = 3;}

                    frontier.add(new UCSState(children.get(i),state_colorCost + currState.gN));
                    inFrontier.put(children.get(i).hash(), true);
                }
            }

        }

    }


    private static boolean isGoal(State state) {
        for (int i = 0; i < state.getGraph().size(); i++) {
            if (state.getGraph().getNode(i).getColor() == Color.Red
                    || state.getGraph().getNode(i).getColor() == Color.Black) {
                return false;
            }
        }
        return true;
    }

    private static void result(State state) {
        Stack<State> states = new Stack<State>();
        while (true) {
            states.push(state);
            if (state.getParentState() == null) {
                break;
            } else {
                state = state.getParentState();
            }
        }
        try {
            FileWriter myWriter = new FileWriter("UCSResult.txt");
            System.out.println("initial state : ");
            while (!states.empty()) {
                State tempState = states.pop();
                if (tempState.getSelectedNodeId() != -1) {
                    System.out.println("selected id : " + tempState.getSelectedNodeId());
                }
                tempState.getGraph().print();

                myWriter.write(tempState.getSelectedNodeId() + " ,");
                myWriter.write(tempState.outputGenerator() + "\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

