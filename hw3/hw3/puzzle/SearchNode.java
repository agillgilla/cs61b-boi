package hw3.puzzle;

/**
 * Created by Arjun on 3/22/2017.
 */
public class SearchNode implements Comparable<SearchNode> {
    private WorldState state;
    private int distanceFromStart;
    private SearchNode prevState;
    private int estimatedDistanceToGoal;

    public SearchNode(WorldState state, SearchNode prevState) {
        this.state = state;
        this.prevState = prevState;
        if (this.prevState == null) {
            this.distanceFromStart = 0;
        } else {
            this.distanceFromStart = this.prevState.getDistanceFromStart();
        }
        this.estimatedDistanceToGoal = state.estimatedDistanceToGoal();
    }

    public int compareTo(SearchNode other) {
        return (this.getDistanceFromStart() + this.getEstimatedDistanceToGoal() - (other.getDistanceFromStart() + other.getEstimatedDistanceToGoal()));
    }

    public int getDistanceFromStart() {
        return this.distanceFromStart;
    }

    public SearchNode getPrevNode() {
        return this.prevState;
    }

    public int getEstimatedDistanceToGoal() {
        return this.estimatedDistanceToGoal;
    }

    public WorldState getState() {
        return this.state;
    }
}
