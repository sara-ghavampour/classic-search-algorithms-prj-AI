import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


class A_StarComparator implements Comparator<A_StarState> {

    @Override
    public int compare(A_StarState o1, A_StarState o2) {
        if (o1.gN + o1.h > o2.gN + o2.h) {
            return 1;
        } else if (o1.gN + o1.h < o2.gN + o2.h){
            return -1;
        }else
            return 0;
    }
}


class A_StarState {


    public State state;
    public int gN;
    public int h;
    public A_StarState(State state, int gN, int h) {
        this.state = state;
        this.gN = gN;
        this.h = h;
    }
}
public class A_Star {


    public static void search(State initialState){
        System.out.println("A_Star");

        PriorityQueue<A_StarState> frontier = new PriorityQueue<A_StarState>(new A_StarComparator());
        frontier.add(new A_StarState(initialState, 0,HeuristicEvaluator.h_value(initialState)));
        Hashtable<String, Boolean> inFrontier = new Hashtable<>();
        Hashtable<String, Boolean> explored = new Hashtable<>();
        inFrontier.put(initialState.hash(), true);
        while (!frontier.isEmpty()) {
            A_StarState tempState = frontier.poll();
            inFrontier.remove(tempState.state.hash());
            if (isGoal(tempState.state)) {
                System.out.println("Cost : " + tempState.gN + "\nH : " + tempState.h);
                result(tempState.state);
                return;
            }
            explored.put(tempState.state.hash() , true);// add to explored after expansion
            ArrayList<State> children = tempState.state.successor();  // generate next level states(children)

            for(int i = 0;i<children.size();i++){
                if(!(inFrontier.containsKey(children.get(i).hash()))
                        && !(explored.containsKey(children.get(i).hash()))) {
                        frontier.add(new A_StarState(children.get(i),1 + tempState.gN,HeuristicEvaluator.h_value(children.get(i))));
                        inFrontier.put(children.get(i).hash(), true);
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
            FileWriter myWriter = new FileWriter("AStarResult.txt");
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

