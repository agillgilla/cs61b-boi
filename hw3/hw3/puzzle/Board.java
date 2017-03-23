package hw3.puzzle;

public class Board implements WorldState {

    private int[][] tiles;
    private int N;
    /**
     * Constructs a board from an N-by-N array of tiles where
     * tiles[i][j] = tile at row i, column j
     * @param tiles
     */
    public Board(int[][] tiles) {
        this.tiles = new int[tiles.length][tiles[0].length];
        for (int i = 0; i < tiles.length; i++) {
            this.tiles[i] = tiles[i].clone();
        }
        this.N = tiles.length;
    }

    /**
     * Returns value of tile at row i, column j (or 0 if blank)
     * @param i
     * @param j
     * @return
     */
    public int tileAt(int i, int j) {
        return this.tiles[i][j];
    }

    /**
     * Returns the board size N
     * @return
     */
    public int size() {
        return this.N;
    }

    /**
     * Returns the neighbors of the current board
     * @return
     */
    public Iterable<WorldState> neighbors() {
        return null;
    }

    /**
     * Returns Hamming estimate described
     * @return
     */
    public int hamming() {
        int val = 0;
        int wrong = 0;
        for (int i = 0; i < this.N; i++) {
            for (int j = 0; j < this.N; j++) {
                val++;
                if (this.tileAt(i, j) != val && !(i == (this.N - 1) && j == (this.N - 1))) {
                    wrong++;
                }
            }
        }
        return wrong;
    }

    /**
     * Returns Manhattan estimate
     * @return
     */
    public int manhattan() {
        int manDistSum = 0;
        for (int i = 0; i < this.N; i++) {
            for (int j = 0; j < this.N; j++) {
                if (!(i == (this.N - 1) && j == (this.N - 1)) && this.tileAt(i, j) > 0 && this.tileAt(i, j) < this.N - 1) {
                    manDistSum += Math.abs(i - correctI(this.tileAt(i, j)));
                    manDistSum += Math.abs(j - correctJ(this.tileAt(i, j)));
                }
            }
        }
        return manDistSum;
    }

    /**
     * Estimated distance to goal. This method should
     * simply return the results of manhattan() when submitted to
     * Gradescope.
     * @return
     */
    public int estimatedDistanceToGoal() {
        return this.manhattan();
    }

    /**
     * Returns true if is this board the goal board
     * @return
     */
    public boolean isGoal() {
        int val = 0;
        for (int i = 0; i < this.N; i++) {
            for (int j = 0; j < this.N; j++) {
                val++;
                if (this.tileAt(i, j) != val && !(i == (this.N - 1) && j == (this.N - 1))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns true if this board's tile values are the same
     * position as y's
     * @param y
     * @return
     */
    public boolean equals(Object y) {
        Board other = (Board) y;
        if (this.N != other.getN()) {
            return false;
        }
        for (int i = 0; i < this.N; i++) {
            for (int j = 0; j < this.N; j++) {
                if (this.tileAt(i, j) != other.tileAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns the string representation of the board.
     * @return
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

    public int correctI(int x) {
        return (x - 1) / this.N;
    }

    public int correctJ(int x) {
        return (x - 1) % this.N;
    }

    public int getN() {
        return this.N;
    }

}
