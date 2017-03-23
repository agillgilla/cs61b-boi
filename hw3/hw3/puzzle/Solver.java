package hw3.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Arjun on 3/22/2017.
 */
public class Solver {
    private MinPQ<SearchNode> queue;
    private Iterable<WorldState> solution;
    private SearchNode start;
    private int moves;
    /**
     *  Constructor which solves the puzzle, computing
     *  everything necessary for moves() and solution() to
     *  not have to solve the problem again. Solves the
     *  puzzle using the A* algorithm. Assumes a solution exists.
     * @param initial
     */
    public Solver(WorldState initial) {
        this.queue = new MinPQ<>();
        this.start = new SearchNode(initial, null);
        this.queue.insert(this.start);
        this.solution = this.findSolution();

    }

    public Iterable<WorldState> findSolution() {
        SearchNode current = null;
        while (!this.queue.isEmpty()) {
            current = this.queue.delMin();
            if (current.getState().isGoal()) {
                return this.tracePath(current, this.start);
            }

            for (WorldState neighbor : current.getState().neighbors()) {
                if (!neighbor.equals(current.getPrevNode()) && !neighbor.equals(current)) {
                    this.queue.insert(new SearchNode(neighbor, current));
                }
            }
        }
        return null;
    }

    /**
     * Returns the minimum number of moves to solve the puzzle starting
     * at the initial WorldState.
     * @return
     */
    public int moves() {
        return this.moves;
    }

    /**
     * Returns a sequence of WorldStates from the initial WorldState
     * to the solution.
     * @return
     */
    public Iterable<WorldState> solution() {
        return this.solution;
    }

    public Iterable<WorldState> tracePath(SearchNode end, SearchNode start) {
        ArrayList<WorldState> path = new ArrayList<>();
        SearchNode current = end;
        while (!current.equals(start)) {
            path.add(0, current.getState());
            current = current.getPrevNode();
        }
        path.add(0, start.getState());
        this.moves = path.size() - 1;
        return path;
    }
}
