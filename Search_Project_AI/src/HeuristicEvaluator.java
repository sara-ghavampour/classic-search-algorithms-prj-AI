import java.util.LinkedList;

public class HeuristicEvaluator {
    public static int h_value(State state){
       // return 1;
        int nonGreenNodes=0;
        for (int i = 0; i < state.getGraph().size(); i++) {

            switch (state.getGraph().getNode(i).getColor()) {

                case Red -> nonGreenNodes++;
                case Black -> nonGreenNodes++;
            }

        }
        return nonGreenNodes;
    }
}
