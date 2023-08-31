
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


class GBFSStateComparator implements Comparator<GBFSState> {

    @Override
    public int compare(GBFSState o1, GBFSState o2) {
        if (o1.h > o2.h) {
            return 1;
        } else if (o1.h < o2.h) {
            return -1;
        }else
            return 0;
    }
}


class GBFSState {


    public State state;
    public int h;
    public GBFSState(State state, int h) {
        this.state = state;
        this.h = h;
    }
}
class GBFSSearch{



    public static void search(State initialState){

        PriorityQueue<GBFSState> frontier = new PriorityQueue<GBFSState>(new GBFSStateComparator());
        frontier.add(new GBFSState(initialState,HeuristicEvaluator.h_value(initialState)));
        Hashtable<String, Boolean> inFrontier = new Hashtable<>();
        Hashtable<String, Boolean> explored = new Hashtable<>();
        inFrontier.put(initialState.hash(), true);

        while (!frontier.isEmpty()) {
            GBFSState tempState = frontier.poll();
            inFrontier.remove(tempState.state.hash());
            if (isGoal(tempState.state)) { // check goaltest after expansion
                System.out.println("H : " + tempState.h);
                result(tempState.state);
                return;
            }
            explored.put(tempState.state.hash() , true);  // add to explored after expansion
            ArrayList<State> children = tempState.state.successor();  // generate next level states(children)

            for(int i = 0;i<children.size();i++){
                if(!(inFrontier.containsKey(children.get(i).hash()))
                        && !(explored.containsKey(children.get(i).hash()))) {
                        frontier.add(new GBFSState(children.get(i),HeuristicEvaluator.h_value(children.get(i))));
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
            FileWriter myWriter = new FileWriter("GBFSResult.txt");
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

