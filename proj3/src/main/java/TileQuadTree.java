/**
 * Created by Arjun on 4/11/2017.
 */
public class TileQuadTree {

    private Tile element;

    TileQuadTree NW;
    TileQuadTree NE;
    TileQuadTree SW;
    TileQuadTree SE;

    public TileQuadTree(Tile element) {
        this.element = element;
        this.NW = null;
        this.NE = null;
        this.SW = null;
        this.SE = null;
    }

    public TileQuadTree(Tile element, TileQuadTree NW, TileQuadTree NE, TileQuadTree SW, TileQuadTree SE) {
        this.element = element;
        this.NW = NW;
        this.NE = NE;
        this.SW = SW;
        this.SE = SE;
    }

    // RECURSIVE CONSTRUCTOR
    public TileQuadTree(TileQuadTree parent, int direction) {
        this.element = new Tile(parent.getElement(), direction);
        if (!(Integer.parseInt(this.element.getImgPath()) >= 444444)) {
            this.NW = new TileQuadTree(this, 1);
            this.NE = new TileQuadTree(this, 2);
            this.SW = new TileQuadTree(this, 3);
            this.SE = new TileQuadTree(this, 4);
        } else {
            this.NW = null;
            this.NE = null;
            this.SW = null;
            this.SE = null;
        }
    }

    public Tile getElement() {
        return this.element;
    }

    public void setNW(TileQuadTree NW) {
        this.NW = NW;
    }

    public void setNE(TileQuadTree NE) {
        this.NE = NE;
    }

    public void setSW(TileQuadTree SW) {
        this.SW = SW;
    }

    public void setSE(TileQuadTree SE) {
        this.SE = SE;
    }

}
