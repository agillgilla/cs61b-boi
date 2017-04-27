/**
 * Created by Arjun on 4/26/2017.
 */
import java.awt.Color;

public class Pixel {

    private Color color;
    private Pixel connection;
    private double energy;
    private double cumulativeEnergy;
    private int col;
    private int row;

    public Pixel(double energy, int row, int col) {
        this.energy = energy;
        this.row = row;
        this.col = col;
    }

    public void setConnection(Pixel parent) {
        this.connection = parent;
        if (this.connection == null) {
            this.cumulativeEnergy = this.energy;
        } else {
            this.cumulativeEnergy = parent.getCumEnergy() + this.energy;
        }
    }

    public double getCumEnergy() {
        return this.cumulativeEnergy;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public Pixel getConnection() {
        return this.connection;
    }
}
